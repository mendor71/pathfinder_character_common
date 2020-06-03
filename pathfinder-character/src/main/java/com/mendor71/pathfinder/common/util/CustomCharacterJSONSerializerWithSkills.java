package com.mendor71.pathfinder.common.util;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mendor71.pathfinder.common.exceptions.RaceNotExiststException;
import com.mendor71.pathfinder.common.skills.CharacterSkill;
import com.mendor71.pathfinder.common.Character;

import java.util.Set;

public class CustomCharacterJSONSerializerWithSkills extends CustomCharacterJSONSerializer {

    public CustomCharacterJSONSerializerWithSkills(IJSONSerializer basePreSerializer) {
        super(basePreSerializer);
    }

    @Override
    public ObjectNode serialize(Character character) throws RaceNotExiststException {
        ObjectNode root = basePreSerializer.serialize(character);

        Set<CharacterSkill> characterSkills = character.getCharacterSkillDetailsSet();

        ObjectNode skills = root.putObject("skills");

        skills.put("skillPointsFree", character.getFreeSkillPoints());
        skills.put("skillPointsUsed", character.getUsedSkillPoints());

        ArrayNode list = skills.putArray("list");

        for (CharacterSkill d: characterSkills) {
            ObjectNode skill = list.addObject();

            skill.put("type", d.getSkillType().toString());
            skill.put("trainedPoints", d.getTrainedPoints());
            skill.put("temporarySkillValueModifier", d.getTemporarySkillValueModifier());
            skill.put("stableBonus", d.getStableBonus());
            skill.put("attributeModifier", d.getAttributeModifier());
            skill.put("id", d.getId());
            skill.put("attributeType", d.getSkill().getAttributeType().toString());
        }
        return root;
    }
}
