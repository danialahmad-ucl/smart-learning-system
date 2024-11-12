package controller;

import model.Model;
import view.View;

public class Controller implements ControllerInterface {
    private Model systemModel;
    private View systemView;

    public Controller() {
        this.systemModel = new Model();
        this.systemView = new View(this, systemModel);
    }

    @Override
    public int activate(String[] deactivations, String[] activations) {
        int index = 0;
        try {
            // Process deactivations
            for (String feature : deactivations) {
                systemModel.deactivateFeature(feature);
                systemModel.getCurrentLogsList().add("[Controller] Deactivated feature: " + feature);

                index--;
            }

            // Process activations
            for (String feature : activations) {
                systemModel.activateFeature(feature);
                systemModel.getCurrentLogsList().add("[Controller] Activated feature: " + feature);
                index++;
            }

            return index;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public boolean enableUIView() {
        systemModel.getCurrentLogsList().add("[Controller] Enabled UI view");
        return this.systemView.showView();
    }

    @Override
    public boolean disableUIView() {
        systemModel.getCurrentLogsList().add("[Controller] Disabled UI view");
        return this.systemView.hideView();
    }

    @Override
    public String[] getStateAsLog() {

        //Update this as well
        return systemModel.getCurrentLogs();
    }

    public String getActivatedFeatures() {
        return systemModel.getActivatedFeatures();
    }

    public String getDeactivatedFeatures() {
        return systemModel.getDeactivatedFeatures();
    }
}
