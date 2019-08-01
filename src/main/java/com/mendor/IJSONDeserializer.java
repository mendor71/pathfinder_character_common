package com.mendor;

import com.fasterxml.jackson.databind.JsonNode;

public interface IJSONDeserializer {
    PathfinderCharacter deserialize(JsonNode source);
}
