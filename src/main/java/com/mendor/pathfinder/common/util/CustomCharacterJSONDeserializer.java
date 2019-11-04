package com.mendor.pathfinder.common.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.mendor.pathfinder.common.PathfinderCharacter;
import com.mendor.pathfinder.common.attributes.SimpleCharacterAttributeManager;
import com.mendor.pathfinder.common.pathfinderclasses.SimpleCharacterClassManager;
import com.mendor.pathfinder.common.skills.SimpleCharacterSkillManager;

import java.awt.*;
import java.io.IOException;

public class CustomCharacterJSONDeserializer extends StdDeserializer<PathfinderCharacter> {
    protected CustomCharacterJSONDeserializer baseDeserializer;
    protected JsonNode root;

    public CustomCharacterJSONDeserializer(Class<?> c) {
        super(c);
    }

    public CustomCharacterJSONDeserializer(CustomCharacterJSONDeserializer baseDeserializer) {
        this(PathfinderCharacter.class);
        this.baseDeserializer = baseDeserializer;
    }

    protected JsonNode getRoot() {
        return root;
    }

    @Override
    public PathfinderCharacter deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        PathfinderCharacter pathfinderCharacter = new PathfinderCharacter();

        pathfinderCharacter.setAttributeManager(new SimpleCharacterAttributeManager());
        pathfinderCharacter.setClassManager(new SimpleCharacterClassManager());
        pathfinderCharacter.setSkillManager(new SimpleCharacterSkillManager());

        JsonNode root = parser.getCodec().readTree(parser);
        this.root = root;
        JsonNode properties = root.get("characterProperties");

        pathfinderCharacter.setUuid(properties.get("uuid").asText());
        pathfinderCharacter.setName(properties.get("name").asText());
        pathfinderCharacter.setLevel(properties.get("level").longValue());
        pathfinderCharacter.setAge(properties.get("age").longValue());
        pathfinderCharacter.setArmorClass(properties.get("armorClass").longValue());
        pathfinderCharacter.setHeight(properties.get("height").longValue());
        pathfinderCharacter.setWeight(properties.get("weight").longValue());

        JsonNode eyeColorNode = properties.get("eyeColor");
        if ( eyeColorNode.hasNonNull("R") && eyeColorNode.hasNonNull("G") && eyeColorNode.hasNonNull("B") ) {
            Color eyeColor = new Color(eyeColorNode.get("R").asInt(), eyeColorNode.get("G").asInt(), eyeColorNode.get("B").asInt());
            pathfinderCharacter.setEyeColor(eyeColor);
        }

        JsonNode hairColorNode = properties.get("hairColor");
        if (hairColorNode.hasNonNull("R") && hairColorNode.hasNonNull("G") && hairColorNode.hasNonNull("B")) {
            Color hairColor = new Color(hairColorNode.get("R").asInt(), hairColorNode.get("G").asInt(), hairColorNode.get("B").asInt());
            pathfinderCharacter.setHairColor(hairColor);
        }

        return pathfinderCharacter;
    }
}
