package com.mendor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Set;

public class CustomCharacterJSONSerializerWithAttributes extends CustomCharacterJSONSerializer {

    public CustomCharacterJSONSerializerWithAttributes(IJSONSerializer basePreSerializer) {
        super(basePreSerializer);
    }

    @Override
    public void preSerialize(PathfinderCharacter character) {
        super.preSerialize(character);

        Set<CharacterAttributeDetails> attributes = character.getAttributes();

        ArrayNode attrs = root.putArray("attributes");

        for (CharacterAttributeDetails d: attributes) {
            ObjectNode attr = attrs.addObject();
            attr.put("type", d.getType().toString());
            attr.put("value", d.getValueNormal());
            attr.put("tempValueBonus", d.getTempValueBonus());
            attr.put("modifier", d.getModifier());
            attr.put("tempModifierBonus", d.getTempModifierBonus());
            attr.put("id", d.getId());
        }
    }
}
