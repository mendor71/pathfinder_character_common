package com.mendor71.pathfinder.common.util;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mendor71.pathfinder.common.PathfinderCharacter;
import com.mendor71.pathfinder.common.attributes.CharacterAttributeDetails;

import java.util.Set;

public class CustomCharacterJSONSerializerWithAttributes extends CustomCharacterJSONSerializer {

    public CustomCharacterJSONSerializerWithAttributes(IJSONSerializer basePreSerializer) {
        super(basePreSerializer);
    }

    @Override
    public ObjectNode serialize(PathfinderCharacter character) {
        ObjectNode root = basePreSerializer.serialize(character);

        Set<CharacterAttributeDetails> attributes = character.getAttributes();

        ArrayNode attrs = root.putArray("attributes");

        for (CharacterAttributeDetails d: attributes) {
            ObjectNode attr = attrs.addObject();
            attr.put("type", d.getType().toString());
            attr.put("value", d.getValue());
            attr.put("tempValueBonus", d.getTempValueBonus());
            attr.put("tempModifierBonus", d.getTempModifierBonus());
            attr.put("id", d.getId());
        }
        return root;
    }
}
