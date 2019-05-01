package main;

public class UserInput {

    public Command getCommand(char keyChar) {
        switch(keyChar) {
            case ' ':
                return Command.JUMP;
            default:
                return Command.NOTHING;
        }
    }
}
