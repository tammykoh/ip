package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Duke {

    private static final int MAX_TASK = 100;

    // List of all tasks
    private static Task[] Tasks = new Task[MAX_TASK];

    // Total number of tasks in the list
    private static int count = 0;

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        File file = new File("./data/tasks.txt");
        File folder = new File("./data");
        if (!folder.exists()) {
            folder.mkdir();
        }
        if (file.exists()) {
            readTextFile(file);
        } else {
            System.out.println("File does not exist. Creating file...");
            file.createNewFile();
        }
        displayWelcomeMessage();
        while (true) {
            String userCommand = getUserInput();
            executeUserCommand(userCommand);
        }
    }

    private static void readTextFile(File file) {
        try {
            Scanner sf = new Scanner(file);
            while (sf.hasNextLine()) {
                String[] taskLine = sf.nextLine().split(" \\| ");
                String command = taskLine[0];
                String done = taskLine[1];
                String item = taskLine[2];
                String datetime = null;
                switch(command) {
                case "T":
                    ToDo todo = new ToDo("t", item);
                    Tasks[count] = todo;
                    break;
                case "D":
                    datetime = taskLine[3];
                    Deadline deadline = new Deadline("d", item, datetime);
                    Tasks[count] = deadline;
                    break;
                case "E":
                    datetime = taskLine[3];
                    Event event = new Event("e", item, datetime);
                    Tasks[count] = event;
                    break;
                }
                count++;
                if (done == "1") {
                    Tasks[count-1].setAsDone();
                }
            }
            sf.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
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

    private static void executeUserCommand(String rawUserCommand) throws IOException {
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

    private static void addToList(String command, String details) throws IOException {
        if (command.equals("todo")) {
            ToDo todo = new ToDo("t", details);
            Tasks[count] = todo;
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
                    Tasks[count] = deadline;
                    break;
                case "event":
                    Event event = new Event("e", item, datetime);
                    Tasks[count] = event;
                    break;
                }
            } else {
                DukeException.printEmptyDetails(command);
                return;
            }
        }
        count++;
        System.out.println("Got it! I've added this task:");
        System.out.println("    " + Tasks[count-1]);
        System.out.println("Now you have " + count + " tasks in the list");
        addToFile();
    }

    public static void addToFile() throws IOException {
        String pathName = "./data/tasks.txt";
        FileWriter fw = new FileWriter(pathName, true);
        String taskToFile = taskToFileFormat(count);
        if (count == 1) {
            fw.write(taskToFile);
        } else {
            fw.write("\n" + taskToFile);
        }
        fw.close();
    }

    private static void displayList() {
        System.out.println("Here are the tasks in your list:");
        for (int i=0; i<count; i++) {
            int taskNumber = i + 1;
            Task task = Tasks[i];
            System.out.println("    " + taskNumber + ". " + task);
            taskNumber++;
        }
    }

    public static void setAsDone(int taskNumber) throws IOException {
        Task task = Tasks[taskNumber-1];
        task.setAsDone();
        System.out.println("Nice! I've marked this task as done:\n"
                + "    " + task);
        appendFile();
    }

    public static void appendFile() throws IOException {
        String tempPath = "./data/temp.txt";
        String pathName = "./data/tasks.txt";
        File tempFile = new File(tempPath);
        File file = new File(pathName);
        tempFile.createNewFile();
        FileWriter fw = new FileWriter(tempPath, true);
        for (int i=0; i<count; i++) {
            String taskLine = taskToFileFormat(i+1);
            if (i == 0) {
                fw.write(taskLine);
            } else {
                fw.write("\n" + taskLine);
            }
        }
        fw.close();
        Files.delete(Paths.get(pathName));
        tempFile.renameTo(file);
    }

    public static String taskToFileFormat(int taskNumber) {
        Task task = Tasks[taskNumber-1];
        String category = task.getCategory().toUpperCase();
        int taskDone = task.getDone() ? 1 : 0;
        String details = task.getDescription();
        String datetime = null;
        if (category.equals("T")) {
            return "T | " + taskDone + " | " + details;
        } else if (category.equals("D")) {
            datetime = ((Deadline) task).getBy();
        } else if (category.equals("E")) {
            datetime = ((Event) task).getAt();
        }
        return category + " | " + taskDone + " | " + details + "| " + datetime;
    }

    private static void exitProgram() {
        displayGoodbyeMessage();
        System.exit(0);
    }
}
