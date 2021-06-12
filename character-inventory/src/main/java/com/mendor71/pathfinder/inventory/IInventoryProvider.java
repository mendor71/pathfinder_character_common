package com.mendor71.pathfinder.inventory;

import com.mendor71.pathfinder.common.ICharacter;

import java.util.Set;

public interface IInventoryProvider {
    void addItem(ICharacter character, IInventoryItem item);
    void removeItem(ICharacter character, IInventoryItem item);
    void useItem(ICharacter character, IInventoryItem item);
    void setOnControl(ICharacter character, Set<IInventoryItem> itemSet);

    Set<IInventoryItem> getInventoryItems(ICharacter character);
}
