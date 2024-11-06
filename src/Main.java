//import controller.MainController;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        MainController controller = new MainController();
//        Scanner scanner = new Scanner(System.in);
//
//        // Show initial system state
//        System.out.println("Initial system state:");
//
//        System.out.println('\n');
//        printSystemState(controller);
//
//        boolean exit = false;
//        while (!exit) {
//
//            System.out.println('\n');
//            System.out.println("Activated Features: " + controller.getActivatedFeatures());
//            System.out.println("Deactivated Features: " + controller.getDeactivatedFeatures());
//
//            // Prompt user for feature activations and deactivations
//            System.out.println('\n');
//            System.out.println("Enter features to activate (comma-separated): ");
//            String[] activations = scanner.nextLine().split(",");
//
//            System.out.println('\n');
//            System.out.println("Enter features to deactivate (comma-separated): ");
//            String[] deactivations = scanner.nextLine().split(",");
//
//            // Process activations and deactivations
//            System.out.println('\n');
//            int result = controller.activate(deactivations, activations);
//            System.out.println("Activation result: " + result);
//
//            // Show current system state
//            System.out.println('\n');
//            System.out.println("Current system state:");
//            printSystemState(controller);
//        }
//
//        scanner.close();
//    }
//
//    private static void printSystemState(MainController controller) {
//        String[] logs = controller.getStateAsLog();
//        for (String log : logs) {
//            System.out.println(log);
//        }
//    }
//}

import java.awt.Color;
import controller.MainController;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create window with title "Smart Learning System" that stops the program when closed
        JFrame frame = new JFrame("Smart Learning System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating a panel that will hold the buttons
        JPanel panel = new JPanel();
        frame.add(panel);
        frame.setSize(400, 400);
        frame.setVisible(true); // show the window

        MainController controller = new MainController();
        Scanner scanner = new Scanner(System.in);

        // Maintain a map of buttons with their feature names for easy access and removal
        Map<String, JButton> featureButtons = new HashMap<>();

        // Show initial system state
        System.out.println("Initial system state:");
        printSystemState(controller);

        updateFeatureButtons(panel, featureButtons, controller.getActivatedFeatures(), controller.getDeactivatedFeatures());

        boolean exit = false;
        while (!exit) {
            // Refresh UI after changes
            frame.repaint();
            frame.revalidate();
            System.out.println("\nActivated Features: " + controller.getActivatedFeatures());
            System.out.println("Deactivated Features: " + controller.getDeactivatedFeatures());

            // Prompt user for feature activations and deactivations
            System.out.println("\nEnter features to activate (comma-separated): ");
            String[] activations = scanner.nextLine().split(",");

            System.out.println("Enter features to deactivate (comma-separated): ");
            String[] deactivations = scanner.nextLine().split(",");

            // Process activations and deactivations
            int result = controller.activate(deactivations, activations);
            System.out.println("Activation result: " + result);

            updateFeatureButtons(panel, featureButtons, controller.getActivatedFeatures(), controller.getDeactivatedFeatures());

            // Show current system state
            System.out.println("\nCurrent system state:");
            printSystemState(controller);
        }

        scanner.close();
    }

    private static void updateFeatureButtons(JPanel panel, Map<String, JButton> featureButtons, String activatedFeatures, String deactivatedFeatures) {
        // Parse activated features string and add buttons for new features
        for (String feature : parseFeatures(activatedFeatures)) {
            if (!featureButtons.containsKey(feature)) {
                JButton button = new JButton(feature);
                button.addActionListener(e -> System.out.println("Button for " + feature + " clicked"));
                featureButtons.put(feature, button);
                panel.add(button);
            }
        }

        // Parse deactivated features string and remove buttons for deactivated features
        for (String feature : parseFeatures(deactivatedFeatures)) {
            if (featureButtons.containsKey(feature)) {
                JButton button = featureButtons.get(feature);
                panel.remove(button);
                featureButtons.remove(feature);
            }
        }
    }

    // Helper method to parse features from a string like "[Course Social Practice Premium User]"
    private static String[] parseFeatures(String features) {
        //split by whitespace
        return features.trim().split("\\s+");
    }

    private static void printSystemState(MainController controller) {
        String[] logs = controller.getStateAsLog();
        for (String log : logs) {
            System.out.println(log);
        }
    }
}