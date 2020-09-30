package duke.ui;

import java.util.Scanner;

public class UserInterface {

    private static final Scanner sc = new Scanner(System.in);

    public static void displayWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "Hey there! I'm Duke.\n" + "How may I be of service?");
    }

    public static void displayGoodbyeMessage() {
        System.out.println("Goodbye. Hope to see you again soon! :)");
    }

    public static String getUserInput() {
        String userCommand = sc.nextLine();
        return userCommand;
    }
}
