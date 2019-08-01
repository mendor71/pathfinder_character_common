package com.mendor.types;

public enum SkillType {
    Acrobatics
    , Appraise
    , Bluff
    , Climb
    , Craft
    , Diplomacy
    , DisableDevice
    , Disguise
    , EscapeArtist
    , Fly
    , HandleAnimal
    , Heal
    , Intimidate
    , Insign
    , KnowledgeArcana
    , KnowledgeDungeoneering
    , KnowledgeEngineering
    , KnowledgeGeography
    , KnowledgeHistory
    , KnowledgeLocal
    , KnowledgeNature
    , KnowledgeNobility
    , KnowledgePlane
    , KnowledgeReligion
    , Linguistics
    , Perception
    , Perform
    , Profession
    , Ride
    , SenseMotive
    , SleightOfHand
    , Spellcraft
    , Stealth
    , Survival
    , Swim
    , UseMagicDevice;


    public static SkillType byName(String name) {
        for (SkillType d: values()) {
            if (d.toString().equals(name))
                return d;
        }
        return null;
    }
}
