package duke.task;

/** Represents a Deadline Task in the TaskList */
public class Deadline extends Task {
    protected String by;

    public Deadline(String category, String description, String by) {
        super(category, description);
        this.by = by;
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}