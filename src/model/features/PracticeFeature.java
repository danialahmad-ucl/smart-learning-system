package model.features;

public class PracticeFeature implements Feature {
    private boolean active;
    private final String name = "PracticeMode";

    public PracticeFeature(boolean active) {
        this.active = active;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void activate() {
        active = true;
    }

    @Override
    public void deactivate() {
        active = false;
    }

    @Override
    public String[] getLogEntries() {
        return new String[]{
                "buttonMedium practice mainWindow-Right",
                "window practiceMode",
                "list practiceExercises practiceMode-Center"
        };
    }

    @Override
    public boolean isActive() {
        return active;
    }
}