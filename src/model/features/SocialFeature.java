package model.features;

public class SocialFeature implements Feature {
    private boolean active;
    private final String name = "SocialFeatures";

    public SocialFeature(boolean active) {
        this.active = active;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isActive() {
        return active;
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
                "buttonMedium socialHub mainWindow-Left",
                "window socialFeatures",
                "list leaderboard socialFeatures-Right",
                "list forums socialFeatures-Left",
                "buttonSmall groupLearning socialFeatures-Bottom"
        };
    }
}