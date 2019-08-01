//package com.mendor.pclasses;
//
//import com.mendor.CharacterSkill;
//import com.mendor.types.SkillType;
//
//import java.util.Set;
//
//public class FighterClass extends CharacterClass {
//
//    private static FighterClass fighterClass;
//
//    private FighterClass() { }
//
//    public static synchronized FighterClass getInstance() {
//        if (fighterClass == null)
//            fighterClass = new FighterClass();
//            fighterClass.characterClassSkills.add(fighterClass.simpleSkillProvider.getSkillByType(SkillType.Climb));
//            fighterClass.characterClassSkills.add(fighterClass.simpleSkillProvider.getSkillByType(SkillType.Craft));
//            fighterClass.characterClassSkills.add(fighterClass.simpleSkillProvider.getSkillByType(SkillType.HandleAnimal));
//            fighterClass.characterClassSkills.add(fighterClass.simpleSkillProvider.getSkillByType(SkillType.Intimidate));
//            fighterClass.characterClassSkills.add(fighterClass.simpleSkillProvider.getSkillByType(SkillType.KnowledgeDungeoneering));
//            fighterClass.characterClassSkills.add(fighterClass.simpleSkillProvider.getSkillByType(SkillType.KnowledgeEngineering));
//            fighterClass.characterClassSkills.add(fighterClass.simpleSkillProvider.getSkillByType(SkillType.Profession));
//            fighterClass.characterClassSkills.add(fighterClass.simpleSkillProvider.getSkillByType(SkillType.Ride));
//            fighterClass.characterClassSkills.add(fighterClass.simpleSkillProvider.getSkillByType(SkillType.Survival));
//            fighterClass.characterClassSkills.add(fighterClass.simpleSkillProvider.getSkillByType(SkillType.Swim));
//        return fighterClass;
//    }
//
//    @Override
//    public String getHitDiceName() {
//        return "1d12";
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
//        return "Fighter";
//    }
//
//    @Override
//    public long getMaxHitDiceValue() {
//        return (long) 10;
//    }
//
//    @Override
//    public long getSkillPointsByLevel() {
//        return (long) 2;
//    }
//
//    @Override
//    public Set<CharacterSkill> getClassSkills() {
//        return characterClassSkills;
//    }
//}
