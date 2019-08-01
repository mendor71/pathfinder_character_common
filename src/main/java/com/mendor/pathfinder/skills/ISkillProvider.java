package com.mendor.pathfinder.skills;

import com.mendor.pathfinder.types.SkillType;

public interface ISkillProvider {
    CharacterSkill getSkillByType(SkillType type);
}
