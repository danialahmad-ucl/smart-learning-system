package controller;

import model.SystemModel;

public class MainController implements ControllerInterface {
    private final SystemModel systemModel;
    private boolean isUIEnabled;

    public MainController() {
        this.systemModel = new SystemModel();
        this.isUIEnabled = false;
    }

    @Override
    public int activate(String[] deactivations, String[] activations) {
        try {
            // Process deactivations
            for (String feature : deactivations) {
                systemModel.deactivateFeature(feature);
            }

            // Process activations
            for (String feature : activations) {
                systemModel.activateFeature(feature);
            }

            return 0;
        } catch (Exception e) {
            return 3;
        }
    }

    @Override
    public boolean enableUIView() {
        if (isUIEnabled) return true;
        isUIEnabled = true;
        return true;
    }

    @Override
    public boolean disableUIView() {
        if (!isUIEnabled) return true;
        isUIEnabled = false;
        return true;
    }

    @Override
    public String[] getStateAsLog() {
        return systemModel.getCurrentLogs();
    }

    public String getActivatedFeatures() {
        return systemModel.getActivatedFeatures();
    }

    public String getDeactivatedFeatures() {
        return systemModel.getDeactivatedFeatures();
    }
}
