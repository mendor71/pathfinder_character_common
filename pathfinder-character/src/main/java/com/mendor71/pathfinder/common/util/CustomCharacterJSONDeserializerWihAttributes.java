package com.mendor71.pathfinder.common.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.mendor71.pathfinder.common.attributes.CharacterAttributeDetails;
import com.mendor71.pathfinder.common.PathfinderCharacter;
import com.mendor71.pathfinder.common.types.AttributeType;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CustomCharacterJSONDeserializerWihAttributes extends CustomCharacterJSONDeserializer {

    public CustomCharacterJSONDeserializerWihAttributes(Class<?> c) {
        super(c);
    }

    public CustomCharacterJSONDeserializerWihAttributes(CustomCharacterJSONDeserializer baseDeserializer) {
        super(PathfinderCharacter.class);
        this.baseDeserializer = baseDeserializer;
    }

    @Override
    protected JsonNode getRoot() {
        return baseDeserializer.getRoot();
    }

    @Override
    public PathfinderCharacter deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        PathfinderCharacter character = baseDeserializer.deserialize(parser, context);

        ArrayNode attributes = (ArrayNode) getRoot().get("attributes");

        Set<CharacterAttributeDetails> attributeDetailsSet = new HashSet<>();

        for (JsonNode attr: attributes) {
            CharacterAttributeDetails details = new CharacterAttributeDetails();
            AttributeType type = AttributeType.byName(attr.get("type").asText());
            details.setType(type);
            details.setValueNormal(attr.get("value").longValue());
            details.setTempValueBonus(attr.get("tempValueBonus").longValue());
            details.setModifier(attr.get("modifier").longValue());
            details.setTempModifierBonus(attr.get("tempModifierBonus").longValue());
            details.setId(attr.get("id").longValue());
            attributeDetailsSet.add(details);
        }

        character.setAttributes(attributeDetailsSet);
        return character;
    }
}
