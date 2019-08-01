package com.mendor.pathfinder.util;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mendor.pathfinder.pathfinderclasses.CharacterClassDetails;
import com.mendor.pathfinder.PathfinderCharacter;

import java.util.Set;

public class CustomCharacterJSONSerializerWithClasses extends CustomCharacterJSONSerializer {
    public CustomCharacterJSONSerializerWithClasses(IJSONSerializer basePreSerializer) {
        super(basePreSerializer);
    }

    @Override
    public ObjectNode serialize(PathfinderCharacter character) {
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
