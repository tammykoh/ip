package duke;

import duke.ui.UserInterface;
import duke.parser.Parser;
import duke.storage.Storage;

import java.io.IOException;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {

    private static UserInterface ui = new UserInterface();
    private static Storage storage = new Storage();
    private static Parser parser = new Parser();

    /**
     * Prints the welcome message, and loads up the data from the storage file
     * Reads and executes user command until termination
     */
    public static void main(String[] args) throws IOException {
        ui.displayWelcomeMessage();
        storage.loadTextFile();
        ui.displayReadyMessage();
        while (true) {
            ui.printLine();
            String userCommand = ui.getUserInput();
            parser.executeUserCommand(userCommand);
        }
    }
}
