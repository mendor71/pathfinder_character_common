package com.mendor.pathfinder.skills;

import com.mendor.pathfinder.types.AttributeType;
import com.mendor.pathfinder.types.SkillType;

public interface ICharacterSkill {
    AttributeType getAttributeType();
    SkillType getSkillType();
    boolean useUntrained();
}
