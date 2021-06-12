package com.mendor71.pathfinder.common.bonus;

import com.mendor71.pathfinder.common.Character;
import com.mendor71.pathfinder.common.exceptions.CharacterSkillAlreadyExistsException;
import com.mendor71.pathfinder.common.exceptions.CharacterSkillListIllegalStateException;
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
    public void apply(Character character) throws CharacterSkillListIllegalStateException, CharacterSkillAlreadyExistsException {
        if (!character.containsCharacterSkill(skillType))
            character.addCharacterSkill(skillType);

        if (temporary)
            character.increaseSkillTemporaryModifierValue(skillType, value);
        else
            character.increaseSkillStableBonusValue(skillType,value);
    }
}
