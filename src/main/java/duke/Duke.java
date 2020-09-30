package duke;

import duke.ui.UserInterface;
import duke.parser.Parser;
import duke.storage.Storage;

import java.io.IOException;

public class Duke {

    private static UserInterface ui = new UserInterface();
    private static Storage storage = new Storage();
    private static Parser parser = new Parser();

    public static void main(String[] args) throws IOException {
        ui.displayWelcomeMessage();
        storage.loadTextFile();
        while (true) {
            String userCommand = ui.getUserInput();
            parser.executeUserCommand(userCommand);
        }
    }
}
