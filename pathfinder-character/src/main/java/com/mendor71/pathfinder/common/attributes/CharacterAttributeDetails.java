package com.mendor71.pathfinder.common.attributes;


import com.mendor71.pathfinder.common.types.AttributeType;

import java.util.*;

public class CharacterAttributeDetails implements IAttributeNotifier {

    private Long id;
    private AttributeType type;
    private long value;
    private long modifier;
    private long tempValueBonus;
    private long tempModifierBonus;

    private Set<IAttributeListener> listenersList = new HashSet<>();

    public CharacterAttributeDetails() {
    }

    public CharacterAttributeDetails(AttributeType type, long value) {
        this.type = type;
        this.value = value;
        calculateModifier();
    }

    public Long getId() {
        return id == null ? -1 : id;
    }

    public void setType(AttributeType type) {
        this.type = type;
    }

    @Override
    public void addListener(IAttributeListener listener) {
        listenersList.add(listener);
        listener.update(this.value, this.modifier);
    }
    @Override
    public void notifyListeners() {
        listenersList.forEach(v -> v.update(value, modifier));
    }

    public long increaseValue(long addValue) {
        value += addValue;
        modifier = calculateModifier();
        notifyListeners();
        return value;
    }

    public long decreaseValue(long addValue) {
        value -= addValue;
        modifier = calculateModifier();
        notifyListeners();
        return value;
    }

    private long calculateModifier() {
        if (tempModifierBonus == 0)
            return modifier = (value + tempValueBonus - 10) / 2;
        else
            return modifier + tempModifierBonus;
    }

    public void setTempValueBonus(long value) {
        this.tempValueBonus = value;
    }

    public void setTempModifierBonus(long value) {
        this.tempModifierBonus = value;
    }

    public void increaseTempValueBonus(long value) {
        this.tempValueBonus += value;
    }

    public void decreaseTempValueBonus(long value) {
        this.tempValueBonus -= value;
    }

    public void increaseTempModifierBonus(long value) {
        this.tempModifierBonus += value;
    }

    public void decreaseTempModifierBonus(long value) {
        this.tempModifierBonus -= value;
    }

    public long getModifier() {
        return calculateModifier();
    }

    public AttributeType getType() {
        return type;
    }

    public long getValue() {
        return value + tempValueBonus;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public CharacterAttributeDetails setId(Long id) {
        this.id = id;
        return this;
    }

    public long getTempValueBonus() {
        return tempValueBonus;
    }

    public long getTempModifierBonus() {
        return tempModifierBonus;
    }

    public void resetTempValueBonus() {
        this.tempValueBonus = 0;
    }

    public void resetTempModifierBonus() {
        this.tempModifierBonus = 0;
    }
}
