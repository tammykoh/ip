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

public class Storage {

    private static TaskList taskList = new TaskList();

    public static void loadTextFile() throws IOException {
        File file = new File("./data/tasks.txt");
        File folder = new File("./data");
        if (!folder.exists()) {
            folder.mkdir();
        }
        if (file.exists()) {
            readTextFile(file);
        } else {
            System.out.println("File does not exist. Creating file...");
            file.createNewFile();
        }
    }

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
        return category + " | " + taskDone + " | " + details + "| " + datetime;
    }
}
