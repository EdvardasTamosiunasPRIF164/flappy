import main.Command;
import main.UserInput;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserInputTest {
    private UserInput userInput;

    @Before
    public void init(){
        userInput = new UserInput();
    }

    @Test
    public void whenSpaceIsClicked_ReturnCommandJump() {
        assertEquals(userInput.getCommand(' '), Command.JUMP);
    }

    @Test
    public void whenAnythingIsClicked_ReturnNothing() {
        assertEquals(userInput.getCommand('p'), Command.NOTHING);
    }

}
