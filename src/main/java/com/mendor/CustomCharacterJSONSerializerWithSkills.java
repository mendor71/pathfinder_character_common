package com.mendor;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Set;

public class CustomCharacterJSONSerializerWithSkills extends CustomCharacterJSONSerializer {

    public CustomCharacterJSONSerializerWithSkills(IJSONSerializer basePreSerializer) {
        super(basePreSerializer);
    }

    @Override
    public void preSerialize(PathfinderCharacter character) {
        super.preSerialize(character);

        Set<CharacterSkillDetails> characterSkills = character.getSkillSet();

        ArrayNode skills = root.putArray("skills");

        for (CharacterSkillDetails d: characterSkills) {
            ObjectNode skill = skills.addObject();
            skill.put("type", d.getSkillType().toString());
            skill.put("value", d.getValue());
            skill.put("modifier", d.getModifier());
            skill.put("bonus", d.getBonus());
        }
    }
}
