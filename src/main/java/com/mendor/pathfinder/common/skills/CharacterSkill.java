package com.mendor.pathfinder.common.skills;

import com.mendor.pathfinder.common.types.AttributeType;
import com.mendor.pathfinder.common.types.SkillType;

public class CharacterSkill implements ICharacterSkill {
    private AttributeType attributeType;
    private SkillType skillType;
    private boolean useUntrained ;

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
