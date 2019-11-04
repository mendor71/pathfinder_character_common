package com.mendor71.pathfinder.inventory;

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

    public IInventoryItem clone()  {
        InventoryItem copy = new InventoryItem();
        copy.buyCost = this.buyCost;
        copy.sellCost = this.sellCost;
        copy.description = this.description;
        copy.name = this.name;

        return copy;
    }
}