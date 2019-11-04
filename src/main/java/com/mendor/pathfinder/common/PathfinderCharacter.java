package com.mendor.pathfinder.common;

import com.mendor.pathfinder.common.attributes.CharacterAttributeDetails;
import com.mendor.pathfinder.common.attributes.ICharacterAttributeManager;
import com.mendor.pathfinder.common.exceptions.NotEnoughSkillPointsException;
import com.mendor.pathfinder.common.inventory.IInventoryItem;
import com.mendor.pathfinder.common.pathfinderclasses.CharacterClassDetails;
import com.mendor.pathfinder.common.pathfinderclasses.ICharacterClass;
import com.mendor.pathfinder.common.pathfinderclasses.ICharacterClassManager;
import com.mendor.pathfinder.common.races.IRace;
import com.mendor.pathfinder.common.skills.CharacterSkillDetails;
import com.mendor.pathfinder.common.skills.ICharacterSkillManager;
import com.mendor.pathfinder.common.types.AttributeType;
import com.mendor.pathfinder.common.types.SkillType;

import java.awt.*;
import java.util.*;
import java.util.List;

public class PathfinderCharacter  {
    private String uuid;

    private IDamageProvider damageProvider;
    private ISpellProvider spellProvider;

    private ICharacterClassManager classManager;
    private ICharacterAttributeManager attributeManager;
    private ICharacterSkillManager skillManager;

    private IRace race;
    private List<IInventoryItem> IInventoryItems = new ArrayList<>();
    private long armorClass;
    private long level;
    private String name;
    private long hitPointsMax;
    private long hitPointsCurrent;
    private long age;
    private long height;
    private long weight;
    private Color hairColor;
    private Color eyeColor;

    public PathfinderCharacter() {
        this.uuid = UUID.randomUUID().toString();
    }

    public PathfinderCharacter(
            IRace race
            , ICharacterClassManager classManager
            , ICharacterAttributeManager attributeManager
            , ICharacterSkillManager skillManager
            , Set<CharacterClassDetails> characterClassDetails
            , Set<CharacterAttributeDetails> characterAttributeDetails) {

        this.race = race;
        this.attributeManager = attributeManager;
        this.classManager = classManager;
        this.skillManager = skillManager;

        classManager.setOnControl(this, characterClassDetails);
        attributeManager.setOnControl(this, characterAttributeDetails);
        skillManager.setCharacterBaseSkillPoints(this);
        skillManager.setOnControlClassSkills(this);

        this.level = classManager.getCharacterSummaryLevel(this);
        setStartHitPoints();
    }

    public void takeWeapon(IDamageProvider damageProvider) {
        this.damageProvider = damageProvider;
        damageProvider.setOwner(this);
    }

    public void addAttributeSkillListener(AttributeType attributeType, SkillType skillType) {
        if (this.attributeManager == null)
            throw new IllegalStateException("attributes manager not set!");

        if (this.skillManager == null)
            throw new IllegalStateException("skill manager not set!");

        CharacterSkillDetails skillDetails =  this.skillManager.getCharacterSkillDetails(this, skillType);
        this.attributeManager.getAttributeDetails(this, attributeType).addListener(skillDetails);
    }

    public long increaseSkillPoints(SkillType type, long value) throws NotEnoughSkillPointsException {
        if (this.getSkillPoints(type) == 0 && this.classManager.isClassSkill(this, type)) {
            skillManager.increaseClassSkillPoints(this, type);
        }
        return skillManager.increaseSkillPoints(this, type, value);
    }

    public void addInventoryItem(IInventoryItem IInventoryItems) {
        this.IInventoryItems.add(IInventoryItems);
    }

    public void heal(long value) {
        if (hitPointsCurrent + value < hitPointsMax)
            hitPointsCurrent += value;
        else
            hitPointsCurrent = hitPointsMax;
    }

    public void damage(DamageInstance damageInstance) {
        long resultDamageValue = saveRoll(damageInstance);
        hitPointsCurrent -= resultDamageValue;
    }

    public long saveRoll(DamageInstance damageInstance) {
        return damageInstance.getDamageValue();
    }

    public void setStartHitPoints() {
        Set<CharacterClassDetails> characterClassList = classManager.getCharacterClasses(this);

        if (characterClassList.size() == 0) {
            throw new IllegalStateException("Character class list must contains elements");
        }

        if (this.hitPointsMax == 0) {
            int addToHitPoints = 0;

            addToHitPoints += characterClassList.stream().map(e -> {
                long addVal = e.getCharacterClass().getMaxHitDiceValue();

                for (int i = 0; i < e.getLevel() - 1; i++) {
                    addVal += e.getCharacterClass().rollHitPointsBonus(e.getLevel());
                }
                return addVal;
            }).reduce(0L, (a, b) -> (a + b));

            long enduranceModifier = attributeManager.getAttributeModifier(this, AttributeType.ENDURANCE );
            if (enduranceModifier >= 0)
                addToHitPoints += enduranceModifier * level;

            this.hitPointsMax = addToHitPoints;
            this.hitPointsCurrent = addToHitPoints;
        }
    }

    /**
     * @param value increase hit points on received value
     * @return new max hit points
     */
    public long increaseMaxHitPoints(long value) {
        return this.hitPointsMax += value;
    }

    /**
     * @return new max hit points
     * @apiNote increase hit points at class hit dice + endurance modifier value
     */
    public long increaseMaxHitPoints(ICharacterClass characterClass) {
        if (!classManager.containsCharacterClass(this, characterClass))
            throw new IllegalStateException("characterClass must be not null!");

        return increaseMaxHitPoints(characterClass.rollHitPointsBonus(this.level));
    }

    public void levelUpAtClass(ICharacterClass characterClass) {
        this.level += 1;
        classManager.levelUpAtClass(this, characterClass);
        skillManager.increaseFreeSkillPoints(this, characterClass.getSkillPointsByLevel());

        increaseMaxHitPoints(characterClass);

    }

    public PathfinderCharacter setDamageProvider(IDamageProvider damageProvider) {
        this.damageProvider = damageProvider;
        return this;
    }

    public PathfinderCharacter setSpellProvider(ISpellProvider spellProvider) {
        this.spellProvider = spellProvider;
        return this;
    }

    public PathfinderCharacter setArmorClass(long armorClass) {
        this.armorClass = armorClass;
        return this;
    }

    public PathfinderCharacter setName(String name) {
        this.name = name;
        return this;
    }

    public PathfinderCharacter setHitPointsMax(long hitPointsMax) {
        this.hitPointsMax = hitPointsMax;
        return this;
    }

    public PathfinderCharacter setHitPointsCurrent(long hitPointsCurrent) {
        this.hitPointsCurrent = hitPointsCurrent;
        return this;
    }

    public PathfinderCharacter setAge(long age) {
        this.age = age;
        return this;
    }

    public PathfinderCharacter setHeight(long height) {
        this.height = height;
        return this;
    }

    public PathfinderCharacter setWeight(long weight) {
        this.weight = weight;
        return this;
    }

    public PathfinderCharacter setHairColor(Color hairColor) {
        this.hairColor = hairColor;
        return this;
    }

    public PathfinderCharacter setEyeColor(Color eyeColor) {
        this.eyeColor = eyeColor;
        return this;
    }

    public void setLevel(long level) {
        this.level = level;
    }

    public void setRace(IRace race) {
        this.race = race;
    }

    public void setClassManager(ICharacterClassManager classManager) {
        this.classManager = classManager;
    }

    public void setAttributeManager(ICharacterAttributeManager attributeManager) {
        this.attributeManager = attributeManager;
    }

    public void setSkillManager(ICharacterSkillManager skillManager) {
        this.skillManager = skillManager;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setAttributes(Set<CharacterAttributeDetails> details) {
        if (this.attributeManager == null)
            throw new IllegalStateException("attributes manager not set!");

        this.attributeManager.setOnControl(this, details);
    }

    public void setClasses(Set<CharacterClassDetails> details) {
        if (this.classManager == null)
            throw new IllegalStateException("classes manager not set!");

        this.classManager.setOnControl(this, details);
    }

    public void setSkillSet(Set<CharacterSkillDetails> details) {
        if (this.skillManager == null)
            throw new IllegalStateException("skills manager not set!");

        this.skillManager.setOnControl(this, details);
    }

    public void setFreeAndUsedSkillPoints(long free, long used) {
        if (this.skillManager == null)
            throw new IllegalStateException("skills manager not set!");

        this.skillManager.setFreeAndUsedSkillPoints(this, free, used);
    }

    public IDamageProvider getDamageProvider() {
        return damageProvider;
    }

    public ISpellProvider getSpellProvider() {
        return spellProvider;
    }

    public IRace getRace() {
        return race;
    }

    public Long getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public List<IInventoryItem> getInventoryItems() {
        return IInventoryItems;
    }

    public Long getAge() {
        return age;
    }

    public Long getHeight() {
        return height;
    }

    public Long getWeight() {
        return weight;
    }

    public Color getHairColor() {
        return hairColor;
    }

    public Color getEyeColor() {
        return eyeColor;
    }

    public Long getArmorClass() {
        return armorClass;
    }

    public Long getHitPointsMax() {
        return hitPointsMax;
    }

    public Long getHitPointsCurrent() {
        return hitPointsCurrent;
    }

    public long getSkillPointsProvidedByClasses() {
        if (this.getClassManager() == null)
            throw new IllegalStateException("character class manager not initialized");

        return classManager.getSummaryCharacterSkillPointsProvidedByClasses(this);
    }

    public long getFreeSkillPoints() {
        if (this.getSkillManager() == null)
            throw new IllegalStateException("character skill manager not initialized");

        return skillManager.getFreeSkillPoints(this);
    }

    public long getUsedSkillPoints() {
        if (this.getSkillManager() == null)
            throw new IllegalStateException("character skill manager not initialized");

        return skillManager.getUsedSkillPoints(this);
    }

    public Set<CharacterClassDetails> getCharacterClasses() {
        return classManager.getCharacterClasses(this);
    }

    public Set<CharacterAttributeDetails> getAttributes() {
        return attributeManager.getCharacterAttributes(this);
    }

    public Set<CharacterSkillDetails> getSkillSet() {
        return skillManager.getCharacterSkillDetailsSet(this);
    }

    public ICharacterClassManager getClassManager() {
        return classManager;
    }

    public ICharacterAttributeManager getAttributeManager() {
        return attributeManager;
    }

    public long getAttributeModifier(AttributeType type) {
        return this.getAttributeManager().getAttributeModifier(this, type);
    }

    public ICharacterSkillManager getSkillManager() {
        return skillManager;
    }

    public Long getSkillPoints(SkillType type) {
        return skillManager.getSkillPoints(this, type);
    }

    public String getUUID() {
        return this.uuid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PathfinderCharacter))
            return false;

        PathfinderCharacter that = (PathfinderCharacter) obj;
        return that.getUUID().equals(this.getUUID());
    }
}
