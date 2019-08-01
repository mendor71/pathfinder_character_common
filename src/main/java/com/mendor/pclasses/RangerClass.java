//package com.mendor.pclasses;
//
//import com.mendor.CharacterSkill;
//import com.mendor.types.SkillType;
//
//import java.util.Set;
//
//public class RangerClass extends CharacterClass {
//
//    private static RangerClass rangerClass;
//
//    private RangerClass() { }
//
//    public static synchronized RangerClass getInstance() {
//        if (rangerClass == null) {
//            rangerClass = new RangerClass();
//            rangerClass.characterClassSkills.add(rangerClass.simpleSkillProvider.getSkillByType(SkillType.Climb));
//            rangerClass.characterClassSkills.add(rangerClass.simpleSkillProvider.getSkillByType(SkillType.Craft));
//            rangerClass.characterClassSkills.add(rangerClass.simpleSkillProvider.getSkillByType(SkillType.HandleAnimal));
//            rangerClass.characterClassSkills.add(rangerClass.simpleSkillProvider.getSkillByType(SkillType.Heal));
//            rangerClass.characterClassSkills.add(rangerClass.simpleSkillProvider.getSkillByType(SkillType.Intimidate));
//            rangerClass.characterClassSkills.add(rangerClass.simpleSkillProvider.getSkillByType(SkillType.KnowledgeDungeoneering));
//            rangerClass.characterClassSkills.add(rangerClass.simpleSkillProvider.getSkillByType(SkillType.KnowledgeGeography));
//            rangerClass.characterClassSkills.add(rangerClass.simpleSkillProvider.getSkillByType(SkillType.KnowledgeNature));
//            rangerClass.characterClassSkills.add(rangerClass.simpleSkillProvider.getSkillByType(SkillType.Perception));
//            rangerClass.characterClassSkills.add(rangerClass.simpleSkillProvider.getSkillByType(SkillType.Profession));
//            rangerClass.characterClassSkills.add(rangerClass.simpleSkillProvider.getSkillByType(SkillType.Ride));
//            rangerClass.characterClassSkills.add(rangerClass.simpleSkillProvider.getSkillByType(SkillType.Spellcraft));
//            rangerClass.characterClassSkills.add(rangerClass.simpleSkillProvider.getSkillByType(SkillType.Stealth));
//            rangerClass.characterClassSkills.add(rangerClass.simpleSkillProvider.getSkillByType(SkillType.Survival));
//            rangerClass.characterClassSkills.add(rangerClass.simpleSkillProvider.getSkillByType(SkillType.Swim));
//        }
//
//        return rangerClass;
//    }
//
//    @Override
//    public String getHitDiceName() {
//        return "1d10";
//    }
//
//    @Override
//    public long getMinHitDiceValue() {
//        return (long) 1;
//    }
//
//    @Override
//    public long getId() {
//        return 0;
//    }
//
//    @Override
//    public String getName() {
//        return "Ranger";
//    }
//
//    @Override
//    public long getMaxHitDiceValue() {
//        return (long) 10;
//    }
//
//    @Override
//    public long getSkillPointsByLevel() {
//        return (long) 6;
//    }
//
//    @Override
//    public Set<CharacterSkill> getClassSkills() {
//        return characterClassSkills;
//    }
//}
