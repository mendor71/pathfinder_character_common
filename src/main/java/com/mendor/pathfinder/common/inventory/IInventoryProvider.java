package com.mendor.pathfinder.common.inventory;

import com.mendor.pathfinder.common.PathfinderCharacter;

import java.util.Set;

public interface IInventoryProvider {
    void addItem(PathfinderCharacter character, IInventoryItem item);
    void removeItem(PathfinderCharacter character, IInventoryItem item);
    void useItem(PathfinderCharacter character, IInventoryItem item);
    void setOnControl(PathfinderCharacter character, Set<IInventoryItem> itemSet);

    Set<IInventoryItem> getInventoryItems(PathfinderCharacter character);
}
