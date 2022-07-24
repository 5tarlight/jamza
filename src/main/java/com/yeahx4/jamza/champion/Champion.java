package com.yeahx4.jamza.champion;

public class Champion {
    public final String name;
    public final Champions type;

    public String nickname;

    public int exp;
    public int level;
    public int maxHp;
    public int hp;
    public int maxMp;
    public int mp;
    public int ad;
    public int ap;
    public int adDur;
    public int apDur;
    public int speed;
    public int crit;
    public int incap;

    public Champion(String name, Champions type) {
        this.name = name;
        this.type = type;
    }
}
