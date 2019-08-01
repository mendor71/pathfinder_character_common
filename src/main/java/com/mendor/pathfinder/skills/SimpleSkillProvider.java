package com.mendor.pathfinder.skills;

import com.mendor.pathfinder.types.AttributeType;
import com.mendor.pathfinder.types.SkillType;

import java.util.HashMap;
import java.util.Map;

public class SimpleSkillProvider implements ISkillProvider {
    private static SimpleSkillProvider instance;

    private static Map<SkillType, CharacterSkill> skillMap = new HashMap<>();

    static {
        skillMap.put(SkillType.Acrobatics, new CharacterSkill(AttributeType.AGILITY, SkillType.Acrobatics, true));
        skillMap.put(SkillType.Bluff, new CharacterSkill(AttributeType.CHARISMA, SkillType.Bluff, true));
        skillMap.put(SkillType.Ride, new CharacterSkill(AttributeType.AGILITY, SkillType.Ride, true));
        skillMap.put(SkillType.Perception, new CharacterSkill(AttributeType.WISDOM, SkillType.Perception, true));
        skillMap.put(SkillType.Survival, new CharacterSkill(AttributeType.WISDOM, SkillType.Survival, true));
        skillMap.put(SkillType.Diplomacy, new CharacterSkill(AttributeType.CHARISMA, SkillType.Diplomacy, true));
        skillMap.put(SkillType.HandleAnimal, new CharacterSkill(AttributeType.CHARISMA, SkillType.HandleAnimal, false));
        skillMap.put(SkillType.Intimidate, new CharacterSkill(AttributeType.CHARISMA, SkillType.Intimidate, true));
        skillMap.put(SkillType.KnowledgeNobility, new CharacterSkill(AttributeType.INTELLIGENCE, SkillType.KnowledgeNobility, false));
        skillMap.put(SkillType.KnowledgeGeography, new CharacterSkill(AttributeType.INTELLIGENCE, SkillType.KnowledgeNobility, false));
        skillMap.put(SkillType.KnowledgeEngineering, new CharacterSkill(AttributeType.INTELLIGENCE, SkillType.KnowledgeEngineering, false));
        skillMap.put(SkillType.KnowledgeHistory, new CharacterSkill(AttributeType.INTELLIGENCE, SkillType.KnowledgeLocal, false));
        skillMap.put(SkillType.KnowledgeLocal, new CharacterSkill(AttributeType.INTELLIGENCE, SkillType.KnowledgeLocal, false));
        skillMap.put(SkillType.KnowledgeArcana, new CharacterSkill(AttributeType.INTELLIGENCE, SkillType.KnowledgeArcana, false));
        skillMap.put(SkillType.KnowledgePlane, new CharacterSkill(AttributeType.INTELLIGENCE, SkillType.KnowledgePlane, false));
        skillMap.put(SkillType.KnowledgeDungeoneering, new CharacterSkill(AttributeType.INTELLIGENCE, SkillType.KnowledgeDungeoneering, false));
        skillMap.put(SkillType.KnowledgeNature, new CharacterSkill(AttributeType.INTELLIGENCE, SkillType.KnowledgeNature, false));
        skillMap.put(SkillType.KnowledgeReligion, new CharacterSkill(AttributeType.INTELLIGENCE, SkillType.KnowledgeReligion, false));
        skillMap.put(SkillType.EscapeArtist, new CharacterSkill(AttributeType.AGILITY, SkillType.EscapeArtist, true));
        skillMap.put(SkillType.UseMagicDevice, new CharacterSkill(AttributeType.CHARISMA, SkillType.UseMagicDevice, false));
        skillMap.put(SkillType.Spellcraft, new CharacterSkill(AttributeType.INTELLIGENCE, SkillType.Spellcraft, false));
        skillMap.put(SkillType.SenseMotive, new CharacterSkill(AttributeType.WISDOM, SkillType.SenseMotive, false));
        skillMap.put(SkillType.Climb, new CharacterSkill(AttributeType.STRENGTH, SkillType.Climb, true));
        skillMap.put(SkillType.Heal, new CharacterSkill(AttributeType.WISDOM, SkillType.Heal, true));
        skillMap.put(SkillType.SleightOfHand, new CharacterSkill(AttributeType.AGILITY, SkillType.SleightOfHand, false));
        skillMap.put(SkillType.Disguise, new CharacterSkill(AttributeType.CHARISMA, SkillType.Disguise, true));
        skillMap.put(SkillType.DisableDevice, new CharacterSkill(AttributeType.AGILITY, SkillType.DisableDevice, false));
        skillMap.put(SkillType.Appraise, new CharacterSkill(AttributeType.INTELLIGENCE, SkillType.Appraise, true));
        skillMap.put(SkillType.Swim, new CharacterSkill(AttributeType.STRENGTH, SkillType.Swim, true));
        skillMap.put(SkillType.Fly, new CharacterSkill(AttributeType.AGILITY, SkillType.Fly, true));
        skillMap.put(SkillType.Insign, new CharacterSkill(AttributeType.WISDOM, SkillType.Insign, true));
        skillMap.put(SkillType.Linguistics, new CharacterSkill(AttributeType.INTELLIGENCE, SkillType.Linguistics, false));
        skillMap.put(SkillType.Stealth, new CharacterSkill(AttributeType.AGILITY, SkillType.Stealth, true));
        skillMap.put(SkillType.Perform, new CharacterSkill(AttributeType.CHARISMA, SkillType.Perform, true));
        skillMap.put(SkillType.Craft, new CharacterSkill(AttributeType.INTELLIGENCE, SkillType.Craft, false));
        skillMap.put(SkillType.Profession, new CharacterSkill(AttributeType.INTELLIGENCE, SkillType.Profession, false));
    }

    private SimpleSkillProvider() {

    }

    public static SimpleSkillProvider getInstance() {
        if (instance == null)
            instance = new SimpleSkillProvider();
        return instance;
    }

    @Override
    public CharacterSkill getSkillByType(SkillType type) {
        if (skillMap.get(type) == null)
            throw new IllegalStateException("Skill data " + type + " not initialized");

        return skillMap.get(type);
    }
}
