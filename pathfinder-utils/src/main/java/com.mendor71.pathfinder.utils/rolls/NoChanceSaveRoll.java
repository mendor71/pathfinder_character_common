package com.mendor71.pathfinder.utils.rolls;

public class NoChanceSaveRoll implements ISaveRoll {
    public boolean tryToSave(Character character) {
        return false;
    }
}
