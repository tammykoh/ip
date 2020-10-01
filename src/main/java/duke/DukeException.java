package duke;

/**
 * Represents the file used to store address book data.
 */
public class DukeException extends Exception{

    /** Signals an error caused by an invalid command. */
    public static void printInvalidCommand() {
        System.out.println("Oh no! I do not know what that command means \u2639");
    }

    /** Signals an error caused by an empty description field. */
    public static void printEmptyDescription(String command) {
        System.out.println("Oops! You can't leave the description for " + command + " empty \u2639");
    }

    /** Signals an error caused by the lack of /at or /by for the event and deadline respectively. */
    public static void printEmptyDetails(String command) {
        System.out.println("Oops! You can't leave the date/time for " + command + " empty \u2639");
    }
}

