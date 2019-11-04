package com.mendor71.pathfinder.common.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.mendor71.pathfinder.common.PathfinderCharacter;

public interface IJSONDeserializer {
    PathfinderCharacter deserialize(JsonNode source);
}
