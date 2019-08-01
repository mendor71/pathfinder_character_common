package com.mendor.pathfinder.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.mendor.pathfinder.PathfinderCharacter;

public interface IJSONDeserializer {
    PathfinderCharacter deserialize(JsonNode source);
}
