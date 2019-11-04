package com.mendor71.pathfinder.common.skills;

import com.mendor71.pathfinder.common.attributes.IAttributeListener;
import com.mendor71.pathfinder.common.types.AttributeType;
import com.mendor71.pathfinder.common.types.SkillType;
import com.mendor71.pathfinder.common.PathfinderCharacter;

public class CharacterSkillDetails implements IAttributeListener {
    private long id;

    private CharacterSkill skill;
    private PathfinderCharacter character;

    private long value;
    private long modifier;
    private long bonus;

    public CharacterSkillDetails() {
    }

    public CharacterSkillDetails(PathfinderCharacter character, CharacterSkill skill) {
        this.skill = skill;
        this.character = character;
        this.value = 0;
    }

    public CharacterSkillDetails(PathfinderCharacter character, CharacterSkill skill, long value) {
        this.skill = skill;
        this.value = value;
        this.character = character;
    }

    public long increaseValue(long bonus) {
        return this.value += bonus;
    }

    public long decreaseValue(long minus) {
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

    public PathfinderCharacter getCharacter() {
        return character;
    }

    public void setCharacter(PathfinderCharacter character) {
        this.character = character;
    }

    @Override
    public void update(long normalValue, long modifier) {
        this.modifier = modifier;
    }
}
