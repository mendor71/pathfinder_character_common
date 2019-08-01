package com.mendor;

import com.mendor.exceptions.NotEnoughSkillPointsException;
import com.mendor.types.SkillType;
import com.mendor.util.Tuple2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SimpleCharacterSkillManager implements ICharacterSkillManager {
    private SimpleSkillProvider simpleSkillProvider = SimpleSkillProvider.getInstance();
    private Map<PathfinderCharacter, Set<CharacterSkillDetails>> characterSkillDetailsMap = new HashMap<>();
    private Map<PathfinderCharacter, Tuple2<Long,Long>> characterSkillPointsMap = new HashMap<>();

    @Override
    public void setOnControlClassSkills(PathfinderCharacter character) {
        if (!characterSkillDetailsMap.containsKey(character))
            characterSkillDetailsMap.put(character, new HashSet<>());

        character.getCharacterClasses().forEach( c -> {
            Set<CharacterSkill> classSkills = c.getCharacterClass().getClassSkills();

            classSkills.forEach( s -> {
                if (s != null && !containsCharacterSkill(character, s.getSkillType())) {
                    characterSkillDetailsMap.get(character).add(new CharacterSkillDetails(character, s, CharacterSkill.defaultClassBonus));
                    character.addAttributeSkillListener(s.getAttributeType(), s.getSkillType());
                }
            });
        });
    }

    @Override
    public void addAttributeSkillListeners(PathfinderCharacter character, Set<CharacterSkillDetails> skillDetails) {
        skillDetails.forEach(s -> {
            character.addAttributeSkillListener(s.getAttributeType(), s.getSkillType());
        });
    }

    @Override
    public void setOnControl(PathfinderCharacter character, Set<CharacterSkillDetails> skillDetails) {
        if (!characterSkillDetailsMap.containsKey(character))
            characterSkillDetailsMap.put(character, new HashSet<>());

        characterSkillDetailsMap.get(character).addAll(skillDetails);
        addAttributeSkillListeners(character, skillDetails);
    }

    @Override
    public CharacterSkillDetails getCharacterSkillDetails(PathfinderCharacter character, SkillType type) {
        if (characterSkillDetailsMap.containsKey(character)) {
            for (CharacterSkillDetails d : characterSkillDetailsMap.get(character)) {
                if (d.getSkill().getSkillType().equals(type))
                    return d;
            }
        }
        return null;
    }

    @Override
    public Set<CharacterSkillDetails> getCharacterSkillDetailsSet(PathfinderCharacter character) {
        return characterSkillDetailsMap.get(character);
    }

    @Override
    public boolean containsCharacterSkill(PathfinderCharacter character, SkillType type) {
        if (!characterSkillDetailsMap.containsKey(character))
            return false;

        for (CharacterSkillDetails d: characterSkillDetailsMap.get(character))
            if (d.getSkill().getSkillType().equals(type))
                return true;

        return false;
    }

    @Override
    public long increaseSkillPoints(PathfinderCharacter character, SkillType type, long increaseOn) throws NotEnoughSkillPointsException {
        if (!characterSkillPointsMap.containsKey(character))
            throw new IllegalStateException("character skill point map not initialized");

        if (characterSkillPointsMap.get(character).getValue1() < increaseOn)
            throw new NotEnoughSkillPointsException("free skill points: " + characterSkillPointsMap.get(character).getValue1() + ", trying to use: " + increaseOn);

        long newValue;
        if ( containsCharacterSkill(character, type) ) {
            CharacterSkillDetails details = getCharacterSkillDetails(character, type);
            newValue = details.increaseValue(increaseOn);
        } else {
            if (!characterSkillDetailsMap.containsKey(character))
                throw new IllegalStateException("set character data before use this method");

            characterSkillDetailsMap.get(character).add(new CharacterSkillDetails(character, simpleSkillProvider.getSkillByType(type), increaseOn));
            newValue = increaseOn;
        }

        Tuple2<Long, Long> oldVal = characterSkillPointsMap.get(character);
        characterSkillPointsMap.put(character, new Tuple2<>(oldVal.getValue1() - increaseOn, oldVal.getValue2() + increaseOn));
        return newValue;
    }

    @Override
    public long getSkillPoints(PathfinderCharacter character, SkillType type) {
        if (!containsCharacterSkill(character, type))
            return 0;

        return getCharacterSkillDetails(character, type).getValue();
    }

    @Override
    public void setCharacterBaseSkillPoints(PathfinderCharacter character) {
        if (!characterSkillPointsMap.containsKey(character))
            characterSkillPointsMap.put(character, new Tuple2<>(character.getSkillPointsProvidedByClasses(), 0L));
    }

    @Override
    public void setFreeAndUsedSkillPoints(PathfinderCharacter character, long free, long used) {
        characterSkillPointsMap.put(character, new Tuple2<>(free, used));
    }

    @Override
    public long getFreeSkillPoints(PathfinderCharacter character) {
        if (!characterSkillPointsMap.containsKey(character))
            return 0;
        return characterSkillPointsMap.get(character).getValue1();
    }

    @Override
    public long getUsedSkillPoints(PathfinderCharacter character) {
        if (!characterSkillPointsMap.containsKey(character))
            return 0;
        return characterSkillPointsMap.get(character).getValue2();
    }

    @Override
    public long increaseFreeSkillPoints(PathfinderCharacter character, long value) {
        Tuple2<Long, Long> oldVal = characterSkillPointsMap.get(character);
        characterSkillPointsMap.put(character, new Tuple2<>(oldVal.getValue1() + value, oldVal.getValue2()));
        return oldVal.getValue1() + value;
    }
}
