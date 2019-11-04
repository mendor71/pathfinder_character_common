package com.mendor.pathfinder.common.skills;

import com.mendor.pathfinder.common.types.SkillType;

public interface ISkillProvider {
    CharacterSkill getSkillByType(SkillType type);
}
