package com.mendor71.pathfinder.common.skills;

import com.mendor71.pathfinder.common.exceptions.NotEnoughSkillPointsException;
import com.mendor71.pathfinder.common.types.SkillType;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PersonifiedSkillManager implements ISkillManager {
    private String characterId;
    private Map<SkillType, CharacterSkillDetails> skillMap = new EnumMap<>(SkillType.class);
    private Set<SkillType> classSkills;
    private long sumSkillPoints;
    private long usedSkillPoints;
    private long freeSkillPoints;

    public PersonifiedSkillManager(String characterId) {
        this.characterId = characterId;
    }

    public PersonifiedSkillManager(String characterId, Set<CharacterSkillDetails> skillDetails) {
        this(characterId);
        setSkillsOnControl(skillDetails);
    }

    @Override
    public void setClassSkills(Set<SkillType> classSkills) {
        this.classSkills = classSkills;
        classSkills.forEach(skillType -> {
            if (skillMap.containsKey(skillType))
                skillMap.get(skillType).increaseBonus(3);
        });
    }

    @Override
    public void setSkillsOnControl(Set<CharacterSkillDetails> skillDetails) {
        skillDetails.forEach(skill ->  { if (!skillMap.containsKey(skill.getSkillType())) skillMap.put(skill.getSkillType(), skill); });
        this.usedSkillPoints = skillDetails.stream().mapToLong(CharacterSkillDetails::getTrainedPoints).sum();
    }

    @Override
    public CharacterSkillDetails getCharacterSkillDetails(SkillType type) {
       return getCharacterSkillDetailsByTypeOrThrowException(type);
    }

    @Override
    public Set<CharacterSkillDetails> getCharacterSkillDetailsSet() {
        return new HashSet<>(skillMap.values());
    }

    @Override
    public boolean containsCharacterSkill(SkillType type) {
        return skillMap.containsKey(type);
    }

    @Override
    public long increaseSkillPoints(SkillType type, long value) throws NotEnoughSkillPointsException {
        if (value <= freeSkillPoints) {
            long newValue = 0;
            if (skillMap.containsKey(type)) {
                newValue = skillMap.get(type).increaseValue(value, classSkills.contains(type));
            } else {
                skillMap.put(type, new CharacterSkillDetails(SimpleSkillProvider.getInstance().getSkillByType(type)));
                increaseSkillPoints(type, value);
            }
            freeSkillPoints -= value;
            return newValue;
        } else {
            throw new NotEnoughSkillPointsException("free skill points: " + freeSkillPoints + ", trying to use: " + value);
        }
    }

    @Override
    public long getUsedSkillPointsBySkill(SkillType type) {
        return getCharacterSkillDetailsByTypeOrThrowException(type).getTrainedPoints();
    }

    @Override
    public long setSumSkillPoints(long value) {
        this.sumSkillPoints = value;
        this.freeSkillPoints = sumSkillPoints - usedSkillPoints;
        return value;
    }

    @Override
    public long getFreeSkillPoints() {
        return freeSkillPoints;
    }

    @Override
    public long getUsedSkillPoints() {
        return usedSkillPoints;
    }

    @Override
    public long getSkillValue(SkillType type) {
        return getCharacterSkillDetailsByTypeOrThrowException(type).getSkillValue();
    }

    @Override
    public long getSkillTrainedPoints(SkillType type) {
        return getCharacterSkillDetailsByTypeOrThrowException(type).getTrainedPoints();
    }

    @Override
    public long increaseFreeSkillPoints(long value) {
        return freeSkillPoints += value;
    }

    @Override
    public CharacterSkillDetails getCharacterSkillDetailsByTypeOrThrowException(SkillType type) throws IllegalStateException {
        if (!skillMap.containsKey(type))
            throw new IllegalStateException("Skill details list for character: " + characterId + " must contains exactly one skill with type " + type);
        return skillMap.get(type);
    }
}
