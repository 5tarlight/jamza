package com.yeahx4.jamza.champion;

public abstract class Entity {
    public final String name;

    public long exp;
    public int level;
    public int maxHp;
    public int hp;
    public int maxMp;
    public int mp;
    public int ad;
    public int ap;
    public int adDur;
    public int apDur;

    /**
     * preemptive strike
     */
    public int speed;
    /**
     * Critical damage
     */
    public int crit;
    /**
     * Incapacitation
     */
    public int incap;

    public int baseHp;
    public int baseMp;
    public int baseAd;
    public int baseAp;
    public int baseAdDur;
    public int baseApDur;

    public int growthHp;
    public int growthMp;
    public int growthAd;
    public int growthAp;
    public int growthAdDur;
    public int growthApDur;

    protected Entity(String name) {
        this.name = name;
    }

    public abstract int exp(int gain);

    /**
     * Heal every resource—mp and hp till the max
     */
    public void healAll() {
        mp = maxMp;
        hp = maxHp;
    }
}
