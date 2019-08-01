package com.mendor;

public interface ICharacterRepository {
    void add(PathfinderCharacter character);
    void update(PathfinderCharacter character);
    void remove(PathfinderCharacter character);
    void find();
}
