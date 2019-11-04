package com.mendor.pathfinder.common.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.mendor.pathfinder.common.PathfinderCharacter;

public interface IJSONDeserializer {
    PathfinderCharacter deserialize(JsonNode source);
}
