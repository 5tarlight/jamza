package com.yeahx4.jamza.map;

import java.util.HashMap;

/**
 * This is where the maps are.
 *
 * @author hellun205
 * @since 1.0
 */
public final class Maps {
    /**
     * unique tag of the maps
     */
    public static final HashMap<String, Map> list = new HashMap() {
        {
            put("test-map", new TestMap());
        }
    };

    /**
     * Finds and returns a Map with a unique tag.
     *
     * @param tag unique tag of the map
     * @return map found by unique tag
     */
    public static Map get(String tag) {
        return list.getOrDefault(tag, null);
    }

    public static boolean contains(String tag) {
        return list.containsKey(tag);
    }
}
