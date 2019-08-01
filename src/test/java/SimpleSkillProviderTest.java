import com.mendor.pathfinder.skills.CharacterSkill;
import com.mendor.pathfinder.types.AttributeType;
import com.mendor.pathfinder.types.SkillType;
import com.mendor.pathfinder.skills.SimpleSkillProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class SimpleSkillProviderTest {


    @Test
    public void testSkillFactoryReturnCorrectSkillInstance() {
        SimpleSkillProvider simpleSkillProvider = SimpleSkillProvider.getInstance();

        CharacterSkill skill = simpleSkillProvider.getSkillByType(SkillType.Survival);

        assertTrue(skill.useUntrained());
        assertEquals(skill.getAttributeType(), AttributeType.WISDOM);
    }
}
