package duke.task;

/**
 * Represents a Task in the Task List.
 */
public class Task {
    protected String category;
    protected String description;
    protected boolean isDone;

    public Task(String category, String description) {
        this.category = category;
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns TICK_SYMBOL if task is completed and CROSS_SYMBOL if it is not complete.
     */
    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]");
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return this.description;
    }

    public void setAsDone() {
        this.isDone = true;
    }

    public boolean getDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
