package com.mendor71.pathfinder.common.bonus;

import com.mendor71.pathfinder.common.Character;
import com.mendor71.pathfinder.common.exceptions.CharacterSkillAlreadyExistsException;
import com.mendor71.pathfinder.common.exceptions.CharacterSkillListIllegalStateException;

public interface ICharacterBonus {

    void apply(Character character) throws CharacterSkillListIllegalStateException, CharacterSkillAlreadyExistsException;

}
