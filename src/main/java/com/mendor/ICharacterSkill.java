package com.mendor;

import com.mendor.types.AttributeType;
import com.mendor.types.SkillType;

public interface ICharacterSkill {
    AttributeType getAttributeType();
    SkillType getSkillType();
    boolean useUntrained();
}
