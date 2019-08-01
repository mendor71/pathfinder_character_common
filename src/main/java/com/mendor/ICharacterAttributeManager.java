package com.mendor;

import com.mendor.types.AttributeType;

import java.util.List;
import java.util.Set;

public interface ICharacterAttributeManager {
    long getAttributeValue(PathfinderCharacter character, AttributeType type) throws IllegalStateException;
    long setAttributeValue(PathfinderCharacter character, AttributeType type, long value);
    long getAttributeModifier(PathfinderCharacter character, AttributeType type) throws IllegalStateException;
    long increaseAttributeValue(PathfinderCharacter character, AttributeType type, long bonus);
    long decreaseAttributeValue(PathfinderCharacter character, AttributeType type, long minus);
    Set<CharacterAttributeDetails> getCharacterAttributes(PathfinderCharacter character);
    void setOnControl(PathfinderCharacter character, Set<CharacterAttributeDetails> controlObjects);
    public CharacterAttributeDetails getAttributeDetails(PathfinderCharacter character, AttributeType type);
}
