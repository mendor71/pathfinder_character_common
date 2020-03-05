package com.mendor71.pathfinder.common.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.mendor71.pathfinder.common.pathfinderclasses.CharacterClassDetails;
import com.mendor71.pathfinder.common.pathfinderclasses.CharacterClass;
import com.mendor71.pathfinder.common.pathfinderclasses.PersonifiedClassManager;
import com.mendor71.pathfinder.common.types.ClassType;
import com.mendor71.pathfinder.common.Character;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CustomCharacterJSONDeserializerWithClasses extends CustomCharacterJSONDeserializer {

    public CustomCharacterJSONDeserializerWithClasses(CustomCharacterJSONDeserializer baseDeserializer) {
        super(Character.Builder.class);
        this.baseDeserializer = baseDeserializer;
    }

    @Override
    protected JsonNode getRoot() {
        return baseDeserializer.getRoot();
    }

    @Override
    public Character.Builder deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        Character.Builder builder =  baseDeserializer.deserialize(parser, context);

        JsonNode root = getRoot();
        ArrayNode classes = (ArrayNode) root.get("classes");

        Set<CharacterClassDetails> classDetailsSet = new HashSet<>();

        for (JsonNode cl: classes) {
            ClassType type = ClassType.byName(cl.get("name").asText());
            long level = cl.get("level").longValue();

            CharacterClass characterClass = CharacterClass.getInstance(type);
            CharacterClassDetails details = new CharacterClassDetails(characterClass, level);
            classDetailsSet.add(details);
        }

        builder.manageClasses(new PersonifiedClassManager(builder.getUuid()), classDetailsSet);
        return builder;
    }
}
