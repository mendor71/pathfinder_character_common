package com.mendor;

import com.mendor.types.AttributeType;

public class CharacterAttributeDetails {

    private Long id;
    private AttributeType type;
    private long valueNormal;
    private long modifier;
    private long tempValueBonus;
    private long tempModifierBonus;

    public CharacterAttributeDetails() {
    }

    public CharacterAttributeDetails(AttributeType type, long valueNormal) {
        this.type = type;
        this.valueNormal = valueNormal;
        calculateModifier();
    }

    public long increaseValue(long addValue) {
        valueNormal += addValue;
        modifier = calculateModifier();
        return valueNormal;
    }

    public long decreaseValue(long addValue) {
        valueNormal -= addValue;
        modifier = calculateModifier();
        return valueNormal;
    }

    public CharacterAttributeDetails setModifier(long modifier) {
        this.modifier = modifier;
        return this;
    }

    private long calculateModifier() {
        if (tempModifierBonus == 0)
            return modifier = (valueNormal + tempValueBonus - 10) / 2;
        else
            return modifier + tempValueBonus;
    }

    public void setTempValueBonus(long value) {
        this.tempValueBonus = value;
    }

    public void setTempModifierBonus(long value) {
        this.tempModifierBonus = value;
    }

    public void setType(AttributeType type) {
        this.type = type;
    }

    public void setValueNormal(long valueNormal) {
        this.valueNormal = valueNormal;
    }

    public long getModifier() {
        return modifier;
    }

    public AttributeType getType() {
        return type;
    }

    public long getValue() {
        return valueNormal + tempModifierBonus;
    }

    public Long getId() {
        return id == null ? -1 : id;
    }

    public CharacterAttributeDetails setId(Long id) {
        this.id = id;
        return this;
    }

    public long getValueNormal() {
        return valueNormal;
    }

    public long getTempValueBonus() {
        return tempValueBonus;
    }

    public long getTempModifierBonus() {
        return tempModifierBonus;
    }


}
