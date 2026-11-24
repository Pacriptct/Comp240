import java.util.ArrayList;
import java.util.Scanner;

/**
 * Console user-interface layer.
 * It gathers input, displays output, and delegates business operations to FareManager.
 */
public class TransitFareApp {
    private static final Scanner INPUT = new Scanner(System.in);
    private static final FareManager MANAGER = new FareManager();

    public static void main(String[] args) {
        seed();
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Choice: ");
            switch (choice) {
                case 1 -> displayPasses();
                case 2 -> searchPass();
                case 3 -> processRide();
                case 4 -> displayTrips();
                case 5 -> deactivatePass();
                case 6 -> displaySummary();
                case 7 -> displayTripsForPass();
                case 0 -> running = false;
                default -> System.out.println("Unknown menu choice.");
            }
        }
        System.out.println("Transit Fare Engine closed.");
    }

    /** Creates starter objects so the application has data to display and search. */
    private static void seed() {
        MANAGER.registerPass(new StudentPass("S100", "Ava Student"));
        MANAGER.registerPass(new EmployeePass("E200", "Eli Employee"));
        MANAGER.registerPass(new VisitorPass("V300", "Vera Visitor"));
        MANAGER.registerPass(new SeniorPass("S400", "Sam Senior"));
        MANAGER.registerPass(new CommunityPass("C500", "Cora Community"));
    }

    private static void printMenu() {
        System.out.println("\n1 Display passes\n2 Search pass\n3 Process ride\n4 Display trips");
        System.out.println("5 Deactivate pass\n6 Summary\n7 Trips for one pass\n0 Exit");
    }

    private static void displayPasses() {
        for (TransitPass pass : MANAGER.getPasses()) System.out.println(pass);
    }

    private static void searchPass() {
        String id = readNonBlank("Pass ID: ");
        TransitPass pass = MANAGER.findPassById(id);
        System.out.println(pass == null ? "Pass not found." : pass);
    }

    /** Process-ride workflow: INPUT -> DELEGATE -> OUTPUT. */
    private static void processRide() {
        String tripId = readNonBlank("Trip ID: ");
        String passId = readNonBlank("Pass ID: ");
        String route = readNonBlank("Route: ");
        double baseFare = readDouble("Base fare: ");

        String result = MANAGER.processRide(tripId, passId, route, baseFare);
        System.out.println(result);
    }

    private static void displayTrips() {
        ArrayList<TripRecord> trips = MANAGER.getTrips();
        if (trips.isEmpty()) {
            System.out.println("No successful trips recorded.");
            return;
        }
        for (TripRecord trip : trips) System.out.println(trip);
    }

    private static void deactivatePass() {
        String id = readNonBlank("Pass ID to deactivate: ");
        System.out.println(MANAGER.deactivatePass(id) ? "Pass deactivated." : "Pass not found.");
    }

    private static void displaySummary() {
        System.out.printf("Total collected fare: $%.2f%n", MANAGER.totalCollectedFare());
    }

    /** Filtered report: every trip taken with one pass. */
    private static void displayTripsForPass() {
        String id = readNonBlank("Pass ID: ");
        ArrayList<TripRecord> matches = MANAGER.findTripsByPassId(id);

        if (matches.isEmpty()) {
            System.out.println("No trips found for pass " + id + ".");
            return;
        }
        for (TripRecord trip : matches) {
            System.out.println(trip);
        }
    }

    private static String readNonBlank(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = INPUT.nextLine().trim();
            if (!value.isBlank()) return value;
            System.out.println("Value cannot be blank.");
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            try { return Integer.parseInt(readNonBlank(prompt)); }
            catch (NumberFormatException ex) { System.out.println("Enter a whole number."); }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            try { return Double.parseDouble(readNonBlank(prompt)); }
            catch (NumberFormatException ex) { System.out.println("Enter a numeric value."); }
        }
    }
}
