package com.mendor71.pathfinder.common.attributes;


import com.mendor71.pathfinder.common.types.AttributeType;

import java.util.*;

public class CharacterAttribute implements IAttributeNotifier {

    private Long id;
    private AttributeType type;
    private long value;
    private long stableValueBonus;
    private long tempValueBonus;
    private long tempModifierBonus;

    private Set<IAttributeListener> listenersList = new HashSet<>();

    public CharacterAttribute() {
    }

    public CharacterAttribute(AttributeType type, long value) {
        this.type = type;
        this.value = value;
        calculateModifier();
    }

    public Long getId() {
        return id == null ? -1 : id;
    }

    public CharacterAttribute setId(Long id) {
        this.id = id;
        return this;
    }

    public void setType(AttributeType type) {
        this.type = type;
    }

    @Override
    public void addListener(IAttributeListener listener) {
        listenersList.add(listener);
        notifyListeners();
    }
    @Override
    public void notifyListeners() {
        listenersList.forEach(v -> v.update(value + tempValueBonus + stableValueBonus, calculateModifier()));
    }

    private void changeValue(long addToValue, long addToTmpBonus, long addToStableBonus) {
        value += addToValue;
        tempValueBonus += addToTmpBonus;
        addToStableBonus += addToStableBonus;
        notifyListeners();
    }

    private void changeModifierValue(long tmpBonus) {
        tempModifierBonus += tmpBonus;
        notifyListeners();
    }

    public long increaseValue(long addValue) {
        changeValue(addValue, 0, 0);
        return value;
    }

    public long increaseStableValueBonus(long value) {
        changeValue(0, 0, value);
        return stableValueBonus;
    }

    public long decreaseValue(long addValue) {
        changeValue(-addValue, 0, 0);
        return value;
    }

    public long decreaseStableValueBonus(long value) {
        changeValue(0, 0, -value);
        return stableValueBonus;
    }

    private long calculateModifier() {
        long modifier = (value + tempValueBonus + stableValueBonus - 10) / 2;
        if (tempModifierBonus != 0)
            modifier += tempModifierBonus;
        return modifier;
    }

    public void increaseTempValueBonus(long value) {
        changeValue(0, value, 0);
    }

    public void decreaseTempValueBonus(long value) {
        changeValue(0, -value, 0);
    }

    public void increaseTempModifierBonus(long value) {
        changeModifierValue(value);
    }

    public void decreaseTempModifierBonus(long value) {
        changeModifierValue(-value);
    }

    public long getModifier() {
        return calculateModifier();
    }

    public AttributeType getType() {
        return type;
    }

    public long getValue() {
        return value + tempValueBonus + stableValueBonus;
    }

    public void setValue(long value) {
        this.value = value;
        notifyListeners();
    }

    public void setTempValueBonus(long value) {
        this.tempValueBonus = value;
        notifyListeners();
    }

    public long getStableValueBonus() {
        return stableValueBonus;
    }

    public void setStableValueBonus(long stableBonus) {
        this.stableValueBonus = stableBonus;
        notifyListeners();
    }

    public long getTempValueBonus() {
        return tempValueBonus;
    }

    public void setTempModifierBonus(long value) {
        this.tempModifierBonus = value;
        notifyListeners();
    }

    public long getTempModifierBonus() {
        return tempModifierBonus;
    }

    public void resetTempValueBonus() {
        this.tempValueBonus = 0;
        notifyListeners();
    }

    public void resetTempModifierBonus() {
        this.tempModifierBonus = 0;
        notifyListeners();
    }
}
