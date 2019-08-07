import com.mendor.pathfinder.DamageInstance;
import com.mendor.pathfinder.IDamageProvider;
import com.mendor.pathfinder.PathfinderCharacter;
import com.mendor.pathfinder.types.AttributeType;
import com.mendor.pathfinder.util.RandomRoll;
import com.mendor.pathfinder.damageproviders.LongSword;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LongSwordDamageProviderTest {
    @Mock PathfinderCharacter weaponOwner;

    @Test(expected = IllegalStateException.class)
    public void testWeaponProviderNotSetWeaponOwnerThrowsException() {
        IDamageProvider damageProvider = new LongSword();

        damageProvider.setTwoHanded(true);
        DamageInstance damageInstance = damageProvider.doDamage(RandomRoll.roll(20, 3));

        assertTrue(damageInstance.getDamageValue() > 0);
    }

    @Test
    public void testWeaponProvider() {
        when(weaponOwner.getAttributeModifier(AttributeType.STRENGTH)).thenReturn(3L);

        IDamageProvider damageProvider = new LongSword();

        damageProvider.setTwoHanded(true);
        damageProvider.setOwner(weaponOwner);

        DamageInstance damageInstance = damageProvider.doDamage(RandomRoll.roll(20, 3));

        assertTrue(damageInstance.getDamageValue() > 3);

    }

    @Test(expected = UnsupportedOperationException.class)
    public void testLongSwordShouldThrowsException() {
        final IDamageProvider damageProvider = new LongSword();

        damageProvider.doDamage();
    }
}
