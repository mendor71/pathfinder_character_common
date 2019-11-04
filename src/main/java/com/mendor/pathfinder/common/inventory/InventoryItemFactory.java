package com.mendor.pathfinder.common.inventory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryItemFactory {
    private Map<String, InventoryItem> inventoryItemStorage = new HashMap<>();
    private volatile static InventoryItemFactory factory;

    private InventoryItemFactory() throws IOException {
        InputStream isBase = getClass().getClassLoader().getResourceAsStream("inventory_items.json");
        List<InventoryItem> baseItems = loadFromSource(isBase);
        baseItems.forEach(v -> inventoryItemStorage.put(v.getName(), v));

        try {
            InputStream isAdditional = new FileInputStream(new File("inventory_items.json"));
            List<InventoryItem> additionalItems = loadFromSource(isAdditional);
            additionalItems.forEach(v -> inventoryItemStorage.put(v.getName(), v));
        } catch (FileNotFoundException ignored) {}
    }

    public static InventoryItemFactory getInstance() throws IOException {
        if (factory == null) {
            synchronized (InventoryItemFactory.class) {
                factory = new InventoryItemFactory();
            }
        }

        return factory;
    }

    public IInventoryItem getInventoryItem(String name) {
        return inventoryItemStorage.get(name).clone();
    }

    private List<InventoryItem> loadFromSource(InputStream is) throws IOException {
       return new ObjectMapper().readValue(is, new TypeReference<List<InventoryItem>>(){});
    }
}
