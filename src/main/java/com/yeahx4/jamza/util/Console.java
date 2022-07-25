package com.yeahx4.jamza.util;

import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Console class provides static methods to help use of system console(stdin).
 * Println or Print with text color, background color.
 * And some input methods frequently used for user input.
 *
 * @author yeahx4
 * @since 1.0
 * @see ConsoleColor
 * @see java.io.OutputStream
 * @see java.io.InputStream
 */
public final class Console {
    /**
     * Plain print method. Nothing different from System.out.println(String); but, shorter version
     * @param text text to print
     * @see java.io.OutputStream
     */
    public static void println(String text) {
        System.out.println(ConsoleColor.RESET + text);
    }

    /**
     * Print text with coloured text or coloured background. In this method only on color can be transferred.
     *
     * @param color Text-color or background-color of printing text. use {@link ConsoleColor}
     * @param text text to print
     * @see ConsoleColor
     */
    public static void println(String color, String text) {
        println(color + text + ConsoleColor.RESET);
    }

    /**
     * print text with multiple color. Internally sequence of text and background color is not determined.
     * {@code color1} and {@code color2} must be different type of color
     *
     * @param color1 text color of {@link ConsoleColor}
     * @param color2 background color of {@link ConsoleColor}
     * @param text text to print
     * @see ConsoleColor
     */
    public static void println(String color1, String color2, String text) {
        println(color1 + color2 + text + ConsoleColor.RESET);
    }

    /**
     * Plain print method. Nothing different from System.out.print(String); but, shorter version
     * @param text text to print
     * @see java.io.OutputStream
     */
    public static void print(String text) {
        System.out.print(ConsoleColor.RESET + text);
    }

    /**
     * Print text with coloured text or coloured background. In this method only on color can be transferred.
     *
     * @param color Text-color or background-color of printing text. use {@link ConsoleColor}
     * @param text text to print
     * @see ConsoleColor
     */
    public static void print(String color, String text) {
        print(color + text + ConsoleColor.RESET + ConsoleColor.RESET);
    }

    /**
     * print text with multiple color. Internally sequence of text and background color is not determined.
     * {@code color1} and {@code color2} must be different type of color
     *
     * @param color1 text color of {@link ConsoleColor}
     * @param color2 background color of {@link ConsoleColor}
     * @param text text to print
     * @see ConsoleColor
     */
    public static void print(String color1, String color2, String text) {
        print(color1 + color2 + text + ConsoleColor.RESET);
    }

    /**
     * Read user input of one line.
     * User required to enter to finish writing
     * @return text input from user
     * @see Scanner
     */
    public static String readLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }

    /**
     * Read user input after some question printed
     * same as {@link #readLine()}
     * @param question Question string to be asked. No {@code \n} will be printed.
     * @return text input from user
     * @see #readLine()
     */
    public static String readLine(String question) {
        print(question);
        return readLine();
    }

    public static String readLine(Function<String, Boolean> func) {
        String input;
        do {
            input = readLine();
        } while (!func.apply(input));

        return input;
    }

    public static String readLine(String question, Function<String, Boolean> func) {
        String input;
        do {
            input = readLine(question);
        } while (!func.apply(input));

        return input;
    }

    /**
     * read input from user. If non-integer value get in, user will be infinitely asked to input again with error message.
     * @return int input from user
     */
    public static int readInt() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                return scanner.nextInt();
            } catch (InputMismatchException ex) {
                println(ConsoleColor.RED, "숫자를 입력해주세요.");
            }
        }
    }

    /**
     * Read user int input.
     * @param question question string. This string won't be printed with {@code \n}
     * @return int input from user
     */
    public static int readInt(String question) {
        print(question);
        return readInt();
    }

    /**
     * Clear Console.
     *
     * This will work on terminals that support ANSI escape codes
     * It will not work on Windows' CMD
     * It will not work in the IDE's terminal
     */
    public static void clear() {
        println("\033[H\033[2J");
    }

    /**
     * Ask user of select options by index. Key of option must be unique.
     * Without title
     *
     * @param option Map of options. unique key(String) and value(String)
     *               Only value will be delivered to user.
     *               sort of map is same as put-order
     * @return unique key of option map {@code null} if canceled.
     */
    public static <T> T select(LinkedHashMap<T, String> option) {
        return select("", option);
    }

//    /**
//     * Ask user of select options by index. Key of option must be unique.
//     * Without title, automatic cancel message
//     * With automatic cancel
//     *
//     * @param option Map of options. unique key(String) and value(String)
//     *               Only value will be delivered to user.
//     *               sort of map is same as put-order
//     * @param cancelText Text shown for cancel
//     * @return unique key of option map {@code null} if canceled.
//     */
//    public static <T> T select(LinkedHashMap<T, String> option, String cancelText) {
//        return select("", option, cancelText);
//    }

//    /**
//     * Ask user of select options by index. Key of option must be unique.
//     * not automatic cancel message
//     *
//     * @param title Title of the selection
//     * @param option Map of options. unique key(String) and value(String)
//     *               Only value will be delivered to user.
//     *               sort of map is same as put-order
//     * @return unique key of option map {@code null} if canceled.
//     */
//    public static <T> T select(String title, LinkedHashMap<T, String> option) {
//        return select(title, option);
//    }

    /**
     * Ask user of select options by index. Key of option must be unique.
     * With automatic cancel message
     * @param title Title of the selection
     * @param option Map of options. unique key(String) and value(String)
     *               Only value will be delivered to user.
     *               sort of map is same as put-order
     * @return unique key of option map. {@code null} if canceled.
     */
    public static <T> T select(String title, LinkedHashMap<T, String> option) {
        T[] keys = (T[])option.keySet().toArray();
        boolean done = false;
        int answer;
        do {
            clear();
            if (!title.isEmpty())
                println(title);

            for (int i = 0; i < keys.length; i++) {
                println(String.format("%d. %s", i + 1, option.get(keys[i])));
            }

            println("");
            answer = readInt("=> ") - 1;

            if (answer < keys.length && answer >= 0)
                done = true;

        } while (!done);

        return keys[answer];
    }

    /**
     * Print colored text
     * work with {@link #print(String)}
     *
     * @param colorText colored text
     * @see ColorText
     */
    public static void printc(String colorText) {
        System.out.printf("%s%s%s", ConsoleColor.RESET, ColorText.convertColoredText(colorText), ConsoleColor.RESET);
    }

    /**
     * Println colored text
     * work with {@link #println(String)}
     *
     * @param colorText colored text
     * @see ColorText
     */
    public static void printlnc(String colorText) {
        System.out.printf("%s%s%s\n", ConsoleColor.RESET, ColorText.convertColoredText(colorText), ConsoleColor.RESET);
    }
}
