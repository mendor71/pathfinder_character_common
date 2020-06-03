package com.mendor71.pathfinder.common.bonus;

import com.mendor71.pathfinder.common.Character;
import com.mendor71.pathfinder.common.attributes.CharacterAttribute;
import com.mendor71.pathfinder.common.types.AttributeType;

public class AttributeCharacterBonus extends AbstractCharacterBonus implements ICharacterBonus {
    private AttributeType type;

    public AttributeCharacterBonus(long value, boolean temporary) {
        super(value, temporary);
    }

    public AttributeCharacterBonus(AttributeType type, long value, boolean temporary) {
        this(value, temporary);
        this.type = type;
    }

    @Override
    public void apply(Character character) {
        CharacterAttribute attributeDetails = character.getAttributeByTypeOrThrowException(type);
        if (temporary)
            attributeDetails.increaseTempValueBonus(value);
        else
            attributeDetails.increaseStableValueBonus(value);
    }
}
