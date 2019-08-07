import com.mendor.pathfinder.PathfinderCharacter;
import com.mendor.pathfinder.damageproviders.LongSword;
import com.mendor.pathfinder.inventory.IInventoryProvider;
import com.mendor.pathfinder.inventory.InventoryItem;
import com.mendor.pathfinder.inventory.InventoryItemLoader;
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
    public void testInventoryManager() {
        PathfinderCharacter character = new PathfinderCharacter();

        IInventoryProvider inventoryProvider = new SimpleInventoryProvider();

        inventoryProvider.addItem(character, new LongSword());

        inventoryProvider.getInventoryItems(character).forEach(System.out::println);
    }

    @Test
    public void testDeserializeInventoryItems() throws IOException {
        List<InventoryItem> deserializedData = InventoryItemLoader.deserialize("[{\"name\":\"rope\", \"description\":\"fix someone\", \"buyCost\":1.0, \"sellCost\":0.1}]");
        assertEquals(deserializedData.size(), 1);
    }
}

