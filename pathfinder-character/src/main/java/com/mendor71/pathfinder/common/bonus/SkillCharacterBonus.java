package com.mendor71.pathfinder.common.bonus;

import com.mendor71.pathfinder.common.Character;
import com.mendor71.pathfinder.common.skills.CharacterSkillDetails;
import com.mendor71.pathfinder.common.types.SkillType;

public class SkillCharacterBonus extends AbstractCharacterBonus implements ICharacterBonus {
    private SkillType skillType;

    public SkillCharacterBonus(long value, boolean temporary) {
        super(value, temporary);
    }

    public SkillCharacterBonus(SkillType skillType, long value, boolean temporary) {
        super(value, temporary);
        this.skillType = skillType;
    }

    @Override
    public void apply(Character character) {
        CharacterSkillDetails characterSkill = character.getCharacterSkillDetailsByTypeOrThrowException(skillType);
        if (temporary)
            characterSkill.increaseTemporaryBonus(value);
        else
            characterSkill.increaseBonus(value);
    }
}
