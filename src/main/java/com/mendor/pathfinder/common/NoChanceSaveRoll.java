package com.mendor.pathfinder.common;

public class NoChanceSaveRoll implements ISaveRoll {
    public boolean tryToSave(Character character) {
        return false;
    }
}
