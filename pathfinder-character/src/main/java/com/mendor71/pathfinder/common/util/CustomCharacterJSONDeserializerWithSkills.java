package com.mendor71.pathfinder.common.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.mendor71.pathfinder.common.skills.CharacterSkill;
import com.mendor71.pathfinder.common.skills.CharacterSkillDetails;
import com.mendor71.pathfinder.common.skills.PersonifiedSkillManager;
import com.mendor71.pathfinder.common.skills.SimpleSkillProvider;
import com.mendor71.pathfinder.common.types.SkillType;
import com.mendor71.pathfinder.common.Character;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CustomCharacterJSONDeserializerWithSkills extends CustomCharacterJSONDeserializer {
    public CustomCharacterJSONDeserializerWithSkills(Class<?> c) {
        super(c);
    }

    public CustomCharacterJSONDeserializerWithSkills(CustomCharacterJSONDeserializer baseDeserializer) {
        super(Character.Builder.class);
        this.baseDeserializer = baseDeserializer;
    }

    @Override
    protected JsonNode getRoot() {
        return baseDeserializer.getRoot();
    }

    @Override
    public Character.Builder deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        Character.Builder builder = baseDeserializer.deserialize(parser, context);
        Set<CharacterSkillDetails> detailsSet = new HashSet<>();

        JsonNode root = getRoot();
        JsonNode skills = root.get("skills");

        ArrayNode list = (ArrayNode) skills.get("list");

        for (JsonNode skill: list) {
            SkillType type = SkillType.byName(skill.get("type").asText());

            CharacterSkill s = SimpleSkillProvider.getInstance().getSkillByType(type);
            CharacterSkillDetails skillDetails = new CharacterSkillDetails();

            skillDetails.setSkill(s);
            skillDetails.setTrainedPoints(skill.get("trainedPoints").longValue());
            skillDetails.setTemporarySkillValueModifier(skill.get("temporarySkillValueModifier").longValue());
            skillDetails.setStableBonus(skill.get("stableBonus").longValue());
            skillDetails.setId(skill.get("id").longValue());
            skillDetails.setAttributeModifier(skill.get("attributeModifier").longValue());

            detailsSet.add(skillDetails);
        }

        builder.manageSkills(new PersonifiedSkillManager(builder.getUuid()), detailsSet);
        return builder;
    }
}
