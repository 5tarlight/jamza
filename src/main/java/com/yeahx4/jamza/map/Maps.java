package com.yeahx4.jamza.map;


import java.util.HashMap;

public final class Maps {
    public static final HashMap<String, Map> list = new HashMap() {
        {
            put("test-map", new TestMap());
        }
    };

    public Map get(String uniqueTag) {
        return list.getOrDefault(uniqueTag, null);
    }
}
