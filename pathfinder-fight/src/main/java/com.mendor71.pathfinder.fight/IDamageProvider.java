package com.mendor71.pathfinder.fight;

import com.mendor71.pathfinder.common.ICharacter;
import com.mendor71.pathfinder.inventory.IInventoryItem;

public interface IDamageProvider extends IInventoryItem {
    DamageInstance doDamage(long value) throws UnsupportedOperationException;
    DamageInstance doDamage() throws UnsupportedOperationException;
    void setOwner(ICharacter character);
    void setTwoHanded(boolean value);
}
