package com.yeahx4.jamza;

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
        } while (!input.equals("end"));

    Console.printlnc(Console.readLine());
    }
}
