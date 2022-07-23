package com.yeahx4.jamza.util;
import java.util.InputMismatchException;
import java.util.Scanner;

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
        System.out.println(ConsoleColor.ANSI_RESET + text);
    }

    /**
     * Print text with coloured text or coloured background. In this method only on color can be transferred.
     *
     * @param color Text-color or background-color of printing text. use {@link ConsoleColor}
     * @param text text to print
     * @see ConsoleColor
     */
    public static void println(String color, String text) {
        println(color + text + ConsoleColor.ANSI_RESET);
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
        println(color1 + color2 + text + ConsoleColor.ANSI_RESET);
    }

    /**
     * Plain print method. Nothing different from System.out.print(String); but, shorter version
     * @param text text to print
     * @see java.io.OutputStream
     */
    public static void print(String text) {
        System.out.print(ConsoleColor.ANSI_RESET + text);
    }

    /**
     * Print text with coloured text or coloured background. In this method only on color can be transferred.
     *
     * @param color Text-color or background-color of printing text. use {@link ConsoleColor}
     * @param text text to print
     * @see ConsoleColor
     */
    public static void print(String color, String text) {
        print(color + text + ConsoleColor.ANSI_RESET + ConsoleColor.ANSI_RESET);
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
        print(color1 + color2 + text + ConsoleColor.ANSI_RESET);
    }

    public static String readLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static int readInt() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                return scanner.nextInt();
            } catch (InputMismatchException ex) {
                println(ConsoleColor.ANSI_RED, "숫자를 입력해주세요.");
            }
        }
    }
}
