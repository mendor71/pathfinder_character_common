//package com.mendor.pclasses;
//
//import com.mendor.CharacterSkill;
//import com.mendor.types.SkillType;
//
//import java.util.Set;
//
//public class PaladinClass extends CharacterClass {
//
//    private static PaladinClass paladinClass;
//
//    private PaladinClass() { }
//
//    public static synchronized PaladinClass getInstance() {
//        if (paladinClass == null)
//            paladinClass = new PaladinClass();
//
//            paladinClass.characterClassSkills.add(paladinClass.simpleSkillProvider.getSkillByType(SkillType.Craft));
//            paladinClass.characterClassSkills.add(paladinClass.simpleSkillProvider.getSkillByType(SkillType.Diplomacy));
//            paladinClass.characterClassSkills.add(paladinClass.simpleSkillProvider.getSkillByType(SkillType.HandleAnimal));
//            paladinClass.characterClassSkills.add(paladinClass.simpleSkillProvider.getSkillByType(SkillType.Heal));
//            paladinClass.characterClassSkills.add(paladinClass.simpleSkillProvider.getSkillByType(SkillType.KnowledgeNobility));
//            paladinClass.characterClassSkills.add(paladinClass.simpleSkillProvider.getSkillByType(SkillType.KnowledgeReligion));
//            paladinClass.characterClassSkills.add(paladinClass.simpleSkillProvider.getSkillByType(SkillType.Profession));
//            paladinClass.characterClassSkills.add(paladinClass.simpleSkillProvider.getSkillByType(SkillType.Ride));
//            paladinClass.characterClassSkills.add(paladinClass.simpleSkillProvider.getSkillByType(SkillType.SenseMotive));
//            paladinClass.characterClassSkills.add(paladinClass.simpleSkillProvider.getSkillByType(SkillType.Spellcraft));
//        return paladinClass;
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
//        return "Paladin";
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
