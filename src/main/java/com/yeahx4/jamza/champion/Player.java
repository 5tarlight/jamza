package com.yeahx4.jamza.champion;

import com.yeahx4.jamza.util.Console;

import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        String nickname = Console.readLine(
                "이름을 지정해주세요 : ",
                str -> {
                    Pattern pattern = Pattern.compile("^[^*&%\s]+$");
                    Matcher matcher = pattern.matcher(str);
                    boolean reg = matcher.matches();

                    Console.println(Boolean.toString(reg));

                    return !reg && str.length() > 0 && str.length() < 15;
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
     * @return
     */
    public static boolean saveChampion(Champion champion) {
        return false;
    }

    /**
     * load stored champion data from file system.
     *
     * @return result
     */
    public static boolean loadChampionFromFile() {
        return false;
    }
}
