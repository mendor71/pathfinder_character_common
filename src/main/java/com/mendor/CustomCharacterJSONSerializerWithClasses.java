package com.mendor;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Set;

public class CustomCharacterJSONSerializerWithClasses extends CustomCharacterJSONSerializer {
    public CustomCharacterJSONSerializerWithClasses(IJSONSerializer basePreSerializer) {
        super(basePreSerializer);
    }

    @Override
    public void preSerialize(PathfinderCharacter character) {
        super.preSerialize(character);
        Set<CharacterClassDetails> characterClasses = character.getCharacterClasses();


        ArrayNode classes = root.putArray("classes");
        for (CharacterClassDetails d: characterClasses) {
            ObjectNode cl = classes.addObject();
            cl.put("name", d.getClassName());
            cl.put("level", d.getLevel());

        }
    }
}
