package com.mendor71.pathfinder.common.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.mendor71.pathfinder.common.Character;
import com.mendor71.pathfinder.common.CharacterBase;
import com.mendor71.pathfinder.common.exceptions.CharacterRaceNotSetException;
import com.mendor71.pathfinder.common.exceptions.RaceNotExiststException;
import com.mendor71.pathfinder.common.races.RaceManager;
import com.mendor71.pathfinder.common.races.Races;

import java.awt.*;
import java.io.IOException;

public class CustomCharacterJSONDeserializer extends StdDeserializer<Character.Builder> {
    protected CustomCharacterJSONDeserializer baseDeserializer;
    protected JsonNode root;

    public CustomCharacterJSONDeserializer(Class<?> c) {
        super(c);
    }

    public CustomCharacterJSONDeserializer(CustomCharacterJSONDeserializer baseDeserializer) {
        this(Character.Builder.class);
        this.baseDeserializer = baseDeserializer;
    }

    protected JsonNode getRoot() {
        return root;
    }

    @Override
    public Character.Builder deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode root = parser.getCodec().readTree(parser);
        this.root = root;
        JsonNode properties = root.get("characterProperties");

        CharacterBase.Builder builder = null;

        builder = CharacterBase
            .newBuilder(properties.get("uuid").asText())
            .setArmorClass(properties.get("armorClass").longValue())
            .setName(properties.get("name").asText())
            .setLevel(properties.get("level").longValue())
            .setAge(properties.get("age").longValue())
            .setHeight(properties.get("height").longValue())
            .setWeight(properties.get("weight").longValue());

        try {
            builder.setRace(RaceManager.get(Races.valueOf(properties.get("race").asText())));
        } catch (RaceNotExiststException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }

        JsonNode eyeColorNode = properties.get("eyeColor");
        if (eyeColorNode.hasNonNull("R") && eyeColorNode.hasNonNull("G") && eyeColorNode.hasNonNull("B")) {
            Color eyeColor = new Color(eyeColorNode.get("R").asInt(), eyeColorNode.get("G").asInt(), eyeColorNode.get("B").asInt());
            builder.setEyeColor(eyeColor);
        }

        JsonNode hairColorNode = properties.get("hairColor");
        if (hairColorNode.hasNonNull("R") && hairColorNode.hasNonNull("G") && hairColorNode.hasNonNull("B")) {
            Color hairColor = new Color(hairColorNode.get("R").asInt(), hairColorNode.get("G").asInt(), hairColorNode.get("B").asInt());
            builder.setHairColor(hairColor);
        }

        try {
            return Character.newBuilder().setCharacterBase(builder.build());
        } catch (CharacterRaceNotSetException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }
}
