package com.mendor.damageproviders;

import com.mendor.DamageInstance;
import com.mendor.DamageType;
import com.mendor.NoChanceSaveRoll;

import java.util.Random;

public class LongSword extends AbstractPhisicalDamageProvider {
    private final double twoHandDamageBonus = 1.5;

    public LongSword() {
        minDamage = 1;
        maxDamage = 8;
        criticalMultiplier = 2;
        criticalChancePercent = 5;
    }

    public DamageInstance doDamage(long attackBonus) {
        return new DamageInstance()
                .setDamageType(DamageType.CUTTING)
                .setAttackValue(attackBonus)
                .setDamageValue(calculateDamageValue())
                .setSaveRoll(new NoChanceSaveRoll());
    }

    private long calculateDamageValue() {
        int criticalRoll = new Random().nextInt(100);

        int iter = (criticalRoll >= 100 - criticalChancePercent) ? criticalMultiplier : 1;
        long sumDamage = 0;

        for (int i = 0; i < iter; i++) {
            long baseDamage = minDamage + (long) (Math.random() * (maxDamage - minDamage));

            if (twoHanded) {
               baseDamage = new Double(baseDamage * twoHandDamageBonus ).longValue();
            }

            sumDamage += baseDamage + damageBonus;
        }

        return sumDamage;
    }
}
