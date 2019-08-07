package com.mendor.pathfinder.damageproviders;

import com.mendor.pathfinder.DamageInstance;
import com.mendor.pathfinder.NoChanceSaveRoll;
import com.mendor.pathfinder.inventory.IInventoryItem;
import com.mendor.pathfinder.types.DamageType;

public class TwoHandedAxe extends PhysicalDamageProvider implements IInventoryItem {
    public TwoHandedAxe() {
        this.setMinDamage(1);
        this.setMaxDamage(12);
        this.setCriticalChancePercent(5);
        this.setCriticalMultiplier(3);
        this.setTwoHanded(true);
        this.setTwoHandDamageBonus(1.0);
        this.setUseAgilityBonus(false);
        this.setUseStrengthBonus(true);
        this.setDamageType(DamageType.CUTTING);
        this.setSaveRoll(new NoChanceSaveRoll());

        this.setName("Two Handed Axe");
        this.setDescription("Two Handed Axe");
        this.setBuyCost(10.0);
        this.setSellCost(2.5);
    }
}
