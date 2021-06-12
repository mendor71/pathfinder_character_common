package com.mendor71.pathfinder.common.util;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mendor71.pathfinder.common.exceptions.RaceNotExiststException;
import com.mendor71.pathfinder.common.pathfinderclasses.CharacterClassDetails;
import com.mendor71.pathfinder.common.Character;

import java.util.Set;

public class CustomCharacterJSONSerializerWithClasses extends CustomCharacterJSONSerializer {
    public CustomCharacterJSONSerializerWithClasses(IJSONSerializer basePreSerializer) {
        super(basePreSerializer);
    }

    @Override
    public ObjectNode serialize(Character character) throws RaceNotExiststException {
        ObjectNode root = basePreSerializer.serialize(character);
        Set<CharacterClassDetails> characterClasses = character.getCharacterClasses();


        ArrayNode classes = root.putArray("classes");
        for (CharacterClassDetails d: characterClasses) {
            ObjectNode cl = classes.addObject();
            cl.put("name", d.getClassName());
            cl.put("level", d.getLevel());

        }
        return root;
    }
}
