package com.mendor71.pathfinder.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mendor71.pathfinder.common.Character;
import com.mendor71.pathfinder.common.exceptions.RaceNotExiststException;

public class JsonSerializationService {
    private final ObjectMapper mapper = new ObjectMapper();

    private final IJSONSerializer sBase = new CustomCharacterJSONSerializer();
    private final IJSONSerializer sWithClasses = new CustomCharacterJSONSerializerWithClasses(sBase);
    private final IJSONSerializer sWithAttributes = new CustomCharacterJSONSerializerWithAttributes(sWithClasses);

    private final IJSONSerializer mainSerializer = new CustomCharacterJSONSerializerWithSkills(sWithAttributes);

    public String serialize(Character character) throws JsonProcessingException, RaceNotExiststException {
        return mapper.writeValueAsString(mainSerializer.serialize(character));
    }
}
