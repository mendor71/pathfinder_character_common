package com.mendor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.HashMap;

public interface IJSONSerializer {
    ObjectNode serialize(PathfinderCharacter character);
}
