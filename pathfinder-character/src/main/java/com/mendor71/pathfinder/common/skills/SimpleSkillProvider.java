package com.mendor71.pathfinder.common.skills;

import com.mendor71.pathfinder.common.types.AttributeType;
import com.mendor71.pathfinder.common.types.SkillType;

import java.util.HashMap;
import java.util.Map;

public class SimpleSkillProvider implements ISkillProvider {
    private static SimpleSkillProvider instance;

    private static Map<SkillType, Skill> skillMap = new HashMap<>();

    static {
        skillMap.put(SkillType.Acrobatics, new Skill(AttributeType.AGILITY, SkillType.Acrobatics, true));
        skillMap.put(SkillType.Bluff, new Skill(AttributeType.CHARISMA, SkillType.Bluff, true));
        skillMap.put(SkillType.Ride, new Skill(AttributeType.AGILITY, SkillType.Ride, true));
        skillMap.put(SkillType.Perception, new Skill(AttributeType.WISDOM, SkillType.Perception, true));
        skillMap.put(SkillType.Survival, new Skill(AttributeType.WISDOM, SkillType.Survival, true));
        skillMap.put(SkillType.Diplomacy, new Skill(AttributeType.CHARISMA, SkillType.Diplomacy, true));
        skillMap.put(SkillType.HandleAnimal, new Skill(AttributeType.CHARISMA, SkillType.HandleAnimal, false));
        skillMap.put(SkillType.Intimidate, new Skill(AttributeType.CHARISMA, SkillType.Intimidate, true));
        skillMap.put(SkillType.KnowledgeNobility, new Skill(AttributeType.INTELLIGENCE, SkillType.KnowledgeNobility, false));
        skillMap.put(SkillType.KnowledgeGeography, new Skill(AttributeType.INTELLIGENCE, SkillType.KnowledgeNobility, false));
        skillMap.put(SkillType.KnowledgeEngineering, new Skill(AttributeType.INTELLIGENCE, SkillType.KnowledgeEngineering, false));
        skillMap.put(SkillType.KnowledgeHistory, new Skill(AttributeType.INTELLIGENCE, SkillType.KnowledgeLocal, false));
        skillMap.put(SkillType.KnowledgeLocal, new Skill(AttributeType.INTELLIGENCE, SkillType.KnowledgeLocal, false));
        skillMap.put(SkillType.KnowledgeArcana, new Skill(AttributeType.INTELLIGENCE, SkillType.KnowledgeArcana, false));
        skillMap.put(SkillType.KnowledgePlane, new Skill(AttributeType.INTELLIGENCE, SkillType.KnowledgePlane, false));
        skillMap.put(SkillType.KnowledgeDungeoneering, new Skill(AttributeType.INTELLIGENCE, SkillType.KnowledgeDungeoneering, false));
        skillMap.put(SkillType.KnowledgeNature, new Skill(AttributeType.INTELLIGENCE, SkillType.KnowledgeNature, false));
        skillMap.put(SkillType.KnowledgeReligion, new Skill(AttributeType.INTELLIGENCE, SkillType.KnowledgeReligion, false));
        skillMap.put(SkillType.EscapeArtist, new Skill(AttributeType.AGILITY, SkillType.EscapeArtist, true));
        skillMap.put(SkillType.UseMagicDevice, new Skill(AttributeType.CHARISMA, SkillType.UseMagicDevice, false));
        skillMap.put(SkillType.Spellcraft, new Skill(AttributeType.INTELLIGENCE, SkillType.Spellcraft, false));
        skillMap.put(SkillType.SenseMotive, new Skill(AttributeType.WISDOM, SkillType.SenseMotive, false));
        skillMap.put(SkillType.Climb, new Skill(AttributeType.STRENGTH, SkillType.Climb, true));
        skillMap.put(SkillType.Heal, new Skill(AttributeType.WISDOM, SkillType.Heal, true));
        skillMap.put(SkillType.SleightOfHand, new Skill(AttributeType.AGILITY, SkillType.SleightOfHand, false));
        skillMap.put(SkillType.Disguise, new Skill(AttributeType.CHARISMA, SkillType.Disguise, true));
        skillMap.put(SkillType.DisableDevice, new Skill(AttributeType.AGILITY, SkillType.DisableDevice, false));
        skillMap.put(SkillType.Appraise, new Skill(AttributeType.INTELLIGENCE, SkillType.Appraise, true));
        skillMap.put(SkillType.Swim, new Skill(AttributeType.STRENGTH, SkillType.Swim, true));
        skillMap.put(SkillType.Fly, new Skill(AttributeType.AGILITY, SkillType.Fly, true));
        skillMap.put(SkillType.Insign, new Skill(AttributeType.WISDOM, SkillType.Insign, true));
        skillMap.put(SkillType.Linguistics, new Skill(AttributeType.INTELLIGENCE, SkillType.Linguistics, false));
        skillMap.put(SkillType.Stealth, new Skill(AttributeType.AGILITY, SkillType.Stealth, true));
        skillMap.put(SkillType.Perform, new Skill(AttributeType.CHARISMA, SkillType.Perform, true));
        skillMap.put(SkillType.Craft, new Skill(AttributeType.INTELLIGENCE, SkillType.Craft, false));
        skillMap.put(SkillType.Profession, new Skill(AttributeType.INTELLIGENCE, SkillType.Profession, false));
    }

    private SimpleSkillProvider() {

    }

    public static SimpleSkillProvider getInstance() {
        if (instance == null)
            instance = new SimpleSkillProvider();
        return instance;
    }

    @Override
    public Skill getSkillByType(SkillType type) {
        if (skillMap.get(type) == null)
            throw new IllegalStateException("Skill data " + type + " not initialized");

        return skillMap.get(type);
    }
}
