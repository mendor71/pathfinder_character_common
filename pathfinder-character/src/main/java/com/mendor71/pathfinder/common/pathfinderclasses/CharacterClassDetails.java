package com.mendor71.pathfinder.common.pathfinderclasses;

public class CharacterClassDetails {
    private Long id;
    private ICharacterClass characterClass;
    private long level;

    public CharacterClassDetails(ICharacterClass characterClass, long level) {
        this.characterClass = characterClass;
        this.level = level;
    }

    public Long getId() {
        return id == null ? -1 : id;
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

    public String getClassName() {
        return characterClass.getName();
    }
}
