package com.mendor71.pathfinder.common.util;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import com.mendor71.pathfinder.common.Character;
import com.mendor71.pathfinder.common.exceptions.RaceNotExiststException;
import com.mendor71.pathfinder.common.races.RaceManager;

import java.awt.*;

public class CustomCharacterJSONSerializer implements IJSONSerializer {

    protected IJSONSerializer basePreSerializer;

    public CustomCharacterJSONSerializer() {}

    public CustomCharacterJSONSerializer(IJSONSerializer basePreSerializer) {
        this.basePreSerializer = basePreSerializer;
    }

    @Override
    public ObjectNode serialize(Character character) throws RaceNotExiststException {
        return appendCharacterData(character);
    }

    private ObjectNode appendCharacterData(Character character) throws RaceNotExiststException {
        ObjectNode root = JsonNodeFactory.instance.objectNode();
        ObjectNode properties = root.putObject("characterProperties");

        properties.put("uuid", character.getUuid());
        properties.put("name", character.getName());
        properties.put("level", character.getLevel());
        properties.put("age", character.getAge());
        properties.put("armorClass", character.getArmorClass());
        properties.put("height", character.getHeight());
        properties.put("weight", character.getWeight());
        properties.put("race", RaceManager.getNameByRace(character.getRace()).toString());

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
        return root;
    }
}
