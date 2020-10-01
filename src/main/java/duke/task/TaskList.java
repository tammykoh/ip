package duke.task;

import duke.DukeException;
import duke.storage.Storage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents and contains the data of the entire Task List.
 */
public class TaskList {

    public static ArrayList<Task> tasks = new ArrayList<>();

    /** Total number of tasks in the list */
    private static int count = 0;

    private static Storage storage = new Storage();

    /**
     * Adds a task to the Task List.
     */
    public static void addToList(String command, String details) throws IOException {
        if (command.equals("todo")) {
            ToDo todo = new ToDo("t", details);
            tasks.add(todo);
        } else {
            String item = null;
            String datetime = null;
            int dividerPosition = details.indexOf("/");
            if (dividerPosition != -1) {
                item = details.substring(0, dividerPosition);
                datetime = details.substring(dividerPosition + 4);
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
        storage.addToFile();
    }

    /**
     * Adds existing task from file to the Task List upon starting the application.
     */
    public static void addTextToList(String fileLine) {
        String[] taskLine = fileLine.split(" \\| ");
        String command = taskLine[0];
        String done = taskLine[1];
        String item = taskLine[2];
        String datetime = null;
        switch(command) {
        case "T":
            ToDo todo = new ToDo("t", item);
            tasks.add(todo);
            break;
        case "D":
            datetime = taskLine[3];
            Deadline deadline = new Deadline("d", item, datetime);
            tasks.add(deadline);
            break;
        case "E":
            datetime = taskLine[3];
            Event event = new Event("e", item, datetime);
            tasks.add(event);
            break;
        }
        count++;
        if (done.equals("1")) {
            tasks.get(count-1).setAsDone();
        }
    }

    /**
     * Deletes a task to the Task List.
     */
    public static void deleteFromList(String command, String details) {
        if (details == null) {
            DukeException.printEmptyDescription(command);
            return;
        }
        try {
            int taskNumber = Integer.parseInt(details);
            String task = tasks.get(taskNumber - 1).toString();
            System.out.println("Got it! I've removed this task:");
            System.out.println("    " + task);
            tasks.remove(taskNumber - 1);
            count--;
            if (count == 0) {
                System.out.println("You have no more task in the list.");
            } else if (count == 1) {
                System.out.println("You have 1 task in the list.");
            } else {
                System.out.println("You have " + count + " tasks in the list.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please input a valid task number");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please input a task number between 1 and " + count);
        }
    }

    /**
     * Sets a task as done in the Task List.
     */
    public static void setAsDone(int taskNumber) throws IOException {
        Task task = tasks.get(taskNumber - 1);
        task.setAsDone();
        System.out.println("Nice! I've marked this task as done:\n"
                + "    " + task);
        storage.appendFile();
    }

    /**
     * Displays the entire Task List.
     */
    public static void displayList() {
        if (count == 0) {
            System.out.println("You have no task in your list.");
        } else if (count == 1) {
            System.out.println("Here is the task in your list:");
        } else {
            System.out.println("Here are the tasks in your list:");
        }
        for (int i=0; i<count; i++) {
            int taskNumber = i + 1;
            Task task = tasks.get(i);
            System.out.println("    " + taskNumber + ". " + task);
            taskNumber++;
        }
    }

    public static void searchList(String toFind){
        int numOfMatches = 0;
        System.out.println("Here are the matching task(s) in your list:");
        for (int i=0; i<count; i++) {
            String description = tasks.get(i).getDescription();
            boolean check = description.contains(toFind);
            if (check) {
                numOfMatches++;
                System.out.println("    " + numOfMatches + ". " + tasks.get(i));
            }
        }
        if (numOfMatches == 0) {
            System.out.println("    --- no matches found ---    ");
        }
    }

    public static int getCount() {
        return count;
    }

    public static ArrayList<Task> getArrayList() {
        return tasks;
    };
}
