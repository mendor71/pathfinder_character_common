package com.mendor.pathfinder.common.skills;

import com.mendor.pathfinder.common.types.AttributeType;
import com.mendor.pathfinder.common.types.SkillType;

public interface ICharacterSkill {
    AttributeType getAttributeType();
    SkillType getSkillType();
    boolean useUntrained();
}
