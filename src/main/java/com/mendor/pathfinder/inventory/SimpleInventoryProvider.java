package com.mendor.pathfinder.inventory;

import com.mendor.pathfinder.PathfinderCharacter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class SimpleInventoryProvider implements IInventoryProvider {
    private HashMap<PathfinderCharacter, HashSet<IInventoryItem>> characterItems = new HashMap<>();

    @Override
    public void addItem(PathfinderCharacter character, IInventoryItem item) {
        if (!characterItems.containsKey(character))
            characterItems.put(character, new HashSet<>());

        characterItems.get(character).add(item);
    }

    @Override
    public void removeItem(PathfinderCharacter character, IInventoryItem item) {
        if (characterItems.containsKey(character)) {
            characterItems.get(character).remove(item);
        }
    }

    @Override
    public void useItem(PathfinderCharacter character, IInventoryItem item) {

    }

    @Override
    public void setOnControl(PathfinderCharacter character, Set<IInventoryItem> itemSet) {
        if (!characterItems.containsKey(character))
            characterItems.put(character, new HashSet<>());

        itemSet.forEach(item -> { characterItems.get(character).add(item); });
    }

    @Override
    public Set<IInventoryItem> getInventoryItems(PathfinderCharacter character) {
        if (!characterItems.containsKey(character))
            return null;

        return characterItems.get(character);
    }
}
