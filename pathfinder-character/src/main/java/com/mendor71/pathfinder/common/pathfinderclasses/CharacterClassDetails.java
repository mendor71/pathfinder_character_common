package com.mendor71.pathfinder.common.pathfinderclasses;

import com.mendor71.pathfinder.common.PathfinderCharacter;

public class CharacterClassDetails {
    private Long id;
    private PathfinderCharacter character;
    private ICharacterClass characterClass;
    private long level;

    public CharacterClassDetails(ICharacterClass characterClass, long level) {
        this.characterClass = characterClass;
        this.level = level;
    }

    public Long getId() {
        return id == null ? -1 : id;
    }

    public PathfinderCharacter getCharacter() {
        return character;
    }

    public ICharacterClass getCharacterClass() {
        return characterClass;
    }

    public void increaseLevel(long value) {
        this.level += value;
    }

    public long getLevel() {
        return level;
    }

    public CharacterClassDetails setCharacter(PathfinderCharacter character) {
        this.character = character;
        return this;
    }

    public String getClassName() {
        return characterClass.getName();
    }
}
