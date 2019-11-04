package com.mendor71.pathfinder.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mendor71.pathfinder.common.attributes.CharacterAttributeDetails;
import com.mendor71.pathfinder.common.attributes.IAttributeManager;
import com.mendor71.pathfinder.common.pathfinderclasses.ICharacterClassManager;
import com.mendor71.pathfinder.common.skills.ICharacterSkillManager;
import com.mendor71.pathfinder.common.types.AttributeType;

import java.beans.Transient;
import java.util.Set;

public class Character implements ICharacter {
    @JsonIgnore private IAttributeManager attributeManager;

    private CharacterBase characterBase;

    private Character() {
    }

    public void setCharacterBase(CharacterBase characterBase) {
        this.characterBase = characterBase;
    }

    @Override
    public long getAttributeValue(AttributeType type) throws IllegalStateException {
        return attributeManager.getAttributeValue(type);
    }

    @Override
    public long setAttributeValue(AttributeType type, long value) {
        return attributeManager.setAttributeValue(type, value);
    }

    @Override
    public long getAttributeModifier(AttributeType type) throws IllegalStateException {
        return attributeManager.getAttributeModifier(type);
    }

    @Override
    public long increaseAttributeValue(AttributeType type, long bonus) {
        return attributeManager.increaseAttributeValue(type, bonus);
    }

    @Override
    public long decreaseAttributeValue(AttributeType type, long minus) {
        return attributeManager.decreaseAttributeValue(type, minus);
    }

    @Override
    public Set<CharacterAttributeDetails> getCharacterAttributes() {
        return attributeManager.getCharacterAttributes();
    }

    @Override
    public void setAttributesOnControl(Set<CharacterAttributeDetails> controlObjects) {
        attributeManager.setAttributesOnControl(controlObjects);
    }

    @Override
    public CharacterAttributeDetails getAttributeDetails(AttributeType type) {
        return attributeManager.getAttributeDetails(type);
    }

    @Override
    public CharacterAttributeDetails getAttributeByTypeOrThrowException(AttributeType type) throws IllegalStateException {
        return attributeManager.getAttributeByTypeOrThrowException(type);
    }

    public static Builder newBuilder() {
        return new Character().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder manageAttributes(IAttributeManager attributeManager, Set<CharacterAttributeDetails> attributeDetails) {
            Character.this.attributeManager = attributeManager;
            Character.this.attributeManager.setAttributesOnControl(attributeDetails);
            return this;
        }

        public Builder setCharacterBase(CharacterBase characterBase) {
            Character.this.characterBase = characterBase;
            return this;
        }

        public Character build() {
            return Character.this;
        }
    }
}
