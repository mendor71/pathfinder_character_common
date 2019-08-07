import com.mendor.pathfinder.DamageInstance;
import com.mendor.pathfinder.IDamageProvider;
import com.mendor.pathfinder.PathfinderCharacter;
import com.mendor.pathfinder.damageproviders.TwoHandedAxe;
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
public class DamageProviderTest {
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

        IDamageProvider longSword = new LongSword();
        longSword.setTwoHanded(true);
        longSword.setOwner(weaponOwner);

        DamageInstance longSwordDamageInstance = longSword.doDamage(RandomRoll.roll(20, 3));
        assertTrue(longSwordDamageInstance.getDamageValue() > 3);

        IDamageProvider twoHandedAxe = new TwoHandedAxe();
        twoHandedAxe.setOwner(weaponOwner);

        DamageInstance twoHandedAxeDamageInstance = twoHandedAxe.doDamage(RandomRoll.roll(20, 5));
        assertTrue(twoHandedAxeDamageInstance.getDamageValue() > 3);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testLongSwordShouldThrowsException() {
        final IDamageProvider damageProvider = new LongSword();

        damageProvider.doDamage();
    }
}
