package com.mendor71.pathfinder.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.mendor71.pathfinder.common.Character;
import com.mendor71.pathfinder.common.exceptions.CharacterSkillAlreadyExistsException;
import com.mendor71.pathfinder.common.exceptions.CharacterSkillListIllegalStateException;

import java.io.IOException;

public class JsonDeserializationService {

    private final CustomCharacterJSONDeserializer dBase = new CustomCharacterJSONDeserializer(Character.Builder.class);
    private final CustomCharacterJSONDeserializer dWithClasses = new CustomCharacterJSONDeserializerWithClasses(dBase);
    private final CustomCharacterJSONDeserializer dWithAttributes = new CustomCharacterJSONDeserializerWihAttributes(dWithClasses);
    private final CustomCharacterJSONDeserializer dWithSkills = new CustomCharacterJSONDeserializerWithSkills(dWithAttributes);

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final SimpleModule module = new SimpleModule();


    public JsonDeserializationService() {
        module.addDeserializer(Character.Builder.class, dWithSkills);
        objectMapper.registerModule(module);
    }

    public Character deserialize(String value) throws IOException, CharacterSkillListIllegalStateException, CharacterSkillAlreadyExistsException {
        Character.Builder builder = objectMapper.readValue(value, Character.Builder.class);
        return builder.build();
    }
}
