package com.yeahx4.jamza.util;

import java.util.HashMap;

/**
 * Support minecraft-style color.
 * This might cause bug with texts containing & or etc.
 *
 * @author hellun205
 * @since 1.0
 */
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
    public static final HashMap<Character,String> colorTags = new HashMap<Character, String>() {
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
            put('Z', ConsoleColor.BLACK_BG);
            put('W', ConsoleColor.WHITE_BG);
            put('B', ConsoleColor.BLUE_BG);
            put('C', ConsoleColor.CYAN_BG);
            put('R', ConsoleColor.RED_BG);
            put('G', ConsoleColor.GREEN_BG);
            put('P', ConsoleColor.PURPLE_BG);
            put('Y', ConsoleColor.YELLOW_BG);
        }
    };

    /**
     * Convert plain text to ANSI-colored text.
     * @param formattedText text to convert into ANSI.
     * @return ANSI text.
     */
    public static String convertColoredText(String formattedText) {
        String[] splitText = (String.format("%s%s%s", colorCharacter, "n", formattedText).split(colorCharacter));
        StringBuilder stringBuilder = new StringBuilder();
        boolean isIgnore = false;

        if (formattedText.length() == 0) {
            return "";
        } else if (!formattedText.contains(colorCharacter)) {
            return formattedText;
        } else {
            for (String str: splitText) {
                String text;
                char colorTag;

                if (str.length() == 0)
                    continue;

                if (isIgnore) {
                    if (str.charAt(str.length() - 1) == ignoreCharacter.charAt(0)) {
                        isIgnore = true;
                        stringBuilder.append(colorCharacter).append(str.substring(0, str.length() - 1));
                    } else {
                        isIgnore = false;
                        stringBuilder.append(colorCharacter).append(str);
                    }
                    continue;
                }
                colorTag = str.charAt(0);

                if (ignoreCharacter.charAt(0) == str.charAt(str.length() - 1)) {
                    text = str.substring(0, str.length() - 1);
                    isIgnore = true;
                } else {
                    text = str;
                }

                if (colorTags.containsKey(colorTag)) {
                    stringBuilder.append(colorTags.get(colorTag));
                    stringBuilder.append(text.substring(1));
                } else
                    stringBuilder.append(text);
            }
        }
        return stringBuilder.toString();
    }
}
