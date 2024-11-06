package model.features;

public class PremiumFeature implements Feature {
    private boolean active;
    private final String name = "Premium";

    public PremiumFeature(boolean active) {
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
                "buttonMedium unlockPremium mainWindow-Top",
                "window premiumFeatures",
                "list premiumBenefits premiumFeatures-Center",
                "text unlimitedLives mainWindow-TopRight",
                "text noLessonLimit mainWindow-TopLeft"
        };
    }

    @Override
    public boolean isActive() {
        return active;
    }
}