package com.mendor71.pathfinder.common.skills;

import com.mendor71.pathfinder.common.types.SkillType;

public interface ISkillProvider {
    CharacterSkill getSkillByType(SkillType type);
}
