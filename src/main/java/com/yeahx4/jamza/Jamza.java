package com.yeahx4.jamza;

import com.yeahx4.jamza.util.Console;
import com.yeahx4.jamza.util.ConsoleColor;

public class Jamza {
    public static void main(String[] args) {
        Console.println(ConsoleColor.ANSI_BLACK, ConsoleColor.ANSI_WHITE_BACKGROUND, "Welcome to Jamza");

//        Console.println(ConsoleColor.ANSI_WHITE, ConsoleColor.ANSI_BLACK_BACKGROUND, String.format("input string: %s", Console.read()));
//        Console.println(ConsoleColor.ANSI_WHITE, ConsoleColor.ANSI_BLACK_BACKGROUND, String.format("input int: %d", Console.read_int()));
    }
}
