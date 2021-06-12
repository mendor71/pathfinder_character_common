package com.mendor71.pathfinder.fight.damageproviders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mendor71.pathfinder.fight.IDamageProvider;

import java.io.*;
import java.util.HashMap;
import java.util.List;

public class WeaponFactory {
    private HashMap<String, PhysicalDamageProvider> weaponsStorage = new HashMap<>();
    private volatile static WeaponFactory factory;

    private WeaponFactory() throws IOException {
        InputStream isBase = getClass().getClassLoader().getResourceAsStream("src/main/resources/weapons.json");
        List<PhysicalDamageProvider> baseItems = loadFromSource(isBase);
        baseItems.forEach(v -> weaponsStorage.put(v.getName(), v));

        try {
            InputStream isAdditional = new FileInputStream(new File("src/main/resources/weapons.json"));
            List<PhysicalDamageProvider> additionalItems = loadFromSource(isAdditional);
            additionalItems.forEach(v -> weaponsStorage.put(v.getName(), v));
        } catch (FileNotFoundException ignored) {}
    }

    public static WeaponFactory getInstance() throws IOException {
        if (factory == null) {
            synchronized (WeaponFactory.class) {
                factory = new WeaponFactory();
            }
        }
        return factory;
    }

    public synchronized IDamageProvider getWeapon(String name) {
        return weaponsStorage.get(name).clone();
    }

    private List<PhysicalDamageProvider> loadFromSource(InputStream is) throws IOException {
        return new ObjectMapper().readValue(is, new TypeReference<List<PhysicalDamageProvider>>(){});
    }
}
