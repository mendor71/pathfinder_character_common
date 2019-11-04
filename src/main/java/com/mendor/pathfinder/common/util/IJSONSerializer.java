package com.mendor.pathfinder.common.util;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mendor.pathfinder.common.PathfinderCharacter;

public interface IJSONSerializer {
    ObjectNode serialize(PathfinderCharacter character);
}
