package com.mendor.pathfinder;

public interface IDamageProvider {
    DamageInstance doDamage(long value) throws UnsupportedOperationException;
    DamageInstance doDamage() throws UnsupportedOperationException;
    void setStrengthCharacterModifier(long value);
    void setAgilityCharacterModifier(long value);
    void setTwoHanded(boolean value);
}
