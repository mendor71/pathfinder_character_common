package com.mendor71.pathfinder.common.attributes;


import com.mendor71.pathfinder.common.types.AttributeType;

import java.util.*;

public class CharacterAttributeDetails implements IAttributeNotifier {

    private Long id;
    private AttributeType type;
    private long value;
    //private long modifier;
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
        notifyListeners();
    }
    @Override
    public void notifyListeners() {
        listenersList.forEach(v -> v.update(value+tempValueBonus, calculateModifier()));
    }

    private void changeValue(long addToValue, long addToTmpBonus) {
        value += addToValue;
        tempValueBonus += addToTmpBonus;
        notifyListeners();
    }

    private void changeModifierValue(long tmpBonus) {
        tempModifierBonus += tmpBonus;
        notifyListeners();
    }

    public long increaseValue(long addValue) {
        changeValue(addValue, 0);
        return value;
    }

    public long decreaseValue(long addValue) {
        changeValue(-addValue, 0);
        return value;
    }

    private long calculateModifier() {
        long modifier = (value + tempValueBonus - 10) / 2;
        if (tempModifierBonus != 0)
            modifier += tempModifierBonus;
        return modifier;
    }

    public void setTempValueBonus(long value) {
        this.tempValueBonus = value;
        notifyListeners();
    }

    public void setTempModifierBonus(long value) {
        this.tempModifierBonus = value;
        notifyListeners();
    }

    public void increaseTempValueBonus(long value) {
        changeValue(0, value);
    }

    public void decreaseTempValueBonus(long value) {
        changeValue(0, -value);
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
        return value + tempValueBonus;
    }

    public void setValue(long value) {
        this.value = value;
        notifyListeners();
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
        notifyListeners();
    }

    public void resetTempModifierBonus() {
        this.tempModifierBonus = 0;
        notifyListeners();
    }
}
