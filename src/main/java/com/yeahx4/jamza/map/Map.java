package com.yeahx4.jamza.map;

import com.yeahx4.jamza.champion.Player;
import com.yeahx4.jamza.util.Console;

import java.io.Serializable;
import java.util.HashMap;
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
     * {@link #interact(String)} must have an actions on the options.
     */
    public final LinkedHashMap<String, String> interactOption;

    /**
     * @param name the name of the map
     * @param description description of the map
     * @param combatable combat availability
     * @param interactOption The choices to perform on this map.
     *                         {@link #interact(String)} must have an actions on the options.
     */
    protected Map(String name, String description, boolean combatable, LinkedHashMap<String, String> interactOption) {
        this.name = name;
        this.description = description;
        this.combatable = combatable;
        this.interactOption = interactOption;
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
                    putAll(interactOption);
                    put("end", "취소");
                }
            });

            switch (input) {
                case "info" -> {
                    Console.printlnc(String.format("&y%s&n\n%s", name, description));
                    Console.readLine();
                }
                case "end" -> {
                    Player.saveChampion(Player.current);
                    done = true;
                }
                default -> interact(input);
            }
        } while(!done);
    }

    /**
     * Performs the action corresponding to the {@link #interactOption}.
     * This must have an actions on the options.
     * @param key unique key of {@link #interactOption}
     */
    public abstract void interact(String key);

    private static HashMap<Maps, Map> maps = new HashMap<>() {{
       put(Maps.TestMap, new TestMap());
    }};

    public static Map get(Maps map) {
        return maps.get(map);
    }
}
