package com.mendor71.pathfinder.common.races;

import com.mendor71.pathfinder.common.bonus.ICharacterBonus;

import java.util.List;

public interface IRace {
    List<ICharacterBonus> getBonuses();
}
