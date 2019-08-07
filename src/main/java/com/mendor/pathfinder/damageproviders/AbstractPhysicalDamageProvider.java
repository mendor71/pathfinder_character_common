package com.mendor.pathfinder.damageproviders;

import com.mendor.pathfinder.*;
import com.mendor.pathfinder.inventory.IInventoryItem;
import com.mendor.pathfinder.inventory.InventoryItem;
import com.mendor.pathfinder.types.AttributeType;
import com.mendor.pathfinder.types.DamageType;

import java.util.Random;


public abstract class AbstractPhysicalDamageProvider implements IDamageProvider, IInventoryItem {
    private int minDamage;
    private int maxDamage;
    private int criticalMultiplier;
    private int criticalChancePercent;
    private boolean twoHanded;
    private double twoHandDamageBonus;
    private PathfinderCharacter weaponOwner;
    private boolean useAgilityBonus;
    private boolean useStrengthBonus;
    private String name;
    private String description;
    private Double buyCost;
    private Double sellCost;
    private DamageType damageType;
    private ISaveRoll saveRoll;

    public DamageInstance doDamage(long attackBonus) {
        return new DamageInstance()
                .setDamageType(damageType)
                .setAttackValue(attackBonus)
                .setDamageValue(calculateDamageValue())
                .setSaveRoll(saveRoll);
    }

    protected long calculateDamageValue() {
        if (weaponOwner == null)
            throw new IllegalStateException("weapon owner must be define");

        int criticalRoll = new Random().nextInt(100);

        int iter = (criticalRoll >= 100 - criticalChancePercent) ? criticalMultiplier : 1;
        long sumDamage = 0;

        for (int i = 0; i < iter; i++) {
            long baseDamage = minDamage + (long) (Math.random() * (maxDamage - minDamage));

            if (twoHanded) {
                baseDamage = new Double(baseDamage * twoHandDamageBonus ).longValue();
            }

            sumDamage += baseDamage
                    + (useStrengthBonus ? weaponOwner.getAttributeModifier(AttributeType.STRENGTH) : 0)
                    + (useAgilityBonus ? weaponOwner.getAttributeModifier(AttributeType.AGILITY) : 0);
        }

        return sumDamage;
    }

    public void setMinDamage(int minDamage) {
        this.minDamage = minDamage;
    }

    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }

    public void setCriticalMultiplier(int criticalMultiplier) {
        this.criticalMultiplier = criticalMultiplier;
    }

    public void setCriticalChancePercent(int criticalChancePercent) {
        this.criticalChancePercent = criticalChancePercent;
    }

    public void setTwoHandDamageBonus(double twoHandDamageBonus) {
        this.twoHandDamageBonus = twoHandDamageBonus;
    }

    public void setUseAgilityBonus(boolean useAgilityBonus) {
        this.useAgilityBonus = useAgilityBonus;
    }

    public void setUseStrengthBonus(boolean useStrengthBonus) {
        this.useStrengthBonus = useStrengthBonus;
    }

    public void setTwoHanded(boolean value) {
        this.twoHanded = value;
    }

    @Override
    public void setOwner(PathfinderCharacter character) {
        this.weaponOwner = character;
    }

    public DamageInstance doDamage() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("use method DamageInstance doDamage(long attackBonus)");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBuyCost(Double buyCost) {
        this.buyCost = buyCost;
    }

    public void setSellCost(Double sellCost) {
        this.sellCost = sellCost;
    }

    public void setDamageType(DamageType damageType) {
        this.damageType = damageType;
    }

    public void setSaveRoll(ISaveRoll saveRoll) {
        this.saveRoll = saveRoll;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getBuyCost() {
        return buyCost;
    }

    @Override
    public double getSellCost() {
        return sellCost;
    }

    @Override
    public String toString() {
        return "AbstractPhysicalDamageProvider{" +
                "minDamage=" + minDamage +
                ", maxDamage=" + maxDamage +
                ", criticalMultiplier=" + criticalMultiplier +
                ", criticalChancePercent=" + criticalChancePercent +
                ", twoHanded=" + twoHanded +
                ", twoHandDamageBonus=" + twoHandDamageBonus +
                ", weaponOwner=" + weaponOwner +
                ", useAgilityBonus=" + useAgilityBonus +
                ", useStrengthBonus=" + useStrengthBonus +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", buyCost=" + buyCost +
                ", sellCost=" + sellCost +
                '}';
    }
}
