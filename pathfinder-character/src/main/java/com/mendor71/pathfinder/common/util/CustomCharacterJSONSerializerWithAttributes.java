package com.mendor71.pathfinder.common.util;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mendor71.pathfinder.common.attributes.CharacterAttribute;
import com.mendor71.pathfinder.common.Character;
import com.mendor71.pathfinder.common.exceptions.RaceNotExiststException;

import java.util.Set;

public class CustomCharacterJSONSerializerWithAttributes extends CustomCharacterJSONSerializer {

    public CustomCharacterJSONSerializerWithAttributes(IJSONSerializer basePreSerializer) {
        super(basePreSerializer);
    }

    @Override
    public ObjectNode serialize(Character character) throws RaceNotExiststException {
        ObjectNode root = basePreSerializer.serialize(character);

        Set<CharacterAttribute> attributes = character.getCharacterAttributes();

        ArrayNode attrs = root.putArray("attributes");

        for (CharacterAttribute d: attributes) {
            ObjectNode attr = attrs.addObject();
            attr.put("type", d.getType().toString());
            attr.put("value", d.getValue());
            attr.put("stableValueBonus", d.getStableValueBonus());
            attr.put("tempValueBonus", d.getTempValueBonus());
            attr.put("tempModifierBonus", d.getTempModifierBonus());
            attr.put("id", d.getId());
        }
        return root;
    }
}
