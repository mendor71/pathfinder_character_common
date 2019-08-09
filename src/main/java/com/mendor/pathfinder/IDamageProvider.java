package com.mendor.pathfinder;

import com.mendor.pathfinder.inventory.IInventoryItem;

public interface IDamageProvider extends IInventoryItem {
    DamageInstance doDamage(long value) throws UnsupportedOperationException;
    DamageInstance doDamage() throws UnsupportedOperationException;
    void setOwner(PathfinderCharacter character);
    void setTwoHanded(boolean value);
}
