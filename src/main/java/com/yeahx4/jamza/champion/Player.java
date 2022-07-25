package com.yeahx4.jamza.champion;

import java.util.Arrays;

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

    public static boolean createNewChampion() {
        var chms = Arrays.asList(Champions.values());

        return false;
    }

    public static boolean loadChampionFromFile() {
        return false;
    }
}
