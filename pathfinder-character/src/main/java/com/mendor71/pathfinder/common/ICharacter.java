package com.mendor71.pathfinder.common;

import com.mendor71.pathfinder.common.attributes.IAttributeManager;
import com.mendor71.pathfinder.common.exceptions.CharacterSkillAlreadyExistsException;
import com.mendor71.pathfinder.common.exceptions.CharacterSkillListIllegalStateException;
import com.mendor71.pathfinder.common.pathfinderclasses.IClassManager;
import com.mendor71.pathfinder.common.skills.ISkillManager;

public interface ICharacter extends IAttributeManager, IClassManager, ISkillManager {

    void applyRaceBonuses() throws CharacterSkillListIllegalStateException, CharacterSkillAlreadyExistsException;
}
