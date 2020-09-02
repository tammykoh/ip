import java.util.Scanner;

public class Duke {

     // Maximum number of tasks that can be held
    private static final int MAX_TASK = 100;

    // List of all tasks
    //private static String[] allTasks;
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
        int dividerPosition = rawUserCommand.indexOf(" ");
        if (dividerPosition != -1) {
            userCommand = rawUserCommand.substring(0, dividerPosition);
        }
        switch(userCommand) {
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
            echoUserCommand(rawUserCommand);
            break;
        }
    }

    private static void echoUserCommand(String userCommand) {
        Task task = new Task(userCommand);
        Tasks[count] = task;
        System.out.println("added: " + task.description);
        count++;
    }

    private static void displayList() {
        for (int i=0; i<count; i++) {
            int taskNumber = i + 1;
            Task task = Tasks[i];
            System.out.println(taskNumber + ". " + task.getStatusIcon() + " " + task.description);
            taskNumber++;
        }
    }

    public static void setAsDone(int taskNumber){
        Task task = Tasks[taskNumber-1];
        task.setAsDone();
        System.out.println("Nice! I've marked this task as done:\n" + "  " + task.getStatusIcon() + " " + task.description);
    }

    private static void exitProgram() {
        displayGoodbyeMessage();
        System.exit(0);
    }
}
