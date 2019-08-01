package com.mendor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.awt.*;

public class CustomCharacterJSONSerializer implements IJSONSerializer {
    protected ObjectNode root = JsonNodeFactory.instance.objectNode();
    protected IJSONSerializer basePreSerializer;

    public CustomCharacterJSONSerializer() {}

    public CustomCharacterJSONSerializer(IJSONSerializer basePreSerializer) {
        this.basePreSerializer = basePreSerializer;
    }

    @Override
    public void preSerialize(PathfinderCharacter character) {
        appendCharacterData(character);
    }

    @Override
    public JsonNode getResult() {
        return root;
    }

    private void appendCharacterData(PathfinderCharacter character) {
        ObjectNode properties = root.putObject("characterProperties");

        properties.put("uuid", character.getUUID());
        properties.put("name", character.getName());
        properties.put("level", character.getLevel());
        properties.put("age", character.getAge());
        properties.put("armorClass", character.getArmorClass());
        properties.put("height", character.getHeight().toString());
        properties.put("weight", character.getWeight().toString());

        ObjectNode eyeColorNode = properties.putObject("eyeColor");
        Color eyeColor = character.getEyeColor();
        if (eyeColor != null) {
            eyeColorNode.put("R", eyeColor.getRed());
            eyeColorNode.put("G", eyeColor.getGreen());
            eyeColorNode.put("B", eyeColor.getBlue());
        }

        ObjectNode hairColorNode = properties.putObject("hairColor");
        Color hairColor = character.getHairColor();
        if (hairColor != null) {
            hairColorNode.put("R", hairColor.getRed());
            hairColorNode.put("G", hairColor.getGreen());
            hairColorNode.put("B", hairColor.getBlue());
        }
    }
}
