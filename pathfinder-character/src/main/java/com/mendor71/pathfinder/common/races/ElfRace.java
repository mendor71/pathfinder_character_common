package com.mendor71.pathfinder.common.races;

import com.mendor71.pathfinder.common.bonus.AttributeCharacterBonus;
import com.mendor71.pathfinder.common.bonus.DescribedCharacterBonus;
import com.mendor71.pathfinder.common.bonus.ICharacterBonus;
import com.mendor71.pathfinder.common.bonus.SkillCharacterBonus;
import com.mendor71.pathfinder.common.types.AttributeType;
import com.mendor71.pathfinder.common.types.SkillType;

import java.util.Arrays;
import java.util.List;

public class ElfRace implements IRace {
    private static final String name = "ELF";

    @Override
    public List<ICharacterBonus> getBonuses() {
        return Arrays.asList(
                new AttributeCharacterBonus(AttributeType.AGILITY, 2, false)
                , new AttributeCharacterBonus(AttributeType.INTELLIGENCE, 2, false)
                , new AttributeCharacterBonus(AttributeType.ENDURANCE, -2, false)
                , new SkillCharacterBonus(SkillType.Perception, 2, false)
                , new DescribedCharacterBonus("+2 to Witchcraft checks when determining magic items")
                , new DescribedCharacterBonus("+2 Challenge Charm School Challenge")
                , new DescribedCharacterBonus("immunity to magic sleep")
        );
    }
}
