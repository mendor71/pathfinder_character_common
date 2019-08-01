import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.mendor.CustomCharacterJSONDeserializer;
import com.mendor.PathfinderCharacter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;

@RunWith(JUnit4.class)
public class CustomDeserializerTest {

    private static String testJson = "{\"characterProperties\":{\"uuid\":\"9310583a-a3cd-4b37-b121-edcbe7a9dc23\",\"name\":\"Vadgast\",\"level\":7,\"age\":34,\"armorClass\":0,\"height\":\"182\",\"weight\":\"80\",\"eyeColor\":{\"R\":128,\"G\":128,\"B\":128},\"hairColor\":{\"R\":0,\"G\":0,\"B\":0}},\"skills\":[{\"type\":\"Stealth\",\"value\":3,\"modifier\":0,\"bonus\":0},{\"type\":\"Swim\",\"value\":3,\"modifier\":0,\"bonus\":0},{\"type\":\"Climb\",\"value\":3,\"modifier\":0,\"bonus\":0},{\"type\":\"Survival\",\"value\":3,\"modifier\":0,\"bonus\":0},{\"type\":\"Ride\",\"value\":3,\"modifier\":0,\"bonus\":0},{\"type\":\"KnowledgeNature\",\"value\":3,\"modifier\":0,\"bonus\":0},{\"type\":\"KnowledgeEngineering\",\"value\":3,\"modifier\":0,\"bonus\":0},{\"type\":\"Intimidate\",\"value\":3,\"modifier\":0,\"bonus\":0},{\"type\":\"Profession\",\"value\":3,\"modifier\":0,\"bonus\":0},{\"type\":\"Spellcraft\",\"value\":3,\"modifier\":0,\"bonus\":0},{\"type\":\"KnowledgeNobility\",\"value\":3,\"modifier\":0,\"bonus\":0},{\"type\":\"Craft\",\"value\":3,\"modifier\":0,\"bonus\":0},{\"type\":\"Perception\",\"value\":3,\"modifier\":0,\"bonus\":0},{\"type\":\"KnowledgeDungeoneering\",\"value\":3,\"modifier\":0,\"bonus\":0},{\"type\":\"HandleAnimal\",\"value\":3,\"modifier\":0,\"bonus\":0},{\"type\":\"Heal\",\"value\":3,\"modifier\":0,\"bonus\":0}]}";

    @Test
    public void testCustomDeserializer() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(PathfinderCharacter.class, new CustomCharacterJSONDeserializer(PathfinderCharacter.class));
        objectMapper.registerModule(module);


        PathfinderCharacter character = objectMapper.readValue(testJson, PathfinderCharacter.class);

        System.out.println(character);
    }
}
