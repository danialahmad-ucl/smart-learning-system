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
}