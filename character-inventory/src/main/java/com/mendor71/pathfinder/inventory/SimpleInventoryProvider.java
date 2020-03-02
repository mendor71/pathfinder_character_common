package com.mendor71.pathfinder.inventory;



import com.mendor71.pathfinder.common.ICharacter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class SimpleInventoryProvider implements IInventoryProvider {
    private HashMap<ICharacter, HashSet<IInventoryItem>> characterItems = new HashMap<>();

    @Override
    public void addItem(ICharacter character, IInventoryItem item) {
        if (!characterItems.containsKey(character))
            characterItems.put(character, new HashSet<>());

        characterItems.get(character).add(item);
    }

    @Override
    public void removeItem(ICharacter character, IInventoryItem item) {
        if (characterItems.containsKey(character)) {
            characterItems.get(character).remove(item);
        }
    }

    @Override
    public void useItem(ICharacter character, IInventoryItem item) {

    }

    @Override
    public void setOnControl(ICharacter character, Set<IInventoryItem> itemSet) {
        if (!characterItems.containsKey(character))
            characterItems.put(character, new HashSet<>());

        itemSet.forEach(item -> { characterItems.get(character).add(item); });
    }

    @Override
    public Set<IInventoryItem> getInventoryItems(ICharacter character) {
        if (!characterItems.containsKey(character))
            return null;

        return characterItems.get(character);
    }
}
