package com.mendor71.pathfinder.common.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.mendor71.pathfinder.common.attributes.CharacterAttributeDetails;
import com.mendor71.pathfinder.common.attributes.PersonifiedAttributeManager;
import com.mendor71.pathfinder.common.types.AttributeType;
import com.mendor71.pathfinder.common.Character;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CustomCharacterJSONDeserializerWihAttributes extends CustomCharacterJSONDeserializer {

    public CustomCharacterJSONDeserializerWihAttributes(Class<?> c) {
        super(c);
    }

    public CustomCharacterJSONDeserializerWihAttributes(CustomCharacterJSONDeserializer baseDeserializer) {
        super(Character.Builder.class);
        this.baseDeserializer = baseDeserializer;
    }

    @Override
    protected JsonNode getRoot() {
        return baseDeserializer.getRoot();
    }

    @Override
    public Character.Builder deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        Character.Builder builder = baseDeserializer.deserialize(parser, context);

        ArrayNode attributes = (ArrayNode) getRoot().get("attributes");

        Set<CharacterAttributeDetails> attributeDetailsSet = new HashSet<>();

        for (JsonNode attr: attributes) {
            CharacterAttributeDetails details = new CharacterAttributeDetails();
            AttributeType type = AttributeType.byName(attr.get("type").asText());
            details.setType(type);
            details.setValue(attr.get("value").longValue());
            details.setStableValueBonus(attr.get("stableValueBonus").longValue());
            details.setTempValueBonus(attr.get("tempValueBonus").longValue());
            details.setTempModifierBonus(attr.get("tempModifierBonus").longValue());
            details.setId(attr.get("id").longValue());
            attributeDetailsSet.add(details);
        }

        builder.manageAttributes(new PersonifiedAttributeManager(builder.getUuid()), attributeDetailsSet);
        return builder;
    }
}
