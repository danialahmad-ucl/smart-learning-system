package model.features;

public interface Feature {
    String getName();
    void activate();
    void deactivate();
    boolean isActive();
}