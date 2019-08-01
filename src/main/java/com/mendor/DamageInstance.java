package com.mendor;

public class DamageInstance {
    DamageType damageType;
    private long damageValue;
    private long attackValue;
    private ISaveRoll saveRoll;

    public DamageInstance setDamageType(DamageType damageType) {
        this.damageType = damageType;
        return this;
    }

    public DamageInstance setDamageValue(long damageValue) {
        this.damageValue = damageValue;
        return this;
    }

    public DamageInstance setAttackValue(long attackValue) {
        this.attackValue = attackValue;
        return this;
    }

    public DamageInstance setSaveRoll(ISaveRoll saveRoll) {
        this.saveRoll = saveRoll;
        return this;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public long getDamageValue() {
        return damageValue;
    }

    public long getAttackValue() {
        return attackValue;
    }

    public ISaveRoll getSaveRoll() {
        return saveRoll;
    }

    @Override
    public String toString() {
        return "DamageInstance{" +
                "damageType=" + damageType +
                ", damageValue=" + damageValue +
                ", attackValue=" + attackValue +
                ", saveRoll=" + saveRoll +
                '}';
    }
}
