package com.yeahx4.jamza.map;

import java.io.Serializable;

/**
 * Map super class
 *
 * @author hellun205
 * @since 1.0
 */
public abstract class Map implements Serializable {
    public final String name;
    public final String explanation;


//    public abstract <T> T (LinkedHashMap<>)

    protected Map(String name, String explanation) {
        this.name = name;
        this.explanation = explanation;
    }

    public void whenLocated() {

    }
}
