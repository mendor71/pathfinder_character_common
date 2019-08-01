package com.mendor.damageproviders;

import com.mendor.DamageInstance;
import com.mendor.IDamageProvider;


public abstract class AbstractPhisicalDamageProvider implements IDamageProvider {
    int minDamage;
    int maxDamage;
    int criticalMultiplier;
    int criticalChancePercent;
    long damageBonus;
    boolean twoHanded;

    public void setStrengthCharacterModifier(long value) {
        this.damageBonus = value;
    }

    public void setAgilityCharacterModifier(long value) {

    }

    public void setTwoHanded(boolean value) {
        this.twoHanded = value;
    }

    public DamageInstance doDamage() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("use method DamageInstance doDamage(long attackBonus)");
    }
}
