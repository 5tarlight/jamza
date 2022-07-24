package com.yeahx4.jamza.util;

import java.util.HashMap;

/**
 * Support minecraft-style color.
 * This might cause bug with texts containing & or etc.
 *
 * @author hellun205
 * @since 1.0
 * @deprecated
 */
@Deprecated
public final class ColorText {
    /**
     * identifier of color-unique token. If String contains this, it may be a problem.
     */
    public static final String colorCharacter = "&";

    /**
     * Ignore & char in text. \\& will be displayed literally.
     */
    public static final String ignoreCharacter = "\\";

    /**
     * Shortcuts of text color
     */
    public static final HashMap<Character,String> fgColors = new HashMap<Character, String>() {
        {
            put('n', "");
            put('0', ConsoleColor.BLACK);
            put('1', ConsoleColor.WHITE);
            put('b', ConsoleColor.BLUE);
            put('c', ConsoleColor.CYAN);
            put('r', ConsoleColor.RED);
            put('g', ConsoleColor.GREEN);
            put('p', ConsoleColor.PURPLE);
            put('y', ConsoleColor.YELLOW);
        }
    };

    /**
     * Shortcuts of background color
     */
    public static final HashMap<Character,String> bgColors = new HashMap<Character, String>() {
        {
            put('n', "");
            put('0', ConsoleColor.BLACK_BG);
            put('1', ConsoleColor.WHITE_BG);
            put('b', ConsoleColor.BLUE_BG);
            put('c', ConsoleColor.CYAN_BG);
            put('r', ConsoleColor.RED_BG);
            put('g', ConsoleColor.GREEN_BG);
            put('p', ConsoleColor.PURPLE_BG);
            put('y', ConsoleColor.YELLOW_BG);
        }
    };

    /**
     * Convert plain text to ANSI-colored text.
     * @param formattedText text to convert into ANSI.
     * @return ANSI text.
     * @deprecated 
     */
    @Deprecated
    public static String convertColoredText(String formattedText) {
        String[] splitText = formattedText.split(colorCharacter);
        StringBuilder stringBuilder = new StringBuilder();
        boolean isIgnore = false;

        if (formattedText.length() == 0) {
            return "";
        } else if (!formattedText.contains("&")) {
            return formattedText;
        } else {
            for (String str: splitText) {
                if (str.length() == 0)
                    continue;
                if (isIgnore) {
                    isIgnore = false;
                    stringBuilder.append(colorCharacter).append(str);
                    continue;
                }

                String text = "";
                char bgTag = str.charAt(0);
                char fgTag = str.charAt(1);

                if (str.charAt(str.length() - 1) == ignoreCharacter.charAt(0)) {
                    text = str.substring(2, str.length() - 1);
                    isIgnore = true;
                } else {
                    text = str.substring(2);
                }

                stringBuilder.append(ConsoleColor.RESET);

                if (fgColors.containsKey(fgTag))
                    stringBuilder.append(fgColors.get(fgTag));
                if (bgColors.containsKey(bgTag))
                    stringBuilder.append(bgColors.get(bgTag));
                stringBuilder.append(text);
            }
            stringBuilder.append(ConsoleColor.RESET);
        }

        return stringBuilder.toString();
    }
}
