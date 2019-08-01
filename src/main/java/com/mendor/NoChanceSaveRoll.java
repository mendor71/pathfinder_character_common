package com.mendor;

import com.mendor.ISaveRoll;

public class NoChanceSaveRoll implements ISaveRoll {
    public boolean tryToSave(Character character) {
        return false;
    }
}
