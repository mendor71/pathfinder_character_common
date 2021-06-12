package com.mendor71.pathfinder.common.pathfinderclasses;

import com.mendor71.pathfinder.common.skills.CharacterSkill;
import com.mendor71.pathfinder.common.skills.SimpleSkillProvider;
import com.mendor71.pathfinder.common.types.ClassType;
import com.mendor71.pathfinder.common.types.SkillType;

import java.util.*;

public class CharacterClass implements ICharacterClass {

    private static HashMap<ClassType, CharacterClass> classMap = new HashMap<>();
    private static SimpleSkillProvider simpleSkillProvider = SimpleSkillProvider.getInstance();

    static {
        CharacterClass fighter = new CharacterClass();
        fighter.classType = ClassType.FIGHTER;
        fighter.hitDice = "1d12";
        fighter.minHitDiceValue = 1;
        fighter.maxHitDiceValue = 12;
        fighter.skillPointsByLevel = 2;
        fighter.characterClassSkills.add(SkillType.Climb);
        fighter.characterClassSkills.add(SkillType.Craft);
        fighter.characterClassSkills.add(SkillType.HandleAnimal);
        fighter.characterClassSkills.add(SkillType.Intimidate);
        fighter.characterClassSkills.add(SkillType.KnowledgeDungeoneering);
        fighter.characterClassSkills.add(SkillType.KnowledgeEngineering);
        fighter.characterClassSkills.add(SkillType.Profession);
        fighter.characterClassSkills.add(SkillType.Ride);
        fighter.characterClassSkills.add(SkillType.Survival);
        fighter.characterClassSkills.add(SkillType.Swim);

        CharacterClass paladin = new CharacterClass();
        paladin.classType = ClassType.PALADIN;
        paladin.hitDice = "1d10";
        paladin.minHitDiceValue = 1;
        paladin.maxHitDiceValue = 10;
        paladin.skillPointsByLevel = 2;
        paladin.characterClassSkills.add(SkillType.Craft);
        paladin.characterClassSkills.add(SkillType.Diplomacy);
        paladin.characterClassSkills.add(SkillType.HandleAnimal);
        paladin.characterClassSkills.add(SkillType.Heal);
        paladin.characterClassSkills.add(SkillType.KnowledgeNobility);
        paladin.characterClassSkills.add(SkillType.KnowledgeReligion);
        paladin.characterClassSkills.add(SkillType.Profession);
        paladin.characterClassSkills.add(SkillType.Ride);
        paladin.characterClassSkills.add(SkillType.SenseMotive);
        paladin.characterClassSkills.add(SkillType.Spellcraft);

        CharacterClass ranger = new CharacterClass();
        ranger.classType = ClassType.RANGER;
        ranger.hitDice = "1d10";
        ranger.minHitDiceValue = 1;
        ranger.maxHitDiceValue = 10;
        ranger.skillPointsByLevel = 6;
        ranger.characterClassSkills.add(SkillType.Climb);
        ranger.characterClassSkills.add(SkillType.Craft);
        ranger.characterClassSkills.add(SkillType.HandleAnimal);
        ranger.characterClassSkills.add(SkillType.Heal);
        ranger.characterClassSkills.add(SkillType.Intimidate);
        ranger.characterClassSkills.add(SkillType.KnowledgeDungeoneering);
        ranger.characterClassSkills.add(SkillType.KnowledgeGeography);
        ranger.characterClassSkills.add(SkillType.KnowledgeNature);
        ranger.characterClassSkills.add(SkillType.Perception);
        ranger.characterClassSkills.add(SkillType.Profession);
        ranger.characterClassSkills.add(SkillType.Ride);
        ranger.characterClassSkills.add(SkillType.Spellcraft);
        ranger.characterClassSkills.add(SkillType.Stealth);
        ranger.characterClassSkills.add(SkillType.Survival);
        ranger.characterClassSkills.add(SkillType.Swim);

        classMap.put(ClassType.FIGHTER, fighter);
        classMap.put(ClassType.PALADIN, paladin);
        classMap.put(ClassType.RANGER, ranger);
    }

    private Long id;
    private String hitDice;
    private ClassType classType;
    private long maxHitDiceValue;
    private long minHitDiceValue;
    private long skillPointsByLevel;

    private EnumSet<SkillType> characterClassSkills = EnumSet.noneOf(SkillType.class);

    private CharacterClass() {}

    public static CharacterClass getInstance(ClassType type) {
        if (!classMap.containsKey(type))
            throw new IllegalArgumentException("class " + type + " is not supports");

        return classMap.get(type);
    }

    @Override
    public Long getId() {
        return id == null ? -1 : id;
    }

    @Override
    public String getName() {
        return classType.toString();
    }

    @Override
    public long getMaxHitDiceValue() {
        return maxHitDiceValue;
    }

    @Override
    public long getMinHitDiceValue() {
        return minHitDiceValue;
    }

    @Override
    public String getHitDiceName() {
        return hitDice;
    }

    @Override
    public long getSkillPointsByLevel() {
        return skillPointsByLevel;
    }

    @Override
    public Set<SkillType> getClassSkills() {
        return characterClassSkills;
    }

    @Override
    public ClassType getType() {
        return this.classType;
    }
}
