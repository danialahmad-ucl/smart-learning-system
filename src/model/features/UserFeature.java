package model.features;

public class UserFeature implements Feature {
    private boolean active;
    private final String name = "UserFeatures";

    public UserFeature(boolean active) {
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
                "buttonSmall profile mainWindow-TopRight",
                "window userProfile",
                "text progressTracker userProfile-Center",
                "list achievements userProfile-Right",
                "buttonSmall setReminders userProfile-Bottom"
        };
    }

    @Override
    public boolean isActive() {
        return active;
    }
}
