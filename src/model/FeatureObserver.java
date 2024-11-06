package model;

public interface FeatureObserver {
    void onFeatureChange(String feature, boolean activated);
}
