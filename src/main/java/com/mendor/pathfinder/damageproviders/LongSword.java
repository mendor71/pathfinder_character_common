package com.mendor.pathfinder.damageproviders;

import com.mendor.pathfinder.DamageInstance;
import com.mendor.pathfinder.inventory.IInventoryItem;
import com.mendor.pathfinder.types.DamageType;
import com.mendor.pathfinder.NoChanceSaveRoll;

import java.util.Random;

public class LongSword extends AbstractPhysicalDamageProvider {

    public LongSword() {
        this.setMinDamage(1);
        this.setMaxDamage(8);
        this.setCriticalChancePercent(5);
        this.setCriticalMultiplier(2);
        this.setTwoHanded(false);
        this.setTwoHandDamageBonus(1.5);
        this.setUseAgilityBonus(false);
        this.setUseStrengthBonus(true);
        this.setDamageType(DamageType.CUTTING);
        this.setSaveRoll(new NoChanceSaveRoll());

        this.setName("Long Sword");
        this.setDescription("Common Long Sword");
        this.setBuyCost(5.0);
        this.setSellCost(0.5);
    }


}
