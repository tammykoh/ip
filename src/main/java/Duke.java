import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        displayWelcomeMessage();
        while (true) {
            String userCommand = getUserInput();
            echoUserCommand(userCommand);
        }
    }

    private static void displayWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "Hey there! I'm Duke\n" + "How can I be of service?");
    }

    private static void displayGoodbyeMessage() {
        System.out.println("Goodbye. Hope to see you again soon! :)");
    }

    private static String getUserInput() {
        Scanner sc = new Scanner(System.in);
        String userCommand = sc.nextLine();
        if (userCommand.equals("bye")) {
            displayGoodbyeMessage();
            System.exit(0);
        }

        return userCommand;
    }

    private static void echoUserCommand(String userCommand) {
        System.out.println(userCommand);
    }
}
