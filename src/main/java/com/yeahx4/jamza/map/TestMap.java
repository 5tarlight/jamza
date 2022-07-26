package com.yeahx4.jamza.map;

import com.yeahx4.jamza.map.Map;
import com.yeahx4.jamza.util.Console;

import java.util.LinkedHashMap;

/**
 * Test map
 * This map was created for testing.
 *
 * @author hellun205
 * @since 1.0
 */
public final class TestMap extends Map {
    public TestMap () {
        super("테스트용 맵", "테스트를 하기위해 만들어진 맵이다.", false, new LinkedHashMap<>() {
            {
                put("test", "Test");
            }
        });
    }


    @Override
    public void onPerform(String key) {
        switch (key) {
            case "test":
                Console.println("test!");
                Console.readLine();
                break;
        }
    }
}
