import main.Bird;
import org.junit.Before;
import org.junit.Test;
import util.GameUtil;

import java.awt.*;

import static org.junit.Assert.assertTrue;

public class GameUtilTest {

    GameUtil gameUtil;

    @Before
    public void init(){
        gameUtil = GameUtil.getInstance();

    }

    @Test
    public void whenBirdIntersectsColumn_ReturnTrue() {
        Bird bird = new Bird();
        assertTrue(gameUtil.intersects(bird, new Rectangle(bird.x + 10, bird.y + 10)));
    }


}
