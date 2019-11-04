package com.mendor71.pathfinder.common.util;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mendor71.pathfinder.common.skills.CharacterSkillDetails;
import com.mendor71.pathfinder.common.PathfinderCharacter;

import java.util.Set;

public class CustomCharacterJSONSerializerWithSkills extends CustomCharacterJSONSerializer {

    public CustomCharacterJSONSerializerWithSkills(IJSONSerializer basePreSerializer) {
        super(basePreSerializer);
    }

    @Override
    public ObjectNode serialize(PathfinderCharacter character) {
        ObjectNode root = basePreSerializer.serialize(character);

        Set<CharacterSkillDetails> characterSkills = character.getSkillSet();

        ObjectNode skills = root.putObject("skills");

        skills.put("skillPointsFree", character.getFreeSkillPoints());
        skills.put("skillPointsUsed", character.getUsedSkillPoints());

        ArrayNode list = skills.putArray("list");

        for (CharacterSkillDetails d: characterSkills) {
            ObjectNode skill = list.addObject();

            skill.put("type", d.getSkillType().toString());
            skill.put("value", d.getValue());
            skill.put("modifier", d.getModifier());
            skill.put("bonus", d.getBonus());
            skill.put("id", d.getId());
        }
        return root;
    }
}
