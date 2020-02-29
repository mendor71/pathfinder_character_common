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
    private long attributeModifier;
    private long stableBonus;
    private long classBonus;

    private long temporarySkillValueModifier = 0;

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

    public long increaseStableBonus(long value) {
        return this.stableBonus += value;
    }

    public long decreaseStableBonus(long value) {
        return this.stableBonus -= value;
    }

    public long increaseTrainedPoints(long value, boolean classSkill) {
        if (this.trainedPoints == 0 && classSkill && !isClassBonusUsed())
            applyClassBonus();
        return this.trainedPoints += value;
    }

    public long increaseTemporarySkillModifier(long value) {
        return this.temporarySkillValueModifier += value;
    }

    public long decreaseTemporarySkillModifier(long value) {
        return this.temporarySkillValueModifier -= value;
    }

    public void resetTemporarySkillModifier() {
        this.temporarySkillValueModifier = 0;
    }

    public long getSummaryValue() {
        return trainedPoints + stableBonus + attributeModifier + temporarySkillValueModifier + classBonus;
    }

    @Override
    public void update(long normalValue, long modifier) {
        this.attributeModifier = modifier;
    }

    /** GETTERS AND SETTERS */

    public long getId() {
        return id;
    }

    public CharacterSkill getSkill() {
        return skill;
    }

    public PathfinderCharacter getCharacter() {
        return character;
    }

    public long getTrainedPoints() {
        return trainedPoints;
    }

    public long getAttributeModifier() {
        return attributeModifier;
    }

    public long getStableBonus() {
        return stableBonus;
    }

    public long getTemporarySkillValueModifier() {
        return temporarySkillValueModifier;
    }

    public SkillType getSkillType() {
        return this.skill.getSkillType();
    }

    public AttributeType getAttributeType() {
        return this.skill.getAttributeType();
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setSkill(CharacterSkill skill) {
        this.skill = skill;
    }

    public void setCharacter(PathfinderCharacter character) {
        this.character = character;
    }

    public void setTrainedPoints(long trainedPoints) {
        this.trainedPoints = trainedPoints;
    }

    public void setAttributeModifier(long attributeModifier) {
        this.attributeModifier = attributeModifier;
    }

    public void setStableBonus(long stableBonus) {
        this.stableBonus = stableBonus;
    }

    public void setTemporarySkillValueModifier(long temporarySkillValueModifier) {
        this.temporarySkillValueModifier = temporarySkillValueModifier;
    }

    public boolean isClassBonusUsed() {
        return classBonus != 0;
    }

    public void applyClassBonus() {
        classBonus = 3;
    }
}
