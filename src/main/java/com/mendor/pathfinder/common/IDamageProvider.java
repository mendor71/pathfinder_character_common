package com.mendor.pathfinder.common;

import com.mendor.pathfinder.common.inventory.IInventoryItem;

public interface IDamageProvider extends IInventoryItem {
    DamageInstance doDamage(long value) throws UnsupportedOperationException;
    DamageInstance doDamage() throws UnsupportedOperationException;
    void setOwner(PathfinderCharacter character);
    void setTwoHanded(boolean value);
}
