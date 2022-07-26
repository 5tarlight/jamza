package com.yeahx4.jamza.champion;

import com.yeahx4.jamza.map.Maps;
import com.yeahx4.jamza.util.Console;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

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
    private static final File df = new File(dir);
    public static Champion current = null;

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
     * This method must be called in game cycle.
     *
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

                    if (
                        !reg ||
                        str.length() <= 0 ||
                        str.length() >= 15
                    ) return false;

                    assert names != null;
                    return !names.contains(str);
                }
        );

        Champion champion = newChampion(input, nickname);

        return saveChampion(champion);
    }

    /**
     * This method must be called in game cycle.
     *
     * Load saved champion data from file.
     *
     * @return result
     */
    public static boolean loadChampion() {
        LinkedHashMap<String, String> query = new LinkedHashMap<>();

        Objects.requireNonNull(getChampionFileList())
                .stream()
                .map(s -> s.split("\\.")[0]) // remove file extension
                .forEach(s -> query.put(
                        s,
                        String.format("%s (%s)", s, Objects.requireNonNull(getChampionFromFile(s)).name))
                );
        query.put("cancel", "취소");

        String nickname = Console.select("불러올 캐릭터를 선택해주세요.", query);
        if (nickname.equals("cancel"))
            return false;

         current = getChampionFromFile(nickname);
         return true;
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
     * Load {@link Champion} class from file with nickname.
     * {@code null} if not found.
     *
     * @param nick nickname to find
     * @return instance of champion
     */
    public static Champion getChampionFromFile(String nick) {
        File df = new File(dir);
        File file = new File(df, String.format("%s.champion", nick));
        checkDir();

        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            return (Champion) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
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
        checkDir();
        try (Stream<Path> walk = Files.walk(Paths.get(dir))) {

            return walk
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .map(File::getName)
                    .toList();
        } catch (IOException ex) {
            return null;
        }
    }

    /**
     * Change the location of the champion.
     * The choices to perform on this map are output.
     *
     * @param tag unique tag of the map
     */
    public static void changeLocation(String tag) {
        if (Maps.contains(tag)) {
            current.location = tag;
            Maps.get(tag).whenLocated();
        }
    }
}
