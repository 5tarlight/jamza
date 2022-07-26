package com.yeahx4.jamza.map;

import com.yeahx4.jamza.champion.Player;
import com.yeahx4.jamza.util.Console;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * Map super class
 *
 * @author hellun205
 * @since 1.0
 */
public abstract class Map implements Serializable {
    public final String name;
    public final String description;
    public final boolean combatable;

    /**
     * The choices to perform on this map.
     * {@link #onPerform(String)} must have an actions on the options.
     */
    public final LinkedHashMap<String, String> optionsToPerform;

    /**
     * @param name the name of the map
     * @param description description of the map
     * @param combatable combat availability
     * @param optionsToPerform The choices to perform on this map.
     *                         {@link #onPerform(String)} must have an actions on the options.
     */
    protected Map(String name, String description, boolean combatable, LinkedHashMap<String, String> optionsToPerform) {
        this.name = name;
        this.description = description;
        this.combatable = combatable;
        this.optionsToPerform = optionsToPerform;
    }

    /**
     * Invoked when located on this map.
     * The choices to perform on this map are output.
     */
    public void whenLocated() {
        boolean done = false;
        do {
            String input = Console.selectc(String.format("&y%s&n에서 무엇을 하시겠습니까?", name), new LinkedHashMap<>() {
                {
                    put("info", "맵 정보 보기");
                    putAll(optionsToPerform);
                    put("end", "게임 종료");
                }
            });

            switch (input) {
                case "info":
                    Console.printlnc(String.format("&y%s&n\n%s", name, description));
                    Console.readLine();
                    break;
                case "end":
                    boolean exitQuestion = Console.select("게임을 종료하시겠습니까?", new LinkedHashMap<Boolean, String>() {
                        {
                            put(true, "네");
                            put(false, "아니요");
                        }
                    });
                    if (exitQuestion) {
                        Player.saveChampion(Player.current);
                        done = true;
                    }
                    break;
                default:
                    onPerform(input);
                    break;
            }
        } while(!done);
    }

    /**
     * Performs the action corresponding to the {@link #optionsToPerform}.
     * This must have an actions on the options.
     * @param key unique key of {@link #optionsToPerform}
     */
    public abstract void onPerform(String key);
}
