package com.mendor.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.mendor.CharacterClassDetails;
import com.mendor.PathfinderCharacter;
import com.mendor.CharacterClass;
import com.mendor.types.ClassType;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CustomCharacterJSONDeserializerWithClasses extends CustomCharacterJSONDeserializer {

    public CustomCharacterJSONDeserializerWithClasses(CustomCharacterJSONDeserializer baseDeserializer) {
        super(PathfinderCharacter.class);
        this.baseDeserializer = baseDeserializer;
    }

    @Override
    protected JsonNode getRoot() {
        return baseDeserializer.getRoot();
    }

    @Override
    public PathfinderCharacter deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        PathfinderCharacter character =  baseDeserializer.deserialize(parser, context);

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

        character.setClasses(classDetailsSet);
        return character;
    }
}
