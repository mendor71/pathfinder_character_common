package com.mendor71.pathfinder.common.pathfinderclasses;

import com.mendor71.pathfinder.common.types.ClassType;
import com.mendor71.pathfinder.common.types.SkillType;

import java.util.*;
import java.util.stream.Collectors;

public class PersonifiedClassManager implements IClassManager {
    private String characterId;

    private Map<ClassType, CharacterClassDetails> classDetailsMap = new EnumMap<>(ClassType.class);

    public PersonifiedClassManager(String characterId) {
        this.characterId = characterId;
    }

    public PersonifiedClassManager(String characterId, Map<ClassType, CharacterClassDetails> classDetailsMap) {
        this.characterId = characterId;
        this.classDetailsMap = classDetailsMap;
    }

    public PersonifiedClassManager(String characterId, Set<CharacterClassDetails> classDetailsSet) {
        this.characterId = characterId;
        setClassesOnControl(classDetailsSet);
    }

    @Override
    public Set<CharacterClassDetails> getCharacterClasses() {
        return new HashSet<>(classDetailsMap.values());
    }

    @Override
    public CharacterClassDetails getCharacterClassDetails(ClassType type) {
        return getClassDetailsByTypeOrThrowException(type);
    }

    @Override
    public boolean containsCharacterClass(ClassType type) {
        return classDetailsMap.containsKey(type);
    }

    @Override
    /** Just add +1 to level value
     * @param type type of target class
     * */
    public void levelUpAtClass(ClassType type) {
        getClassDetailsByTypeOrThrowException(type).increaseLevel(1);
    }

    @Override
    public void addNewClassDetails(CharacterClassDetails classDetails) {
        this.classDetailsMap.put(classDetails.getCharacterClass().getType(), classDetails);
    }

    @Override
    public long getCharacterSummaryLevel() {
        return this.classDetailsMap.values().stream().mapToLong(CharacterClassDetails::getLevel).sum();
    }

    @Override
    public long getSummaryCharacterSkillPointsProvidedByClasses() {
        return this.classDetailsMap.values().stream().mapToLong(details -> details.getLevel() * details.getCharacterClass().getSkillPointsByLevel()).sum();
    }

    @Override
    public void setClassesOnControl(Set<CharacterClassDetails> characterClassDetails) {
        characterClassDetails.forEach(classDetails -> classDetailsMap.put(classDetails.getCharacterClass().getType(), classDetails));
    }

    public Set<SkillType> getClassSkills() {
        return classDetailsMap.values().stream().flatMap(v -> v.getCharacterClass().getClassSkills().stream()).collect(Collectors.toSet());
    }

    @Override
    public boolean isClassSkill(SkillType skillType) {
        return classDetailsMap.values().stream().anyMatch(classDetails ->
                        classDetails.getCharacterClass().getClassSkills().stream().anyMatch(skill ->
                                skill.equals(skillType)
                        )
        );
    }

    @Override
    public CharacterClassDetails getClassDetailsByTypeOrThrowException(ClassType type) throws IllegalStateException {
        if (!classDetailsMap.containsKey(type))
            throw new IllegalStateException("Class details list for character: " + characterId + " must contains exactly one character class with type " + type);
        return classDetailsMap.get(type);
    }
}
