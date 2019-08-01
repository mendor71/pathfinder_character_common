import com.mendor.DamageInstance;
import com.mendor.IDamageProvider;
import com.mendor.RandomRoll;
import com.mendor.damageproviders.LongSword;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class LongSwordDamageProviderTest {
    @Test
    public void testLongSwordDamageValueIsMoreThenZero() {
        IDamageProvider damageProvider = new LongSword();

        damageProvider.setStrengthCharacterModifier(3);
        damageProvider.setAgilityCharacterModifier(1);
        damageProvider.setTwoHanded(true);
        DamageInstance damageInstance = damageProvider.doDamage(RandomRoll.roll(20, 3));

        assertTrue(damageInstance.getDamageValue() > 0);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testLongSwordShouldThrowsException() {
        final IDamageProvider damageProvider = new LongSword();

        damageProvider.setStrengthCharacterModifier(3);
        damageProvider.setAgilityCharacterModifier(1);

        damageProvider.doDamage();
    }
}
