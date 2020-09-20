public class Event extends Task {
    protected String at;

    public Event(String category, String description, String at) {
        super(category, description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
