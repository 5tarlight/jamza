package com.yeahx4.jamza.champion;

import java.io.Serializable;

/**
 * Champion super class
 * All subclasses must override fields otherwise default is 0.
 *
 * @author yeahx4
 * @since 1.0
 */
public class Champion extends Entity implements Serializable {
    public final Champions type;

    public String nickname;

    protected Champion(String name, Champions type) {
        super(name);
        this.type = type;
    }

    /**
     * Update health state.
     * Healing and damaging are both available.
     * Automatically adjust maximum and minimum value.
     *
     * @param value value affecting new health state.
     * @return updated health point
     */
    public int updateHealth(int value) {
        this.hp = Math.max(0, Math.min(this.maxHp, hp + value));
        return hp;
    }

    /**
     * Update mana state.
     * Refilling and using mana are both available.
     * Automatically adjust maximum and minimum value.
     *
     * @param value value to use or gain mana
     * @return updated mana
     */
    public int updateMana(int value) {
        this.mp = Math.max(0, Math.min(this.maxMp, mp + value));
        return mp;
    }

    /**
     * Update experience and level.
     * Stats will be updated by growth multiplier.
     * exp can be negative value but, not recommended.
     *
     * @param gain exp gain
     * @return updated level
     */
    @Override
    public int exp(int gain) {
        this.exp += gain;

        /*
        exp formula for level
        exp(x) = 100x(x+2)

        level(x) = -1 + sqrt(100 + x) / 10 (reverse function)
        */

        int newLevel = (int)Math.floor(-1 + Math.sqrt(100 + this.exp) / 10) + 1;

        if (newLevel != level) {
            level = newLevel;

            int prevHp = maxHp;
            int prevMp = maxMp;

            this.maxHp = baseHp + growthHp * level;
            this.maxMp = baseMp + growthMp * level;
            updateHealth(maxHp - prevHp);
            updateMana(maxMp - prevMp);

            this.ad = baseAd + growthAd * level;
            this.ap = baseAp + growthAp * level;
            this.adDur = baseAdDur + growthAdDur * level;
            this.apDur = baseApDur + growthApDur * level;
        }

        return level;
    }
}
