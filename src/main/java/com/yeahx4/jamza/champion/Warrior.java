package com.yeahx4.jamza.champion;

/**
 * Jamza's first class.
 * Warrior is AD base bruiser.
 * High AD damage multiplier and low AP damage multiplier.
 * High AD damage endurance and middle AP damage endurance.
 *
 * @author yeahx4
 * @since 1.0
 */
public final class Warrior extends Champion {
    public Warrior(String nickname) {
        super("전사", Champions.WARRIOR);
        this.nickname = nickname;

        exp = 0;
        baseHp = 600;
        growthHp = 120;
        baseMp = 250;
        growthMp = 60;
        baseAd = 60;
        growthAd = 12;
        baseAp = 20;
        growthAp = 6;
        baseAdDur = 50;
        growthAdDur = 9;
        baseApDur = 40;
        growthApDur = 7;

        exp(0);
        healAll();
    }
}
