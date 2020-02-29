package com.mendor71.pathfinder.common.skills;

import com.mendor71.pathfinder.common.attributes.IAttributeListener;
import com.mendor71.pathfinder.common.types.AttributeType;
import com.mendor71.pathfinder.common.types.SkillType;
import com.mendor71.pathfinder.common.PathfinderCharacter;

public class CharacterSkillDetails implements IAttributeListener {
    private long id;

    private CharacterSkill skill;
    private PathfinderCharacter character;

    private long trainedPoints;
    private long modifier;
    private long bonus;

    private long temporaryBonus = 0;

    public CharacterSkillDetails() {
    }

    public CharacterSkillDetails(CharacterSkill skill) {
        this.skill = skill;
    }

    public CharacterSkillDetails(CharacterSkill skill, long trainedPoints) {
        this.skill = skill;
        this.trainedPoints = trainedPoints;
    }

    public CharacterSkillDetails(PathfinderCharacter character, CharacterSkill skill) {
        this.skill = skill;
        this.character = character;
        this.trainedPoints = 0;
    }

    public CharacterSkillDetails(PathfinderCharacter character, CharacterSkill skill, long trainedPoints) {
        this.skill = skill;
        this.trainedPoints = trainedPoints;
        this.character = character;
    }

    public void increaseBonus(long bonus) {
        this.bonus += bonus;
    }

    public long increaseValue(long bonus, boolean classSkill) {
        if (this.trainedPoints == 0 && classSkill)
            bonus += 3;
        return this.trainedPoints += bonus;
    }

    public void increaseTemporaryBonus(long bonus) {
        this.temporaryBonus += bonus;
    }

    public long decreaseValue(long minus) {
        return this.trainedPoints -= minus;
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

    public long getTrainedPoints() {
        return trainedPoints;
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

    public void setTrainedPoints(long value) {
        this.trainedPoints = value;
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

    public long getSkillValue() {
        return modifier + trainedPoints + bonus + temporaryBonus;
    }

    public long getTemporaryBonus() {
        return temporaryBonus;
    }

    public void setTemporaryBonus(long temporaryBonus) {
        this.temporaryBonus = temporaryBonus;
    }

    public void resetTemporaryBonus() {
        this.temporaryBonus = 0;
    }

    @Override
    public void update(long normalValue, long modifier) {
        this.modifier = modifier;
    }
}
