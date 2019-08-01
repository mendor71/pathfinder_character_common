import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mendor.CharacterAttributeDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CustomSerializerTest {
    @Test
    public void testSerializer() throws JsonProcessingException {
        ObjectNode root = JsonNodeFactory.instance.objectNode();

        ObjectNode properties = root.putObject("properties");
        properties.put("age", "28");
        properties.put("name", "mendor71");

        ArrayNode skills = root.putArray("skills");
        ObjectNode o1 = skills.addObject();
        o1.put("name", "fear");
        o1.put("value", "1");

        ObjectNode o2 = skills.addObject();
        o2.put("name", "sword");
        o2.put("value", "11");

        ObjectMapper mapper = new ObjectMapper();

        System.out.println( mapper.writeValueAsString(root) );
    }
}
