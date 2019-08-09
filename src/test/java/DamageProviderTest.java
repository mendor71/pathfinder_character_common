import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mendor.pathfinder.DamageInstance;
import com.mendor.pathfinder.IDamageProvider;
import com.mendor.pathfinder.PathfinderCharacter;
import com.mendor.pathfinder.damageproviders.PhysicalDamageProvider;
import com.mendor.pathfinder.damageproviders.WeaponFactory;
import com.mendor.pathfinder.types.AttributeType;
import com.mendor.pathfinder.types.DamageType;
import com.mendor.pathfinder.util.RandomRoll;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DamageProviderTest {
    @Mock PathfinderCharacter weaponOwner;

    @Test
    public void testWeaponFactory() throws IOException {
        IDamageProvider damageProvider1 = WeaponFactory.getInstance().getWeapon("LongSword");
        IDamageProvider damageProvider2 = WeaponFactory.getInstance().getWeapon("ShortSword");
        assertNotNull(damageProvider1);
        assertNotNull(damageProvider2);
    }

    @Test(expected = IllegalStateException.class)
    public void testWeaponProviderNotSetWeaponOwnerThrowsException() throws IOException {
        IDamageProvider damageProvider = WeaponFactory.getInstance().getWeapon("LongSword");

        damageProvider.setTwoHanded(true);
        DamageInstance damageInstance = damageProvider.doDamage(RandomRoll.roll(20, 3));

        assertTrue(damageInstance.getDamageValue() > 0);
    }

    @Test
    public void testWeaponProvider() throws IOException {
        when(weaponOwner.getAttributeModifier(AttributeType.STRENGTH)).thenReturn(3L);

        IDamageProvider longSword = WeaponFactory.getInstance().getWeapon("LongSword");
        longSword.setTwoHanded(true);
        longSword.setOwner(weaponOwner);

        DamageInstance longSwordDamageInstance = longSword.doDamage(RandomRoll.roll(20, 3));
        assertTrue(longSwordDamageInstance.getDamageValue() > 3);

        IDamageProvider twoHandedAxe = WeaponFactory.getInstance().getWeapon("TwoHandedAxe");
        twoHandedAxe.setOwner(weaponOwner);

        DamageInstance twoHandedAxeDamageInstance = twoHandedAxe.doDamage(RandomRoll.roll(20, 5));
        assertTrue(twoHandedAxeDamageInstance.getDamageValue() > 3);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testLongSwordShouldThrowsException() throws IOException {
        final IDamageProvider damageProvider = WeaponFactory.getInstance().getWeapon("LongSword");
        damageProvider.doDamage();
    }

    @Test
    public void serializationTest() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        PhysicalDamageProvider longSword = new PhysicalDamageProvider();

        longSword.setMinDamage(1);
        longSword.setMaxDamage(8);
        longSword.setCriticalChancePercent(5);
        longSword.setCriticalMultiplier(2);
        longSword.setTwoHanded(false);
        longSword.setTwoHandDamageBonus(1.5);
        longSword.setUseAgilityBonus(false);
        longSword.setUseStrengthBonus(true);
        longSword.setDamageType(DamageType.CUTTING);
        longSword.setName("Long Sword");
        longSword.setDescription("Common Long Sword");
        longSword.setBuyCost(5.0);
        longSword.setSellCost(0.6);


        String value = objectMapper.writeValueAsString(longSword);
        assertTrue(!value.isEmpty());
    }

    @Test
    public void deserializationTest() throws IOException {
        List<PhysicalDamageProvider> providers = new ObjectMapper()
                .readValue(getClass().getClassLoader().getResourceAsStream("weapons.json")
                        , new TypeReference<List<PhysicalDamageProvider>>(){});

        assertNotNull(providers);
    }
}
