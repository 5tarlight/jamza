package com.yeahx4.jamza;

import com.yeahx4.jamza.champion.Player;
import com.yeahx4.jamza.util.Console;
import com.yeahx4.jamza.util.ConsoleColor;

import java.util.LinkedHashMap;

public class Jamza {
    public static void main(String[] args) {
        Console.println(ConsoleColor.BLACK, ConsoleColor.WHITE_BG, "Welcome to Jamza");

        String input;
        do {
            input = Console.select("무엇을 하시겠습니까?", new LinkedHashMap<String, String>(){
                {
                    put("new", "새로운 캐릭터 만들기");
                    put("load", "불러오기");
                    put("end", "종료");
                }
            });

            switch (input) {
                case "new":
                    if (Player.createNewChampion())
                        Player.changeLocation("test-map");
                    else
                        Console.printlnc("&r캐릭터를 만들지 못했습니다.");
                    break;
                case "load":
                    if (Player.loadChampion())
                        Player.changeLocation("test-map");
                    else
                        Console.printlnc("&c캐릭터를 불러오지 못했습니다.");
                break;
            }
        } while (!input.equals("end"));
    }
}
