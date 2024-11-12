package model;

import model.features.*;
import java.util.*;

public class Model {
    private List<String> currentLogs;
    private List<Feature> featureList;

    public CourseFeature courseFeature = null;

    public PracticeFeature practiceFeature = null;
    public UserFeature userFeature = null;
    public SocialFeature socialFeature = null;
    public PremiumFeature premiumFeature = null;


    public Model() {
        this.currentLogs = new ArrayList<>();
        this.featureList = new ArrayList<>();

        initializeInitialFeatures();
    }

    private void initializeInitialFeatures() {
        currentLogs.add("[Model] -> Initialization.");

        this.courseFeature = new CourseFeature(true);
        featureList.add(courseFeature );

        currentLogs.add("[Model] Initialization -> Set implemented feature: "+ courseFeature.getName() +" to true.");

        this.practiceFeature = new PracticeFeature(true);
        featureList.add(practiceFeature);
        currentLogs.add("[Model] Initialization -> Set implemented feature: "+ practiceFeature .getName() +" to true.");

        this.userFeature = new UserFeature(true);
        featureList.add(userFeature);
        currentLogs.add("[Model] Initialization -> Set implemented feature: "+ userFeature.getName() +" to true.");

        this.socialFeature = new SocialFeature(false);
        featureList.add(socialFeature);
        currentLogs.add("[Model] Initialization -> Set unimplemented feature: "+ socialFeature.getName() +" to false.");

        this.premiumFeature = new PremiumFeature(false);
        featureList.add(premiumFeature);
        currentLogs.add("[Model] Initialization -> Set unimplemented feature: "+ premiumFeature.getName() +" to false.");
    }

    public void activateFeature(String featureName) {
        featureList.stream()
                .filter(feature -> feature.getName().equals(featureName))
                .findFirst()
                .ifPresent(feature -> {
                    feature.activate();
                    currentLogs.add("[Model] -> Activated feature: "+ featureName +".");
                });
    }

    public void deactivateFeature(String featureName) {
        featureList.stream()
                .filter(feature -> feature.getName().equals(featureName))
                .findFirst()
                .ifPresent(feature -> {
                    feature.deactivate();
                    currentLogs.add("[Model] -> Deactivated feature: "+ featureName +".");
                });
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

    public List<Feature> getFeatureList() {
        return featureList;
    }

    public List<String> getCurrentLogsList() {
        return currentLogs;
    }
}