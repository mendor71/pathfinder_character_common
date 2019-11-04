package com.mendor71.pathfinder.fight;

import com.mendor71.pathfinder.common.PathfinderCharacter;
import com.mendor71.pathfinder.inventory.IInventoryItem;

public interface IDamageProvider extends IInventoryItem {
    DamageInstance doDamage(long value) throws UnsupportedOperationException;
    DamageInstance doDamage() throws UnsupportedOperationException;
    void setOwner(PathfinderCharacter character);
    void setTwoHanded(boolean value);
}
