package com.yeahx4.jamza.util;

/**
 * Set of ANSI console color.
 * Some terminals not supporting ANSI protocol may be broken.
 *
 * @author yeahx4
 * @since 1.0
 */
public interface ConsoleColor {
    String RESET = "\u001B[0m";

    String BLACK = "\u001B[30m";
    String RED = "\u001B[31m";
    String GREEN = "\u001B[32m";
    String YELLOW = "\u001B[33m";
    String BLUE = "\u001B[34m";
    String PURPLE = "\u001B[35m";
    String CYAN = "\u001B[36m";
    String WHITE = "\u001B[37m";

    String BLACK_BG = "\u001B[40m";
    String RED_BG = "\u001B[41m";
    String GREEN_BG = "\u001B[42m";
    String YELLOW_BG = "\u001B[43m";
    String BLUE_BG = "\u001B[44m";
    String PURPLE_BG = "\u001B[45m";
    String CYAN_BG = "\u001B[46m";
    String WHITE_BG = "\u001B[47m";
}
