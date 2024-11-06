package model;

import model.features.*;

import java.util.*;

public class SystemModel {
    private final List<String> currentLogs;
    private final List<Feature> featureList;

    public SystemModel() {
        this.currentLogs = new ArrayList<>();
        this.featureList = new ArrayList<>();

        initializeBasicState();
        initializeInitialFeatures();
    }

    private void initializeBasicState() {
        currentLogs.add("window mainWindow");
        currentLogs.add("text welcome mainWindow-Center");
    }

    private void initializeInitialFeatures() {
        CourseFeature Course = new CourseFeature(true);
        featureList.add(Course);
        currentLogs.addAll(Arrays.asList(Course.getLogEntries()));

        SocialFeature Social = new SocialFeature(true);
        featureList.add(Social);
        currentLogs.addAll(Arrays.asList(Social.getLogEntries()));

        PracticeFeature Practice = new PracticeFeature(true);
        featureList.add(Practice);
        currentLogs.addAll(Arrays.asList(Practice.getLogEntries()));

        PremiumFeature Premium = new PremiumFeature(true);
        featureList.add(Premium);
        currentLogs.addAll(Arrays.asList(Premium.getLogEntries()));

        UserFeature User = new UserFeature(true);
        featureList.add(User);
        currentLogs.addAll(Arrays.asList(User.getLogEntries()));
    }

    public void activateFeature(String featureName) {
        featureList.stream()
                .filter(feature -> feature.getName().equals(featureName))
                .findFirst()
                .ifPresent(feature -> {
                    feature.activate();
                    updateLogs(featureName);
                });
    }

    public void deactivateFeature(String featureName) {
        featureList.stream()
                .filter(feature -> feature.getName().equals(featureName))
                .findFirst()
                .ifPresent(feature -> {
                    feature.deactivate();
                    updateLogs(featureName);
                });
    }

    private void updateLogs(String featureName) {
        currentLogs.clear();
        initializeBasicState();
        featureList.stream()
                .filter(Feature::isActive)
                .map(Feature::getLogEntries)
                .forEach(logs -> Collections.addAll(currentLogs, logs));
    }

    public String[] getCurrentLogs() {
        return currentLogs.toArray(new String[0]);
    }

    public String getActivatedFeatures() {
        StringBuilder activatedFeatures = new StringBuilder();
        featureList.stream()
                .filter(Feature::isActive)
                .map(Feature::getName)
                .forEach(feature -> activatedFeatures.append(feature).append(" "));
        return activatedFeatures.toString().trim();
    }

    public String getDeactivatedFeatures() {
        StringBuilder deactivatedFeatures = new StringBuilder();
        featureList.stream()
                .filter(feature -> !feature.isActive())
                .map(Feature::getName)
                .forEach(feature -> deactivatedFeatures.append(feature).append(" "));
        return deactivatedFeatures.toString().trim();
    }
}