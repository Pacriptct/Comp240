import java.util.ArrayList;
import java.util.Scanner;

public class ParkingPermitApp {
    private static final Scanner INPUT = new Scanner(System.in);
    private static final PermitManager MANAGER = new PermitManager();

    public static void main(String[] args) {
        seedPermits();
        boolean running = true;

        while (running) {
            displayMenu();
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1: displayAllPermits(); break;
                case 2: searchById(); break;
                case 3: displayByZone(); break;
                case 4: displaySummary(); break;
                case 5: deactivatePermit(); break;
                case 6: displayActivePermits(); break;
                case 0: running = false; break;
                default:
                    System.out.println("Choose a valid option (0-6).");
            }
            System.out.println();
        }

        System.out.println("Campus Parking Permit Manager ended.");
    }

    private static void seedPermits() {
        MANAGER.addPermit(new ParkingPermit(
                "S-101", "Avery Lee", "Student", "A", 45.00));
        MANAGER.addPermit(new ParkingPermit(
                "S-102", "Jordan Smith", "Student", "B", 35.00));
        MANAGER.addPermit(new ParkingPermit(
                "F-201", "Dr. Rivera", "Faculty", "A", 75.00));
        MANAGER.addPermit(new ParkingPermit(
                "F-202", "Dr. Chen", "Faculty", "C", 75.00));
    }

    private static void displayMenu() {
        System.out.println("================================");
        System.out.println(" Campus Parking Permit Manager");
        System.out.println("================================");
        System.out.println("1. Display all permits");
        System.out.println("2. Search permit by ID");
        System.out.println("3. Display permits by zone");
        System.out.println("4. Display permit summary");
        System.out.println("5. Deactivate a permit");
        System.out.println("6. Display active permits");
        System.out.println("0. Exit");
    }

    private static void displayAllPermits() {
        printPermits(MANAGER.getAllPermits());
    }

    private static void searchById() {
        String id = readNonBlank("Permit ID: ");
        ParkingPermit permit = MANAGER.findById(id);
        System.out.println(permit == null ? "Permit not found." : permit);
    }

    // TODO 5 — WEEK 5 SEARCH/FILTER WORKFLOW
    // Read a zone, validate it, call MANAGER.findByZone(...),
    // then pass the returned list to printPermits(...).
    private static void displayByZone() {
        String zone = readNonBlank("Zone (A, B, or C): ");

        if (!MANAGER.isValidZone(zone)) {
            System.out.println("Invalid zone \"" + zone + "\". Valid zones are A, B, and C.");
            return;
        }

        System.out.println("Permits in Zone " + zone.toUpperCase() + ":");
        printPermits(MANAGER.findByZone(zone));
    }

    // TODO 6 — WEEK 5 AGGREGATION
    // Display the total number of permits and number of active permits.
    private static void displaySummary() {
        int total = MANAGER.countAllPermits();
        int active = MANAGER.countActivePermits();

        System.out.println("Total permits:    " + total);
        System.out.println("Active permits:   " + active);
        System.out.println("Inactive permits: " + (total - active));
    }

    // TODO 7 — PROJECT 1 TRANSFER: THIN UI -> MANAGER DELEGATION
    // Read a permit ID, delegate to MANAGER.deactivatePermit(...),
    // then display an appropriate success/rejection message.
    private static void deactivatePermit() {
        String id = readNonBlank("Permit ID to deactivate: ");

        if (MANAGER.deactivatePermit(id)) {
            System.out.println("Permit " + id.toUpperCase() + " was deactivated.");
        } else {
            System.out.println("Rejected: permit " + id
                    + " was not found or is already inactive.");
        }
    }

    private static void displayActivePermits() {
        printPermits(MANAGER.getActivePermits());
    }

    private static void printPermits(ArrayList<ParkingPermit> permits) {
        if (permits.isEmpty()) {
            System.out.println("No permits found.");
            return;
        }

        for (ParkingPermit permit : permits) {
            System.out.println(permit);
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String text = INPUT.nextLine().trim();

            try {
                return Integer.parseInt(text);
            } catch (NumberFormatException ex) {
                System.out.println("Enter a whole number.");
            }
        }
    }

    private static String readNonBlank(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = INPUT.nextLine().trim();

            if (!value.isEmpty()) return value;
            System.out.println("Input cannot be blank.");
        }
    }
}
