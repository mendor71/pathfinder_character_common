package com.mendor.types;

public enum AttributeType {
    STRENGTH, AGILITY, WISDOM, ENDURANCE, INTELLIGENCE, CHARISMA;

    public static AttributeType byName(String name) {
        for (AttributeType t: values()) {
            if (t.toString().equals(name)) {
                return t;
            }
        }
        return null;
    }
}
