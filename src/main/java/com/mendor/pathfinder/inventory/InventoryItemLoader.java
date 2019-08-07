package com.mendor.pathfinder.inventory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class InventoryItemLoader {

    public static List<InventoryItem> deserialize(File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(file, new TypeReference<List<InventoryItem>>(){});
    }

    public static List<InventoryItem> deserialize(String serializedData) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(serializedData, new TypeReference<List<InventoryItem>>(){});
    }
}
