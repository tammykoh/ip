package duke.ui;

import java.util.Scanner;

/**
 * Text UI of the application.
 */
public class UserInterface {

    private static final Scanner sc = new Scanner(System.in);

    /** Displays the welcome message upon the start of the application. */
    public static void displayWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "Hey there! I'm Duke.\n" + "How may I be of service?");
    }

    /** Displays the goodbye message upon the termination of the application. */
    public static void displayGoodbyeMessage() {
        System.out.println("Goodbye. Hope to see you again soon! :)");
    }

    /**
     * Reads the text entered by the user.
     *
     * @return userCommand (full line) entered by the user
     */
    public static String getUserInput() {
        String userCommand = sc.nextLine();
        return userCommand;
    }
}
