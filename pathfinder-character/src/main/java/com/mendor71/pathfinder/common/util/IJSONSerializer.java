package com.mendor71.pathfinder.common.util;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mendor71.pathfinder.common.Character;
import com.mendor71.pathfinder.common.exceptions.RaceNotExiststException;

public interface IJSONSerializer {
    ObjectNode serialize(Character character) throws RaceNotExiststException;
}
