package com.mendor;

import com.mendor.types.ClassType;
import com.mendor.types.SkillType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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
        fighter.characterClassSkills.add(simpleSkillProvider.getSkillByType(SkillType.Climb));
        fighter.characterClassSkills.add(simpleSkillProvider.getSkillByType(SkillType.Craft));
        fighter.characterClassSkills.add(simpleSkillProvider.getSkillByType(SkillType.HandleAnimal));
        fighter.characterClassSkills.add(simpleSkillProvider.getSkillByType(SkillType.Intimidate));
        fighter.characterClassSkills.add(simpleSkillProvider.getSkillByType(SkillType.KnowledgeDungeoneering));
        fighter.characterClassSkills.add(simpleSkillProvider.getSkillByType(SkillType.KnowledgeEngineering));
        fighter.characterClassSkills.add(simpleSkillProvider.getSkillByType(SkillType.Profession));
        fighter.characterClassSkills.add(simpleSkillProvider.getSkillByType(SkillType.Ride));
        fighter.characterClassSkills.add(simpleSkillProvider.getSkillByType(SkillType.Survival));
        fighter.characterClassSkills.add(simpleSkillProvider.getSkillByType(SkillType.Swim));

        CharacterClass paladin = new CharacterClass();
        paladin.classType = ClassType.PALADIN;
        paladin.hitDice = "1d10";
        paladin.minHitDiceValue = 1;
        paladin.maxHitDiceValue = 10;
        paladin.skillPointsByLevel = 2;
        paladin.characterClassSkills.add(simpleSkillProvider.getSkillByType(SkillType.Craft));
        paladin.characterClassSkills.add(simpleSkillProvider.getSkillByType(SkillType.Diplomacy));
        paladin.characterClassSkills.add(simpleSkillProvider.getSkillByType(SkillType.HandleAnimal));
        paladin.characterClassSkills.add(simpleSkillProvider.getSkillByType(SkillType.Heal));
        paladin.characterClassSkills.add(simpleSkillProvider.getSkillByType(SkillType.KnowledgeNobility));
        paladin.characterClassSkills.add(simpleSkillProvider.getSkillByType(SkillType.KnowledgeReligion));
        paladin.characterClassSkills.add(simpleSkillProvider.getSkillByType(SkillType.Profession));
        paladin.characterClassSkills.add(simpleSkillProvider.getSkillByType(SkillType.Ride));
        paladin.characterClassSkills.add(simpleSkillProvider.getSkillByType(SkillType.SenseMotive));
        paladin.characterClassSkills.add(simpleSkillProvider.getSkillByType(SkillType.Spellcraft));

        CharacterClass ranger = new CharacterClass();
        ranger.classType = ClassType.RANGER;
        ranger.hitDice = "1d10";
        ranger.minHitDiceValue = 1;
        ranger.maxHitDiceValue = 10;
        ranger.skillPointsByLevel = 6;
        ranger.characterClassSkills.add(simpleSkillProvider.getSkillByType(SkillType.Climb));
        ranger.characterClassSkills.add(simpleSkillProvider.getSkillByType(SkillType.Craft));
        ranger.characterClassSkills.add(simpleSkillProvider.getSkillByType(SkillType.HandleAnimal));
        ranger.characterClassSkills.add(simpleSkillProvider.getSkillByType(SkillType.Heal));
        ranger.characterClassSkills.add(simpleSkillProvider.getSkillByType(SkillType.Intimidate));
        ranger.characterClassSkills.add(simpleSkillProvider.getSkillByType(SkillType.KnowledgeDungeoneering));
        ranger.characterClassSkills.add(simpleSkillProvider.getSkillByType(SkillType.KnowledgeGeography));
        ranger.characterClassSkills.add(simpleSkillProvider.getSkillByType(SkillType.KnowledgeNature));
        ranger.characterClassSkills.add(simpleSkillProvider.getSkillByType(SkillType.Perception));
        ranger.characterClassSkills.add(simpleSkillProvider.getSkillByType(SkillType.Profession));
        ranger.characterClassSkills.add(simpleSkillProvider.getSkillByType(SkillType.Ride));
        ranger.characterClassSkills.add(simpleSkillProvider.getSkillByType(SkillType.Spellcraft));
        ranger.characterClassSkills.add(simpleSkillProvider.getSkillByType(SkillType.Stealth));
        ranger.characterClassSkills.add(simpleSkillProvider.getSkillByType(SkillType.Survival));
        ranger.characterClassSkills.add(simpleSkillProvider.getSkillByType(SkillType.Swim));

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

    private HashSet<CharacterSkill> characterClassSkills = new HashSet<>();

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
    public Set<CharacterSkill> getClassSkills() {
        return characterClassSkills;
    }
}
