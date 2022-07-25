package com.yeahx4.jamza.champion;

import com.yeahx4.jamza.util.Console;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Champion util static class
 *
 * @author yeahx4
 * @since 1.0
 */
public final class Player {
    /**
     * Directory where champion data stored
     */
    public static final String dir = "./data/player";
    private static File df = new File(dir);

    /**
     * Create new instance of champion with nickname
     * New champion instance is initial state of that class
     *
     * @param type Champion type ({@link Champions})
     * @param nickname new champion's nickname
     * @return new {@link Champion} instance
     */
    public static Champion newChampion(Champions type, String nickname) {
        switch (type) {
            case WARRIOR -> {
                return new Warrior(nickname);
            }
            default -> {
                return new Champion(nickname, null); // Cannot be executed
            }
        }
    }

    /**
     * Create new character with UX.
     * newly created character will be stored to file
     *
     * @return result
     */
    public static boolean createNewChampion() {
        Champions[] chms = Champions.values();
        LinkedHashMap<Champions, String> query = new LinkedHashMap<>();

        for (var ch : chms) {
            query.put(ch, newChampion(ch, "").name);
        }
        query.put(null, "취소");

        Champions input = Console.select("새로 만들 클래스를 선택해주세요.", query);
        if (input == null)
            return false;

        List<String> names = getChampionFileList();
        String nickname = Console.readLine(
                "이름을 지정해주세요 : ",
                str -> {
                    Pattern pattern = Pattern.compile("^[^*&%\s]+$");
                    Matcher matcher = pattern.matcher(str);
                    boolean reg = matcher.matches();

                    return reg &&
                            str.length() > 0 &&
                            str.length() < 15 &&
                            !names.contains(str);
                }
        );

        Champion champion = newChampion(input, nickname);

        return saveChampion(champion);
    }

    /**
     * Save champion class to file
     * name of file is champion name.
     * When rename champion, migrate old file too.
     *
     * @param champion champion class to write
     * @return result
     */
    public static boolean saveChampion(Champion champion) {
        File df = new File(dir);
        File file = new File(df, String.format("%s.champion", champion.nickname));

        try {
            if (!df.exists()) df.mkdirs();
            if (file.exists()) file.delete();
            file.createNewFile();

            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(champion);

            oos.close();
            fos.close();

            return true;
        } catch (IOException io) {
            return false;
        }
    }

    /**
     * load stored champion data from file system.
     *
     * @return result
     */
    public static boolean loadChampionFromFile() {
        return false;
    }

    /**
     * Check base directory is exists and create if not.
     * This method need to be called in the head of methods using file system.
     */
    private static void checkDir() {
        if (!df.exists()) df.mkdirs();
    }

    /**
     * Get all champions' name stored in file.
     *
     * @return List of names
     */
    public static List<String> getChampionFileList() {
        try {
            checkDir();
            List<String> files = Files.walk(Paths.get(dir))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .map(f -> f.getName())
                    .toList();

            return files;
        } catch (IOException ex) {
            return null;
        }
    }
}
