package com.mendor71.pathfinder.common.races;

import com.mendor71.pathfinder.common.bonus.ICharacterBonus;

import java.util.List;

public class HumanRace implements IRace {
    private static HumanRace instance;

    private HumanRace() { }

    public static HumanRace getInstance() {
        if (instance == null)
            instance = new HumanRace();
        return instance;
    }

    @Override
    public List<ICharacterBonus> getBonuses() {
        return null;
    }
}
