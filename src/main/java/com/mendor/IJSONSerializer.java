package com.mendor;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.HashMap;

public interface IJSONSerializer {
    void preSerialize(PathfinderCharacter character);
    JsonNode getResult();
}
