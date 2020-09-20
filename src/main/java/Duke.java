import java.util.Scanner;

public class Duke {

    // Maximum number of tasks that can be held
    private static final int MAX_TASK = 100;

    // List of all tasks
    private static Task[] Tasks = new Task[MAX_TASK];

    // Total number of tasks in the list
    private static int count = 0;

    public static void main(String[] args) {
        displayWelcomeMessage();
        while (true) {
            String userCommand = getUserInput();
            executeUserCommand(userCommand);
        }
    }

    private static void displayWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "Hey there! I'm Duke.\n" + "How may I be of service?");
    }

    private static void displayGoodbyeMessage() {
        System.out.println("Goodbye. Hope to see you again soon! :)");
    }

    private static String getUserInput() {
        Scanner sc = new Scanner(System.in);
        String userCommand = sc.nextLine();
        return userCommand;
    }

    private static void executeUserCommand(String rawUserCommand) {
        String userCommand = rawUserCommand;
        String details = null;
        int dividerPosition = rawUserCommand.indexOf(" ");
        if (dividerPosition != -1) {
            userCommand = rawUserCommand.substring(0, dividerPosition);
            details = rawUserCommand.substring(dividerPosition + 1);
        }
        switch(userCommand) {
        case "todo":
            addToList("t", details);
            break;
        case "deadline":
            addToList("d", details);
            break;
        case "event":
            addToList("e", details);
            break;
        case "list":
            displayList();
            break;
        case "done":
            int taskNumber = Integer.parseInt(rawUserCommand.substring(dividerPosition+1));
            setAsDone(taskNumber);
            break;
        case "bye":
            exitProgram();
            break;
        default:
            System.out.println("You have entered an invalid command. Try again.");
            break;
        }
    }

    private static void addToList(String category, String details) {
        String item = null;
        String datetime = null;
        int dividerPosition2 = details.indexOf("/");
        if (dividerPosition2 != -1) {
            item = details.substring(0, dividerPosition2);
            datetime = details.substring(dividerPosition2 + 4);
        }
        switch(category) {
        case "t":
            ToDo todo = new ToDo("t", details);
            Tasks[count] = todo;
            break;
        case "d":
            Deadline deadline = new Deadline("d", item, datetime);
            Tasks[count] = deadline;
            break;
        case "e":
            Event event = new Event("e", item, datetime);
            Tasks[count] = event;
            break;
        default:
            break;
        }
        count++;
        System.out.println("Got it! I've added this task:");
        System.out.println("    " + Tasks[count-1]);
        System.out.println("Now you have " + count + " tasks in the list");
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

    public static void setAsDone(int taskNumber){
        Task task = Tasks[taskNumber-1];
        task.setAsDone();
        System.out.println("Nice! I've marked this task as done:\n"
                + "    " + task);
    }

    private static void exitProgram() {
        displayGoodbyeMessage();
        System.exit(0);
    }
}
