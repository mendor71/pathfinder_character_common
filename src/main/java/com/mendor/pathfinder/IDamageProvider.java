package com.mendor.pathfinder;

public interface IDamageProvider {
    DamageInstance doDamage(long value) throws UnsupportedOperationException;
    DamageInstance doDamage() throws UnsupportedOperationException;
    void setOwner(PathfinderCharacter character);
    void setTwoHanded(boolean value);
}
