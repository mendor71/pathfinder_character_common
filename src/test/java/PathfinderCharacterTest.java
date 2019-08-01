import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.mendor.*;
import com.mendor.exceptions.NotEnoughSkillPointsException;
import com.mendor.pclasses.CharacterClass;
import com.mendor.races.HumanRace;
import com.mendor.types.AttributeType;
import com.mendor.types.ClassType;
import com.mendor.types.SkillType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.awt.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class PathfinderCharacterTest {
    private PathfinderCharacter testCharacter;
    private ICharacterClassManager characterClassManager ;
    private ICharacterAttributeManager characterAttributeManager;
    private ICharacterSkillManager characterSkillManager;
    private Set<CharacterClassDetails> classDetails;
    private Set<CharacterAttributeDetails> attributeDetails;

    private CharacterFactory characterFactory;

    @Before
    public void prepareUtils() {
        characterClassManager = new SimpleCharacterClassManager();
        characterAttributeManager = new SimpleCharacterAttributeManager();
        characterSkillManager = new SimpleCharacterSkillManager();

        classDetails = new HashSet<>();
        attributeDetails = new HashSet<>();

        classDetails.add(new CharacterClassDetails(CharacterClass.getInstance(ClassType.FIGHTER), 4));
        classDetails.add(new CharacterClassDetails(CharacterClass.getInstance(ClassType.RANGER), 3));

        attributeDetails.add(new CharacterAttributeDetails(AttributeType.ENDURANCE, 14));
        attributeDetails.add(new CharacterAttributeDetails(AttributeType.STRENGTH, 16));
        attributeDetails.add(new CharacterAttributeDetails(AttributeType.AGILITY, 12));
        attributeDetails.add(new CharacterAttributeDetails(AttributeType.WISDOM, 10));
        attributeDetails.add(new CharacterAttributeDetails(AttributeType.INTELLIGENCE, 10));
        attributeDetails.add(new CharacterAttributeDetails(AttributeType.CHARISMA, 12));

        characterFactory = new CharacterFactory(characterClassManager, characterAttributeManager, characterSkillManager);

        testCharacter = characterFactory.newCharacter(new HumanRace(), classDetails, attributeDetails);

        testCharacter.setName("Vadgast");
        testCharacter.setAge(34);
        testCharacter.setHeight(182);
        testCharacter.setWeight(80);
        testCharacter.setEyeColor(Color.GRAY);
        testCharacter.setHairColor(Color.BLACK);
    }

    @Test
    public void characterClassManagerTest() {
        assertEquals(characterClassManager.getCharacterSummaryLevel(testCharacter), testCharacter.getLevel().longValue());
        assertTrue(characterClassManager.containsCharacterClass(testCharacter, CharacterClass.getInstance(ClassType.FIGHTER)));
        assertTrue(characterClassManager.containsCharacterClass(testCharacter, CharacterClass.getInstance(ClassType.RANGER)));
    }

    @Test
    public void characterSkillManagerTest() throws NotEnoughSkillPointsException {
        assertTrue(testCharacter.getSkillManager().containsCharacterSkill(testCharacter, SkillType.Survival));

        assertEquals(testCharacter.getFreeSkillPoints(), 26);
        assertEquals(testCharacter.getUsedSkillPoints(), 0);
        testCharacter.increaseSkillPoints(SkillType.Survival, 5);
        assertEquals(testCharacter.getSkillPoints(SkillType.Survival).longValue(), 8);

        assertEquals(testCharacter.getFreeSkillPoints(), 21);
        assertEquals(testCharacter.getUsedSkillPoints(), 5);

        testCharacter.increaseSkillPoints(SkillType.DisableDevice, 4);
        assertEquals(testCharacter.getSkillPoints(SkillType.DisableDevice).longValue(), 4);

        assertEquals(testCharacter.getFreeSkillPoints(), 17);
        assertEquals(testCharacter.getUsedSkillPoints(), 9);

        testCharacter.increaseSkillPoints(SkillType.DisableDevice, 17);
        assertEquals(testCharacter.getFreeSkillPoints(), 0);
        assertEquals(testCharacter.getUsedSkillPoints(), 26);
        assertEquals(testCharacter.getSkillPoints(SkillType.DisableDevice).longValue(), 21);
    }

    @Test(expected = NotEnoughSkillPointsException.class)
    public void characterSkillManagerUserPointsMoreThanFreeException() throws NotEnoughSkillPointsException {
        testCharacter.increaseSkillPoints(SkillType.Survival, 90);
    }

    @Test
    public void testSerializer() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        SimpleModule module = new SimpleModule("CustomCharacterSerializer", new Version(1, 0, 0, null, null, null));
        module.addSerializer(PathfinderCharacter.class, new PathFinderCharacterJSONSerializer());

        objectMapper.registerModule(module);

        String val = objectMapper.writeValueAsString(testCharacter);

        System.out.println(val);
    }

    @Test
    public void testDecoratorBasedSerializerAndDeserializer() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        IJSONSerializer sBase = new CustomCharacterJSONSerializer();
        IJSONSerializer sWithClasses = new CustomCharacterJSONSerializerWithClasses(sBase);
        IJSONSerializer sWithAttributes = new CustomCharacterJSONSerializerWithAttributes(sWithClasses);
        IJSONSerializer sWithSkills = new CustomCharacterJSONSerializerWithSkills(sWithAttributes);

        sWithSkills.preSerialize(testCharacter);

        String serializedData = mapper.writeValueAsString(sWithSkills.getResult());

        CustomCharacterJSONDeserializer dBase = new CustomCharacterJSONDeserializer(PathfinderCharacter.class);
        CustomCharacterJSONDeserializer dWithAttributes = new CustomCharacterJSONDeserializerWihAttributes(dBase);

        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(PathfinderCharacter.class, dWithAttributes);
        objectMapper.registerModule(module);

        PathfinderCharacter character = objectMapper.readValue(serializedData, PathfinderCharacter.class);

        System.out.println(character);
    }
}
