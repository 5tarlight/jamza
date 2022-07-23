package com.yeahx4.jamza.util;

import java.util.HashMap;

public final class ColorText {

    public static final String colorCharacter = "&";
    public static final String ignoreCharacter = "\\";

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

    public static String convertColoredText(String formattedText) {
        var splittedText = formattedText.split(colorCharacter);
        var stringBuilder = new StringBuilder();
        var isIgnore = false;

        if (formattedText.length() == 0) {
            return "";
        } else if (!formattedText.contains("&")) {
            return formattedText;
        } else {
            for (String str: splittedText) {
                if (str.length() == 0)
                    continue;
                if (isIgnore) {
                    isIgnore = false;
                    stringBuilder.append(colorCharacter).append(str);
                    continue;
                }

                var text = "";
                var bgTag = str.charAt(0);
                var fgTag = str.charAt(1);

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
