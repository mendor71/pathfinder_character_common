package com.mendor.pathfinder.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.mendor.pathfinder.skills.CharacterSkill;
import com.mendor.pathfinder.skills.CharacterSkillDetails;
import com.mendor.pathfinder.PathfinderCharacter;
import com.mendor.pathfinder.skills.SimpleSkillProvider;
import com.mendor.pathfinder.types.SkillType;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CustomCharacterJSONDeserializerWithSkills extends CustomCharacterJSONDeserializer {
    public CustomCharacterJSONDeserializerWithSkills(Class<?> c) {
        super(c);
    }

    public CustomCharacterJSONDeserializerWithSkills(CustomCharacterJSONDeserializer baseDeserializer) {
        super(PathfinderCharacter.class);
        this.baseDeserializer = baseDeserializer;
    }

    @Override
    protected JsonNode getRoot() {
        return baseDeserializer.getRoot();
    }

    @Override
    public PathfinderCharacter deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        PathfinderCharacter character = baseDeserializer.deserialize(parser, context);
        Set<CharacterSkillDetails> detailsSet = new HashSet<>();

        JsonNode root = getRoot();
        JsonNode skills = root.get("skills");

        long freeSkillPoints = skills.get("skillPointsFree").longValue();
        long usedSkillPoints = skills.get("skillPointsUsed").longValue();

        character.setFreeAndUsedSkillPoints(freeSkillPoints, usedSkillPoints);

        ArrayNode list = (ArrayNode) skills.get("list");

        for (JsonNode skill: list) {

            SkillType type = SkillType.byName(skill.get("type").asText());
            CharacterSkill s = SimpleSkillProvider.getInstance().getSkillByType(type);
            CharacterSkillDetails skillDetails = new CharacterSkillDetails();

            skillDetails.setSkill(s);
            skillDetails.setValue(skill.get("value").longValue());
            skillDetails.setModifier(skill.get("modifier").longValue());
            skillDetails.setBonus(skill.get("bonus").longValue());
            skillDetails.setId(skill.get("id").longValue());

            detailsSet.add(skillDetails);
        }

        character.setSkillSet(detailsSet);
        return character;
    }
}
