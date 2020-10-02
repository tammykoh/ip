package duke.parser;

import duke.DukeException;
import duke.task.TaskList;
import duke.ui.UserInterface;

import java.io.IOException;

/**
 * Parses user input.
 */
public class Parser {

    private static final UserInterface ui = new UserInterface();
    private static final TaskList taskList = new TaskList();

    /**
     * Parses user input into command for execution.
     *
     * @param rawUserCommand full user input string
     */
    public static void executeUserCommand(String rawUserCommand) throws IOException {
        String userCommand = rawUserCommand;
        String details = null;
        int dividerPosition = rawUserCommand.indexOf(" ");
        if (dividerPosition != -1) {
            userCommand = rawUserCommand.substring(0, dividerPosition);
            details = rawUserCommand.substring(dividerPosition + 1);
        }
        switch(userCommand) {
        case "todo":
            //Fallthrough
        case "deadline":
            //Fallthrough
        case "event":
            if (details != null) {
                taskList.addToList(userCommand, details);
            } else {
                DukeException.printEmptyDescription(userCommand);
            }
            break;
        case "list":
            taskList.displayList();
            break;
        case "done":
            taskList.setAsDone(details);
            break;
        case "delete":
            taskList.deleteFromList(details);
            break;
        case "find":
            if (dividerPosition != -1) {
                taskList.searchList(details);
            } else {
                DukeException.printEmptyDescription(userCommand);
            }
            break;
        case "bye":
            exitProgram();
            break;
        default:
            DukeException.printInvalidCommand();
            break;
        }
    }

    /** Displays goodbye message and terminates the program */
    private static void exitProgram() {
        ui.displayGoodbyeMessage();
        System.exit(0);
    }
}
