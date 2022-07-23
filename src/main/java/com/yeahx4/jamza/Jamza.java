package com.yeahx4.jamza;

import com.yeahx4.jamza.util.Console;
import com.yeahx4.jamza.util.ConsoleColor;

public class Jamza {
    public static void main(String[] args) {
        Console.println(ConsoleColor.BLACK, ConsoleColor.WHITE_BG, "Welcome to Jamza");

        Console.println(ConsoleColor.WHITE, ConsoleColor.BLACK_BG, String.format("%s를 입력했므", Console.readLine("입력 : ")));
        Console.println(ConsoleColor.WHITE, ConsoleColor.BLACK_BG, String.format("input int: %d", Console.readInt()));
    }
}
