package com.mendor.pathfinder.common.attributes;

import com.mendor.pathfinder.common.PathfinderCharacter;
import com.mendor.pathfinder.common.types.AttributeType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SimpleCharacterAttributeManager implements ICharacterAttributeManager {

    private HashMap<PathfinderCharacter, Set<CharacterAttributeDetails>> characterAttributeMap = new HashMap<>();

    @Override
    public long getAttributeValue(PathfinderCharacter character, AttributeType type) {
        return getAttributeDetails(character, type).getValue();
    }

    @Override
    public long setAttributeValue(PathfinderCharacter character, AttributeType type, long value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getAttributeModifier(PathfinderCharacter character, AttributeType type) throws IllegalStateException {
        return getAttributeDetails(character, type).getModifier();
    }

    @Override
    public long increaseAttributeValue(PathfinderCharacter character, AttributeType type, long bonus) {
        return getAttributeDetails(character, type).increaseValue(bonus);
    }

    @Override
    public long decreaseAttributeValue(PathfinderCharacter character, AttributeType type, long minus) {
        return getAttributeDetails(character, type).decreaseValue(minus);
    }

    @Override
    public Set<CharacterAttributeDetails> getCharacterAttributes(PathfinderCharacter character) {
        if (!characterAttributeMap.containsKey(character))
            return null;
        return characterAttributeMap.get(character);
    }

    @Override
    public void setOnControl(PathfinderCharacter character, Set<CharacterAttributeDetails> controlObjects) {
        if (!characterAttributeMap.containsKey(character))
            characterAttributeMap.put(character, new HashSet<>());

        controlObjects.forEach(v -> {
            characterAttributeMap.get(character).add(v);
        });
    }

    @Override
    public CharacterAttributeDetails getAttributeDetails(PathfinderCharacter character, AttributeType type) {
        List<CharacterAttributeDetails> attributeDetails = characterAttributeMap.get(character).stream()
                .filter(v -> v.getType().equals(type))
                .collect(Collectors.toList());

        if (attributeDetails.size() != 1)
            throw new IllegalStateException("Attribute list for character " + character + " must contains exactly one attribute with type " + type);
        return attributeDetails.get(0);
    }
}
