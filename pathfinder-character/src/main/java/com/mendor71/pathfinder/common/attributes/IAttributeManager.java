package com.mendor71.pathfinder.common.attributes;

import com.mendor71.pathfinder.common.types.AttributeType;

import java.util.Set;

public interface IAttributeManager {
    long getAttributeValue(AttributeType type) throws IllegalStateException;
    long setAttributeValue(AttributeType type, long value);
    long getAttributeModifier(AttributeType type) throws IllegalStateException;
    long increaseAttributeValue(AttributeType type, long bonus);
    long decreaseAttributeValue(AttributeType type, long minus);
    Set<CharacterAttributeDetails> getCharacterAttributes();
    void setAttributesOnControl(Set<CharacterAttributeDetails> controlObjects);
    public CharacterAttributeDetails getAttributeDetails(AttributeType type);
    public CharacterAttributeDetails getAttributeByTypeOrThrowException(AttributeType type) throws IllegalStateException;
}
