package com.mendor71.pathfinder.common.attributes;

import com.mendor71.pathfinder.common.types.AttributeType;

import java.util.*;

public class PersonifiedAttributeManager implements IAttributeManager {
    private String characterId;
    private Map<AttributeType, CharacterAttributeDetails> attributeDetailsMap = new EnumMap<>(AttributeType.class);

    public PersonifiedAttributeManager(String characterId) {
        this.characterId = characterId;
    }

    public PersonifiedAttributeManager(String characterId, Map<AttributeType, CharacterAttributeDetails> attributeDetailsSet) {
        this.characterId = characterId;
        this.attributeDetailsMap = attributeDetailsSet;
    }

    public PersonifiedAttributeManager(String characterId, Set<CharacterAttributeDetails> attributeDetails) {
        this.characterId = characterId;
        setAttributesOnControl(attributeDetails);
    }

    @Override
    public long getAttributeValue(AttributeType type) throws IllegalStateException {
        return getAttributeByTypeOrThrowException(type).getValue();
    }

    @Override
    public long setAttributeValue(AttributeType type, long value) {
        getAttributeByTypeOrThrowException(type).setValue(value);
        return value;
    }

    @Override
    public long getAttributeModifier(AttributeType type) throws IllegalStateException {
        return getAttributeByTypeOrThrowException(type).getModifier();
    }

    @Override
    public long increaseAttributeValue(AttributeType type, long bonus) {
        return getAttributeByTypeOrThrowException(type).increaseValue(bonus);
    }

    @Override
    public long decreaseAttributeValue(AttributeType type, long minus) {
        return getAttributeByTypeOrThrowException(type).decreaseValue(minus);
    }

    @Override
    public Set<CharacterAttributeDetails> getCharacterAttributes() {
        return new HashSet<>(attributeDetailsMap.values());
    }

    @Override
    public void setAttributesOnControl(Set<CharacterAttributeDetails> controlObjects) {
        controlObjects.forEach(attrDetails -> attributeDetailsMap.put(attrDetails.getType(), attrDetails));
    }

    @Override
    public CharacterAttributeDetails getAttributeDetails(AttributeType type) {
        return getAttributeByTypeOrThrowException(type);
    }

    @Override
    public CharacterAttributeDetails getAttributeByTypeOrThrowException(AttributeType type) throws IllegalStateException {
        if (!attributeDetailsMap.containsKey(type))
            throw new IllegalStateException("Attribute list for character: " + characterId + " must contains exactly one attribute with type " + type);
        return attributeDetailsMap.get(type);
    }
}
