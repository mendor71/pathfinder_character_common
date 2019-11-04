package com.mendor71.pathfinder.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mendor71.pathfinder.common.attributes.CharacterAttributeDetails;
import com.mendor71.pathfinder.common.attributes.IAttributeManager;
import com.mendor71.pathfinder.common.exceptions.NotEnoughSkillPointsException;
import com.mendor71.pathfinder.common.pathfinderclasses.CharacterClassDetails;
import com.mendor71.pathfinder.common.pathfinderclasses.ICharacterClassManager;
import com.mendor71.pathfinder.common.pathfinderclasses.IClassManager;
import com.mendor71.pathfinder.common.races.IRace;
import com.mendor71.pathfinder.common.skills.CharacterSkillDetails;
import com.mendor71.pathfinder.common.skills.ICharacterSkillManager;
import com.mendor71.pathfinder.common.skills.ISkillManager;
import com.mendor71.pathfinder.common.types.AttributeType;
import com.mendor71.pathfinder.common.types.ClassType;
import com.mendor71.pathfinder.common.types.SkillType;

import java.awt.*;
import java.util.Set;

public class Character implements ICharacter {
    @JsonIgnore private IAttributeManager attributeManager;
    @JsonIgnore private IClassManager classManager;
    @JsonIgnore private ISkillManager skillManager;

    public static Builder newBuilder() {
        return new Character().new Builder();
    }

    private CharacterBase characterBase;

    private Character() {
    }

    public String getUuid() {
        return characterBase.getUuid();
    }

    public IRace getRace() {
        return characterBase.getRace();
    }

    public long getArmorClass() {
        return characterBase.getArmorClass();
    }

    public long getLevel() {
        return characterBase.getLevel();
    }

    public String getName() {
        return characterBase.getName();
    }

    public long getHitPointsMax() {
        return characterBase.getHitPointsMax();
    }

    public long getHitPointsCurrent() {
        return characterBase.getHitPointsCurrent();
    }

    public long getAge() {
        return characterBase.getAge();
    }

    public long getHeight() {
        return characterBase.getHeight();
    }

    public long getWeight() {
        return characterBase.getWeight();
    }

    public Color getHairColor() {
        return characterBase.getHairColor();
    }

    public Color getEyeColor() {
        return characterBase.getEyeColor();
    }

    public void setCharacterBase(CharacterBase characterBase) {
        this.characterBase = characterBase;
    }

    /** ATTRIBUTES MANAGER */

    @Override
    public long getAttributeValue(AttributeType type) throws IllegalStateException {
        return attributeManager.getAttributeValue(type);
    }

    @Override
    public long setAttributeValue(AttributeType type, long value) {
        return attributeManager.setAttributeValue(type, value);
    }

    @Override
    public long getAttributeModifier(AttributeType type) throws IllegalStateException {
        return attributeManager.getAttributeModifier(type);
    }

    @Override
    public long increaseAttributeValue(AttributeType type, long bonus) {
        return attributeManager.increaseAttributeValue(type, bonus);
    }

    @Override
    public long decreaseAttributeValue(AttributeType type, long minus) {
        return attributeManager.decreaseAttributeValue(type, minus);
    }

    @Override
    public Set<CharacterAttributeDetails> getCharacterAttributes() {
        return attributeManager.getCharacterAttributes();
    }

    @Override
    public void setAttributesOnControl(Set<CharacterAttributeDetails> controlObjects) {
        attributeManager.setAttributesOnControl(controlObjects);
    }

    @Override
    public CharacterAttributeDetails getAttributeDetails(AttributeType type) {
        return attributeManager.getAttributeDetails(type);
    }

    @Override
    public CharacterAttributeDetails getAttributeByTypeOrThrowException(AttributeType type) throws IllegalStateException {
        return attributeManager.getAttributeByTypeOrThrowException(type);
    }

    /** CLASSES MANAGER */

    @Override
    public Set<CharacterClassDetails> getCharacterClasses() {
        return classManager.getCharacterClasses();
    }

    @Override
    public CharacterClassDetails getCharacterClassDetails(ClassType type) {
        return classManager.getCharacterClassDetails(type);
    }

    @Override
    public boolean containsCharacterClass(ClassType type) {
        return classManager.containsCharacterClass(type);
    }

    @Override
    public void levelUpAtClass(ClassType type) {
        classManager.levelUpAtClass(type);
    }

    @Override
    public void addNewClassDetails(CharacterClassDetails classDetails) {
        classManager.addNewClassDetails(classDetails);
    }

    @Override
    public long getCharacterSummaryLevel() {
        return classManager.getCharacterSummaryLevel();
    }

    @Override
    public long getSummaryCharacterSkillPointsProvidedByClasses() {
        return classManager.getSummaryCharacterSkillPointsProvidedByClasses();
    }

    @Override
    public void setClassesOnControl(Set<CharacterClassDetails> characterClassDetails) {
        classManager.setClassesOnControl(characterClassDetails);
    }

    @Override
    public boolean isClassSkill(SkillType skillType) {
        return classManager.isClassSkill(skillType);
    }

    @Override
    public Set<SkillType> getClassSkills() {
        return classManager.getClassSkills();
    }

    @Override
    public CharacterClassDetails getClassDetailsByTypeOrThrowException(ClassType type) throws IllegalStateException {
        return classManager.getClassDetailsByTypeOrThrowException(type);
    }

    /** SKILL MANAGER */

    @Override
    public void setClassSkills(Set<SkillType> classSkills) {
        skillManager.setClassSkills(classSkills);
    }

    @Override
    public void setSkillsOnControl(Set<CharacterSkillDetails> skillDetails) {
        skillManager.setSkillsOnControl(skillDetails);
    }

    @Override
    public CharacterSkillDetails getCharacterSkillDetails(SkillType type) {
        return skillManager.getCharacterSkillDetails(type);
    }

    @Override
    public Set<CharacterSkillDetails> getCharacterSkillDetailsSet() {
        return skillManager.getCharacterSkillDetailsSet();
    }

    @Override
    public boolean containsCharacterSkill(SkillType type) {
        return skillManager.containsCharacterSkill(type);
    }

    @Override
    public long increaseSkillPoints(SkillType type, long value) throws NotEnoughSkillPointsException {
        return skillManager.increaseSkillPoints(type, value);
    }

    @Override
    public long getUsedSkillPointsBySkill(SkillType type) {
        return skillManager.getUsedSkillPointsBySkill(type);
    }

    @Override
    public long setSumSkillPoints(long value) {
        return skillManager.setSumSkillPoints(value);
    }

    @Override
    public long getFreeSkillPoints() {
        return skillManager.getFreeSkillPoints();
    }

    @Override
    public long getUsedSkillPoints() {
        return skillManager.getUsedSkillPoints();
    }

    @Override
    public long getSkillValue(SkillType type) {
        return skillManager.getSkillValue(type);
    }

    @Override
    public long getSkillTrainedPoints(SkillType type) {
        return skillManager.getSkillTrainedPoints(type);
    }

    @Override
    public long increaseFreeSkillPoints(long value) {
        return skillManager.increaseFreeSkillPoints(value);
    }

    @Override
    public CharacterSkillDetails getCharacterSkillDetailsByTypeOrThrowException(SkillType type) throws IllegalStateException {
        return skillManager.getCharacterSkillDetailsByTypeOrThrowException(type);
    }

    public class Builder {
        private Builder() {
        }

        public Builder manageAttributes(IAttributeManager attributeManager, Set<CharacterAttributeDetails> attributeDetails) {
            Character.this.attributeManager = attributeManager;
            Character.this.attributeManager.setAttributesOnControl(attributeDetails);
            return this;
        }

        public Builder manageClasses(IClassManager classManager, Set<CharacterClassDetails> classDetails) {
            Character.this.classManager = classManager;
            Character.this.classManager.setClassesOnControl(classDetails);
            return this;
        }

        public Builder manageSkills(ISkillManager skillManager, Set<CharacterSkillDetails> skillDetails) {
            Character.this.skillManager = skillManager;
            Character.this.skillManager.setSkillsOnControl(skillDetails);
            return this;
        }

        public Builder setCharacterBase(CharacterBase characterBase) {
            Character.this.characterBase = characterBase;
            return this;
        }

        public Character build() {
            if (Character.this.characterBase == null) throw new IllegalStateException("Character base must be set!");
            if (Character.this.classManager == null) throw new IllegalStateException("Character classManager must be set!");
            if (Character.this.attributeManager == null) throw new IllegalStateException("Character attributeManager must be set!");

            characterBase.setLevel(classManager.getCharacterSummaryLevel());
            skillManager.setSumSkillPoints(classManager.getSummaryCharacterSkillPointsProvidedByClasses());
            skillManager.setClassSkills(classManager.getClassSkills());

            skillManager.getCharacterSkillDetailsSet().forEach( skill -> {
                attributeManager.getAttributeByTypeOrThrowException(skill.getAttributeType()).addListener(skill);
            });

            return Character.this;
        }
    }
}
