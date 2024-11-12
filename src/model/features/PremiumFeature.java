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
    public boolean isActive() {
        return active;
    }
}