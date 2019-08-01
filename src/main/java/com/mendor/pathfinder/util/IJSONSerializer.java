package com.mendor.pathfinder.util;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mendor.pathfinder.PathfinderCharacter;

public interface IJSONSerializer {
    ObjectNode serialize(PathfinderCharacter character);
}
