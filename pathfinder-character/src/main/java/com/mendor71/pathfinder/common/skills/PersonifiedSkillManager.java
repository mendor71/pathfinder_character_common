package com.mendor71.pathfinder.common.skills;

import com.mendor71.pathfinder.common.exceptions.CharacterSkillAlreadyExistsException;
import com.mendor71.pathfinder.common.exceptions.CharacterSkillListIllegalStateException;
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
            if (skillMap.containsKey(skillType) && !skillMap.get(skillType).isClassBonusUsed())
                skillMap.get(skillType).applyClassBonus();
        });
    }

    @Override
    public void setSkillsOnControl(Set<CharacterSkillDetails> skillDetails) {
        skillDetails.forEach(skill ->  { if (!skillMap.containsKey(skill.getSkillType())) skillMap.put(skill.getSkillType(), skill); });
        this.usedSkillPoints = skillDetails.stream().mapToLong(CharacterSkillDetails::getTrainedPoints).sum();
    }

    @Override
    public void addCharacterSkill(SkillType type, long trainedPoints) throws CharacterSkillAlreadyExistsException {
        if (skillMap.containsKey(type))
            throw new CharacterSkillAlreadyExistsException("Character " + this.characterId + " already has skill " + type);
        skillMap.put(type, new CharacterSkillDetails(SimpleSkillProvider.getInstance().getSkillByType(type), trainedPoints));
    }

    @Override
    public void addCharacterSkill(SkillType type) throws CharacterSkillAlreadyExistsException {
        this.addCharacterSkill(type, 0);
    }

    @Override
    public CharacterSkillDetails getCharacterSkillDetails(SkillType type) throws CharacterSkillListIllegalStateException {
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
    public long trainSkill(SkillType type, long value) throws NotEnoughSkillPointsException {
        if (value <= freeSkillPoints) {
            long newValue = 0;
            if (skillMap.containsKey(type)) {
                newValue = skillMap.get(type).increaseTrainedPoints(value, classSkills.contains(type));
            } else {
                skillMap.put(type, new CharacterSkillDetails(SimpleSkillProvider.getInstance().getSkillByType(type)));
                trainSkill(type, value);
            }
            freeSkillPoints -= value;
            return newValue;
        } else {
            throw new NotEnoughSkillPointsException("free skill points: " + freeSkillPoints + ", trying to use: " + value);
        }
    }

    @Override
    public long increaseSkillStableBonusValue(SkillType type, long value) throws CharacterSkillListIllegalStateException {
        return getCharacterSkillDetailsByTypeOrThrowException(type).increaseStableBonus(value);
    }

    @Override
    public long decreaseSkillStableBonusValue(SkillType type, long value) throws CharacterSkillListIllegalStateException {
        return getCharacterSkillDetailsByTypeOrThrowException(type).decreaseStableBonus(value);
    }

    @Override
    public long increaseSkillTemporaryModifierValue(SkillType type, long value) throws CharacterSkillListIllegalStateException {
        return getCharacterSkillDetailsByTypeOrThrowException(type).increaseTemporarySkillModifier(value);
    }

    @Override
    public long decreaseSkillTemporaryModifierValue(SkillType type, long value) throws CharacterSkillListIllegalStateException {
        return getCharacterSkillDetailsByTypeOrThrowException(type).decreaseTemporarySkillModifier(value);
    }

    @Override
    public void resetSkillTemporaryModifierValue(SkillType type) throws CharacterSkillListIllegalStateException {
        getCharacterSkillDetailsByTypeOrThrowException(type).resetTemporarySkillModifier();
    }

    @Override
    public long getSumarySkillValue(SkillType type) {
        if (skillMap.containsKey(type))
            return skillMap.get(type).getSummaryValue();
        return 0;
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
    public long getSkillTrainedPoints(SkillType type) {
        if (!skillMap.containsKey(type))
            return 0;
        return skillMap.get(type).getTrainedPoints();
    }

    @Override
    public long getSkillStableBonusValue(SkillType type) {
        if (!skillMap.containsKey(type))
            return 0;
        return skillMap.get(type).getStableBonus();
    }

    @Override
    public long getSkillTemporaryModiferValue(SkillType type) {
        if (!skillMap.containsKey(type))
            return 0;
        return skillMap.get(type).getTemporarySkillValueModifier();
    }

    @Override
    public long increaseFreeSkillPoints(long value) {
        return freeSkillPoints += value;
    }

    @Override
    public CharacterSkillDetails getCharacterSkillDetailsByTypeOrThrowException(SkillType type) throws CharacterSkillListIllegalStateException {
        if (!skillMap.containsKey(type))
            throw new CharacterSkillListIllegalStateException("Skill details list for character: " + characterId + " must contains exactly one skill with type " + type);
        return skillMap.get(type);
    }
}
