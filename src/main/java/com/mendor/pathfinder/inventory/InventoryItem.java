package com.mendor.pathfinder.inventory;

public class InventoryItem implements IInventoryItem {
    protected String name;
    protected String description;
    protected double buyCost;
    protected double sellCost;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double getBuyCost() {
        return buyCost;
    }


    @Override
    public double getSellCost() {
        return sellCost;
    }

    @Override
    public String toString() {
        return "InventoryItem{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", buyCost=" + buyCost +
                ", sellCost=" + sellCost +
                '}';
    }
}