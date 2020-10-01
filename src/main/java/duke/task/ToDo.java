package duke.task;

/** Represents a ToDo Task in the TaskList */
public class ToDo extends Task {

    public ToDo(String category, String description) {
        super(category, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}