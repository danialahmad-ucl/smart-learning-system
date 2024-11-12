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

import controller.Controller;
import model.features.*;
import view.View;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Create array list of dummy strings

        ArrayList<String> activatedFeatures = new ArrayList<String>()
        {
            {
                add("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                add("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
                add("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
                add("ccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc");
                add("ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
                add("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
                add("fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
                add("ggggggggggggggggggggggggggggggggg");
                add("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
                add("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
            }
        };


        Course course1 = new Course("CS 555", Difficulty.EASY, true, activatedFeatures);
        Course course2 = new Course("CS 666", Difficulty.EASY, true, activatedFeatures);
        Course course3 = new Course("CS 777", Difficulty.EASY, true, activatedFeatures);
        Course course4 = new Course("CS 888", Difficulty.EASY, true, activatedFeatures);

        CourseFeature.writeToFile(course1);
        CourseFeature.writeToFile(course2);
        CourseFeature.writeToFile(course3);
        CourseFeature.writeToFile(course4);

//
//        // Add 10 FlashCards
//        PracticeFeature.writeFlashCardToFile(new FlashCard("What is Java?", "A programming language"));
//        PracticeFeature.writeFlashCardToFile(new FlashCard("What is the capital of France?", "Paris"));
//        PracticeFeature.writeFlashCardToFile(new FlashCard("Who developed the theory of relativity?", "Albert Einstein"));
//        PracticeFeature.writeFlashCardToFile(new FlashCard("What is the largest planet in our solar system?", "Jupiter"));
//        PracticeFeature.writeFlashCardToFile(new FlashCard("Who wrote 'Romeo and Juliet'?", "William Shakespeare"));
//        PracticeFeature.writeFlashCardToFile(new FlashCard("What is 2 + 2?", "4"));
//        PracticeFeature.writeFlashCardToFile(new FlashCard("What is the boiling point of water?", "100°C"));
//        PracticeFeature.writeFlashCardToFile(new FlashCard("Who painted the Mona Lisa?", "Leonardo da Vinci"));
//        PracticeFeature.writeFlashCardToFile(new FlashCard("What is the chemical symbol for gold?", "Au"));
//        PracticeFeature.writeFlashCardToFile(new FlashCard("Who is known as the father of modern physics?", "Albert Einstein"));
//
//        // Add 10 MCQs
//        PracticeFeature.writeMCQsToFile(new MCQs("What is the largest ocean on Earth?", "Pacific Ocean", new String[] {"Atlantic Ocean", "Pacific Ocean", "Indian Ocean", "Arctic Ocean"}));
//        PracticeFeature.writeMCQsToFile(new MCQs("What is the capital of Japan?", "Tokyo", new String[] {"Kyoto", "Tokyo", "Osaka", "Sapporo"}));
//        PracticeFeature.writeMCQsToFile(new MCQs("What is the square root of 16?", "4", new String[] {"2", "4", "8", "16"}));
//        PracticeFeature.writeMCQsToFile(new MCQs("Which element is known as the building block of life?", "Carbon", new String[] {"Oxygen", "Hydrogen", "Carbon", "Nitrogen"}));
//        PracticeFeature.writeMCQsToFile(new MCQs("Who invented the telephone?", "Alexander Graham Bell", new String[] {"Thomas Edison", "Alexander Graham Bell", "Nikola Tesla", "Benjamin Franklin"}));
//        PracticeFeature.writeMCQsToFile(new MCQs("Which country is famous for the Great Wall?", "China", new String[] {"China", "India", "Japan", "Vietnam"}));
//        PracticeFeature.writeMCQsToFile(new MCQs("What is the freezing point of water?", "0°C", new String[] {"0°C", "32°F", "-1°C", "100°C"}));
//        PracticeFeature.writeMCQsToFile(new MCQs("Which country is known for kangaroos?", "Australia", new String[] {"Australia", "New Zealand", "South Africa", "Canada"}));
//        PracticeFeature.writeMCQsToFile(new MCQs("What is the currency of the United Kingdom?", "Pound Sterling", new String[] {"Euro", "Pound Sterling", "Dollar", "Yen"}));
//        PracticeFeature.writeMCQsToFile(new MCQs("Who was the first president of the United States?", "George Washington", new String[] {"Thomas Jefferson", "George Washington", "Abraham Lincoln", "John Adams"}));

        Controller controller = new Controller();
        Scanner scanner = new Scanner(System.in);

        // Maintain a map of buttons with their feature names for easy access and removal
        Map<String, JButton> featureButtons = new HashMap<>();

        // Show initial system state
        System.out.println("Initial system state:");
        printSystemState(controller);


        boolean exit = false;
        while (!exit) {
            // Refresh UI after changes

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

    private static void printSystemState(Controller controller) {
        String[] logs = controller.getStateAsLog();
        for (String log : logs) {
            System.out.println(log);
        }
    }
}