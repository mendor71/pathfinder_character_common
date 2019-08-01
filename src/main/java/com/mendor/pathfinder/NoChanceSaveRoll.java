package com.mendor.pathfinder;

public class NoChanceSaveRoll implements ISaveRoll {
    public boolean tryToSave(Character character) {
        return false;
    }
}
