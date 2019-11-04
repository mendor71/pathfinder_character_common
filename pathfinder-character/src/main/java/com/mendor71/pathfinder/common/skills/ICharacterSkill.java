package com.mendor71.pathfinder.common.skills;

import com.mendor71.pathfinder.common.types.AttributeType;
import com.mendor71.pathfinder.common.types.SkillType;

public interface ICharacterSkill {
    AttributeType getAttributeType();
    SkillType getSkillType();
    boolean useUntrained();
}
