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
        System.out.println(logo + "Hey there! I'm Duke.");
        printLine();
    }

    /** Displays the ready message once the file has been set-up. */
    public static void displayReadyMessage() {
        System.out.println("We're set and ready to go. What would you like me to do?");
    }

    /** Prints a dividing line. */
    public static void printLine() {
        System.out.println("_________________________________________________________________");
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
