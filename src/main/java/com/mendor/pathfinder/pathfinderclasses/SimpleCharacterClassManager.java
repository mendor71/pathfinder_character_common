package com.mendor.pathfinder.pathfinderclasses;

import com.mendor.pathfinder.PathfinderCharacter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class SimpleCharacterClassManager implements ICharacterClassManager {

    private HashMap<PathfinderCharacter, Set<CharacterClassDetails>> characterClassMap = new HashMap<>();

    @Override
    public Set<CharacterClassDetails> getCharacterClasses(PathfinderCharacter character) {
        return characterClassMap.get(character);
    }

    @Override
    public CharacterClassDetails getCharacterClassDetails(PathfinderCharacter character, ICharacterClass characterClass) {
        if (!characterClassMap.containsKey(character))
            return null;

        Set<CharacterClassDetails> detailsSet = characterClassMap.get(character);
        for (CharacterClassDetails details: detailsSet) {
            if (details.getCharacterClass().equals(characterClass))
                return details;
        }

        return null;
    }

    @Override
    public boolean containsCharacterClass(PathfinderCharacter character, ICharacterClass characterClass) {
        if (!characterClassMap.containsKey(character))
            return false;

        return characterClassMap.get(character).stream().anyMatch(v -> v.getCharacterClass().equals(characterClass));
    }

    @Override
    public void levelUpAtClass(PathfinderCharacter character, ICharacterClass characterClass) {

    }

    @Override
    public void addCharacterClassDetails(PathfinderCharacter character, CharacterClassDetails characterClass) {
        if (!characterClassMap.containsKey(character))
            characterClassMap.put(character, new HashSet<>());

        characterClassMap.get(character).add(characterClass);
        characterClass.setCharacter(character);
    }

    @Override
    public long getCharacterSummaryLevel(PathfinderCharacter character) {
        if (!characterClassMap.containsKey(character))
            return 0;

        return characterClassMap.get(character).stream().mapToLong(CharacterClassDetails::getLevel).sum();
    }

    @Override
    public long getSummaryCharacterSkillPointsProvidedByClasses(PathfinderCharacter character) {
        if (!characterClassMap.containsKey(character))
            throw new IllegalStateException("character classes not initialized");

        return characterClassMap.get(character).stream().mapToLong(v -> v.getCharacterClass().getSkillPointsByLevel() * v.getLevel()).sum();
    }

    public void setOnControl(PathfinderCharacter character, Set<CharacterClassDetails> controlObjects) {
        controlObjects.forEach(v -> addCharacterClassDetails(character, v));
    }
}
