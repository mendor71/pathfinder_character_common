package com.mendor;

import com.mendor.types.SkillType;

public interface ISkillProvider {
    CharacterSkill getSkillByType(SkillType type);
}
