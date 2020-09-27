package duke.task;

public class Task {
    protected String category;
    protected String description;
    protected boolean isDone;

    public Task(String category, String description) {
        this.category = category;
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        //return tick or X symbols
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
