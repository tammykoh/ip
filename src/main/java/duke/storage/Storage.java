package duke.storage;

import duke.task.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the file used to store address book data.
 */
public class Storage {

    private static TaskList taskList = new TaskList();

    /**
     * Loads a text file upon starting the program
     * Creates a text file if the text file does not exist
     */
    public static void loadTextFile() throws IOException {
        File file = new File("./data/tasks.txt");
        File folder = new File("./data");
        if (!folder.exists()) {
            folder.mkdir();
        }
        if (file.exists()) {
            System.out.println("Loading existing file...");
            readTextFile(file);
        } else {
            System.out.println("Seems like you're new here. No worries! Your file is being created...");
            file.createNewFile();
        }
    }

    /**
     * Reads the text file line by line and adds each task to TaskList
     */
    private static void readTextFile(File file) {
        try {
            Scanner sf = new Scanner(file);
            while (sf.hasNextLine()) {
                taskList.addTextToList(sf.nextLine());
            }
            sf.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /** Saves the new task in TaskList to the storage file. */
    public static void addToFile() throws IOException {
        int count = TaskList.getCount();
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

    /** Appends the file when a task in TaskList has been done or deleted. */
    public static void appendFile() throws IOException {
        int count = TaskList.getCount();
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

    /**
     * Converts a task from the TaskList into a certain format to store in a file
     *
     * @param taskNumber task that needs to be converted into storing format
     * @return the data in string format
     */
    public static String taskToFileFormat(int taskNumber) {
        ArrayList<Task> tasks = taskList.getArrayList();
        String category = tasks.get(taskNumber-1).getCategory().toUpperCase();
        int taskDone = tasks.get(taskNumber-1).getDone() ? 1 : 0;
        String details = tasks.get(taskNumber-1).getDescription();
        String datetime = null;
        if (category.equals("T")) {
            return "T | " + taskDone + " | " + details;
        } else if (category.equals("D")) {
            datetime = ((Deadline) tasks.get(taskNumber-1)).getBy();
        } else if (category.equals("E")) {
            datetime = ((Event) tasks.get(taskNumber-1)).getAt();
        }
        return category + " | " + taskDone + " | " + details + " | " + datetime;
    }
}
