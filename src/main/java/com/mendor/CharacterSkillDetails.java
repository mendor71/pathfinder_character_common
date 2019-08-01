package com.mendor;

import com.mendor.types.AttributeType;
import com.mendor.types.SkillType;

public class CharacterSkillDetails {
    private long id;

    private CharacterSkill skill;
    private long value;
    private long modifier;
    private long bonus;

    public CharacterSkillDetails(CharacterSkill skill, long value) {
        this.skill = skill;
        this.value = value;
    }

    public long increaseValue(long bonus) {
        return this.value += bonus;
    }

    public long dncreaseValue(long minus) {
        return this.value -= minus;
    }

    public long getId() {
        return id;
    }

    public CharacterSkill getSkill() {
        return skill;
    }

    public SkillType getSkillType() {
        return skill.getSkillType();
    }

    public AttributeType getAttributeType() {
        return skill.getAttributeType();
    }

    public long getValue() {
        return value;
    }

    public long getModifier() {
        return modifier;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setSkill(CharacterSkill skill) {
        this.skill = skill;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public void setModifier(long modifier) {
        this.modifier = modifier;
    }

    public void setBonus(long bonus) {
        this.bonus = bonus;
    }

    public long getBonus() {
        return bonus;
    }
}
