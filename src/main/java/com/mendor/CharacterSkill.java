package com.mendor;

import com.mendor.types.AttributeType;
import com.mendor.types.SkillType;

public class CharacterSkill implements ICharacterSkill {
    private AttributeType attributeType;
    private SkillType skillType;
    private boolean useUntrained ;
    public static final long defaultClassBonus = 3;

    public CharacterSkill(AttributeType attributeType, SkillType skillType, boolean useUntrained) {
        this.attributeType = attributeType;
        this.skillType = skillType;
        this.useUntrained = useUntrained;
    }

    @Override
    public AttributeType getAttributeType() {
        return attributeType;
    }

    @Override
    public SkillType getSkillType() {
        return skillType;
    }

    @Override
    public boolean useUntrained() {
        return useUntrained;
    }
}
