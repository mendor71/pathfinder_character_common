package com.mendor.pathfinder.damageproviders;

import com.mendor.pathfinder.DamageInstance;
import com.mendor.pathfinder.inventory.IInventoryItem;
import com.mendor.pathfinder.inventory.InventoryItem;

public class TwoHandedAxe extends AbstractPhysicalDamageProvider implements IInventoryItem {



    @Override
    public DamageInstance doDamage(long value) throws UnsupportedOperationException {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public double getBuyCost() {
        return 0;
    }

    @Override
    public double getSellCost() {
        return 0;
    }
}
