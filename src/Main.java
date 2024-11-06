import controller.MainController;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MainController controller = new MainController();
        Scanner scanner = new Scanner(System.in);

        // Show initial system state
        System.out.println("Initial system state:");

        System.out.println('\n');
        printSystemState(controller);

        boolean exit = false;
        while (!exit) {

            System.out.println('\n');
            System.out.println("Activated Features: " + controller.getActivatedFeatures());
            System.out.println("Deactivated Features: " + controller.getDeactivatedFeatures());

            // Prompt user for feature activations and deactivations
            System.out.println('\n');
            System.out.println("Enter features to activate (comma-separated): ");
            String[] activations = scanner.nextLine().split(",");

            System.out.println('\n');
            System.out.println("Enter features to deactivate (comma-separated): ");
            String[] deactivations = scanner.nextLine().split(",");

            // Process activations and deactivations
            System.out.println('\n');
            int result = controller.activate(deactivations, activations);
            System.out.println("Activation result: " + result);

            // Show current system state
            System.out.println('\n');
            System.out.println("Current system state:");
            printSystemState(controller);
        }

        scanner.close();
    }

    private static void printSystemState(MainController controller) {
        String[] logs = controller.getStateAsLog();
        for (String log : logs) {
            System.out.println(log);
        }
    }
}