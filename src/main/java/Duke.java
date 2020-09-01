import java.util.Scanner;

public class Duke {

     // Maximum number of tasks that can be held
    private static final int MAX_TASK = 100;

    // List of all tasks
    private static String[] allTasks;

    // Total number of tasks in the list
    private static int count;

    public static void main(String[] args) {
        initTaskList();
        displayWelcomeMessage();
        while (true) {
            String userCommand = getUserInput();
            executeUserCommand(userCommand);
        }
    }

    private static void initTaskList() {
        allTasks = new String[MAX_TASK];
        count = 0;
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

    private static void executeUserCommand(String userCommand) {
        switch(userCommand) {
        case "list":
            displayList();
            break;
        case "bye":
            exitProgram();
            break;
        default:
            echoUserCommand(userCommand);
            break;
        }
    }

    private static void echoUserCommand(String userCommand) {
        allTasks[count] = userCommand;
        System.out.println("added: " + userCommand);
        count++;
    }

    private static void displayList() {
        int taskNum = 1;
        String[] taskList = allTasks;
        for (int i=0; i<count; i++) {
            System.out.println(taskNum + ". " + taskList[i]);
            taskNum++;
        }
    }

    private static void exitProgram() {
        displayGoodbyeMessage();
        System.exit(0);
    }
}
