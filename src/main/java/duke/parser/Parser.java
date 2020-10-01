package duke.parser;

import duke.DukeException;
import duke.task.TaskList;
import duke.ui.UserInterface;

import java.io.IOException;

public class Parser {

    private static UserInterface ui = new UserInterface();
    private static TaskList taskList = new TaskList();

    public static void executeUserCommand(String rawUserCommand) throws IOException {
        String userCommand = rawUserCommand.trim();
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
                if (dividerPosition != -1) {
                    taskList.addToList(userCommand, details);
                } else {
                    DukeException.printEmptyDescription(userCommand);
                }
                break;
            case "list":
                taskList.displayList();
                break;
            case "done":
                int taskNumber = Integer.parseInt(details);
                taskList.setAsDone(taskNumber);
                break;
            case "delete":
                taskList.deleteFromList(userCommand, details);
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

    private static void exitProgram() {
        ui.displayGoodbyeMessage();
        System.exit(0);
    }
}
