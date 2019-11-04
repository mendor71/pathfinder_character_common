package com.mendor71.pathfinder.common.util;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mendor71.pathfinder.common.PathfinderCharacter;

public interface IJSONSerializer {
    ObjectNode serialize(PathfinderCharacter character);
}
