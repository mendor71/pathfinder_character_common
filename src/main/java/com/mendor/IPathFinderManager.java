package com.mendor;

import java.util.List;

public interface IPathFinderManager {
    <T> void setOnControl(PathfinderCharacter character, List<T> controlObjects);
}
