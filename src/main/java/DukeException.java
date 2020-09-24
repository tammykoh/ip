public class DukeException extends Exception{

    public static void printInvalidCommand() {
        System.out.println("Oh no! I do not know what that command means \u2639");
    }

    public static void printEmptyDescription(String command) {
        System.out.println("Oops! You can't leave the description for " + command + " empty \u2639");
    }

    public static void printEmptyDetails(String command) {
        System.out.println("Oops! You can't leave the date/time for " + command + " empty \u2639");
    }
}

