package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    // List of all tasks
    private static ArrayList<Task> tasks = new ArrayList<>();

    // Total number of tasks in the list
    private static int count = 0;

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws DukeException {
        displayWelcomeMessage();
        while (true) {
//            try {
                String userCommand = getUserInput();
                executeUserCommand(userCommand);
//            } catch (InvalidInput e) {
//                System.out.println(e.getMessage());
//            }
        }
    }

    private static void displayWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "Hey there! I'm duke.Duke.\n" + "How may I be of service?");
    }

    private static void displayGoodbyeMessage() {
        System.out.println("Goodbye. Hope to see you again soon! :)");
    }

    private static String getUserInput() {
        String userCommand = sc.nextLine();
        return userCommand;
    }

    private static void executeUserCommand(String rawUserCommand) throws DukeException {
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
                addToList(userCommand, details);
            } else {
                DukeException.printEmptyDescription(userCommand);
            }
            break;
        case "delete":
            deleteFromList(userCommand, details);
            break;
        case "list":
            displayList();
            break;
        case "done":
            int taskNumber = Integer.parseInt(details);
            setAsDone(taskNumber);
            break;
        case "bye":
            exitProgram();
            break;
        default:
            DukeException.printInvalidCommand();
            break;
        }
    }

    private static void addToList(String command, String details) {
        if (command.equals("todo")) {
            ToDo todo = new ToDo("t", details);
            tasks.add(todo);
        } else {
            String item = null;
            String datetime = null;
            int dividerPosition2 = details.indexOf("/");
            if (dividerPosition2 != -1) {
                item = details.substring(0, dividerPosition2);
                datetime = details.substring(dividerPosition2 + 4);
                switch(command) {
                case "deadline":
                    Deadline deadline = new Deadline("d", item, datetime);
                    tasks.add(deadline);
                    break;
                case "event":
                    Event event = new Event("e", item, datetime);
                    tasks.add(event);
                    break;
                }
            } else {
                DukeException.printEmptyDetails(command);
                return;
            }
        }
        count++;
        System.out.println("Got it! I've added this task:");
        System.out.println("    " + tasks.get(count-1));
        System.out.println("Now you have " + count + " tasks in the list");
    }

    private static void deleteFromList(String command, String details) {
        if (details == null) {
            DukeException.printEmptyDescription(command);
        } else {
            try {
                int taskNumber = Integer.parseInt(details);
                String task = tasks.get(taskNumber).toString();
                System.out.println("Got it! I've removed this task:");
                System.out.println("    " + task);
                tasks.remove(taskNumber);
                count--;
                System.out.println("Now you have " + count + " tasks in the list.");
            } catch (NumberFormatException e) {
                System.out.println("Please input a valid task number");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Please input a task number between 1 and " + count);
            }
        }
    }

    private static void displayList() {
        System.out.println("Here are the tasks in your list:");
        for (int i=0; i<count; i++) {
            int taskNumber = i + 1;
            Task task = tasks.get(i);
            System.out.println("    " + taskNumber + ". " + task);
            taskNumber++;
        }
    }

    public static void setAsDone(int taskNumber){
        Task task = tasks.get(taskNumber - 1);
        task.setAsDone();
        System.out.println("Nice! I've marked this task as done:\n"
                + "    " + task);
    }

    private static void exitProgram() {
        displayGoodbyeMessage();
        System.exit(0);
    }
}
