import com.mendor.pathfinder.PathfinderCharacter;
import com.mendor.pathfinder.damageproviders.WeaponFactory;
import com.mendor.pathfinder.inventory.IInventoryProvider;
import com.mendor.pathfinder.inventory.InventoryItem;
import com.mendor.pathfinder.inventory.InventoryItemFactory;
import com.mendor.pathfinder.inventory.SimpleInventoryProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class InventoryTest {
    @Test
    public void testInventoryManager() throws IOException, CloneNotSupportedException {
        PathfinderCharacter character = new PathfinderCharacter();

        IInventoryProvider inventoryProvider = new SimpleInventoryProvider();

        inventoryProvider.addItem(character, WeaponFactory.getInstance().getWeapon("LongSword"));
        inventoryProvider.addItem(character, InventoryItemFactory.getInstance().getInventoryItem("SmallHealPotion"));
        inventoryProvider.addItem(character, InventoryItemFactory.getInstance().getInventoryItem("HealPotion"));

        inventoryProvider.getInventoryItems(character).forEach(System.out::println);
    }

}

