package com.mendor71.pathfinder.common.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.mendor71.pathfinder.common.Character;

public interface IJSONDeserializer {
    Character deserialize(JsonNode source);
}
