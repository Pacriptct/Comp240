import java.util.Scanner;
import java.util.ArrayList;

/**
 * COMP 240 - HW02: Campus Package Locker Tracker
 *
 * LockerApp is the console/UI layer: INPUT -> DELEGATE -> OUTPUT.
 *
 * Lecture: Week 3 Slides 13, 22-25.
 */
public class LockerApp {
    private static final Scanner INPUT = new Scanner(System.in);
    private static final LockerManager MANAGER = new LockerManager();

    public static void main(String[] args) {
        seedLockers();
        boolean running = true;

        while (running) {
            displayMenu();
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1 -> displayLockers();
                case 2 -> searchLocker();

                case 3 -> assignPackage();
                case 4 -> pickupPackage();
                case 5 -> displayPackages();

                case 0 -> running = false;
                default -> System.out.println("Unsupported choice.");
            }
            System.out.println();
        }

        System.out.println("Locker Tracker ended.");
        INPUT.close();
    }

    // PROVIDED: four starting locker objects.
    private static void seedLockers() {
        MANAGER.addLocker(new PackageLocker("L101", "Student Center", "SMALL"));
        MANAGER.addLocker(new PackageLocker("L102", "Student Center", "MEDIUM"));
        MANAGER.addLocker(new PackageLocker("L201", "Library", "LARGE"));
        MANAGER.addLocker(new PackageLocker("L202", "Library", "MEDIUM"));
    }

    private static void displayMenu() {
        System.out.println("1. Display lockers");
        System.out.println("2. Search locker by ID");
        System.out.println("3. Assign package");
        System.out.println("4. Pick up package");
        System.out.println("5. Display active packages");
        System.out.println("0. Exit");
    }

    // PROVIDED model: get objects from manager, then display them.
    private static void displayLockers() {
        for (PackageLocker locker : MANAGER.getLockers()) {
            System.out.println(locker);
        }
    }

    // PROVIDED model: INPUT -> DELEGATE -> OUTPUT.
    private static void searchLocker() {
        System.out.print("Locker ID: ");
        String id = INPUT.nextLine().trim();

        PackageLocker found = MANAGER.findLockerById(id);
        System.out.println(found == null ? "Locker not found." : found);
    }

    // PROVIDED robust integer-input pattern.
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(INPUT.nextLine().trim());
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    /**
     * Assignment workflow: INPUT -> DELEGATE -> OUTPUT.
     * The business rules (duplicate, missing, occupied) live in LockerManager.
     */
    private static void assignPackage() {
        String trackingId = readNonBlank("Tracking ID: ");
        String recipient = readNonBlank("Recipient name: ");
        String lockerId = readNonBlank("Locker ID: ");

        String result = MANAGER.assignPackage(trackingId, recipient, lockerId);
        System.out.println(result);
    }

    /**
     * Pickup workflow: read a tracking ID, delegate, print the result.
     */
    private static void pickupPackage() {
        String trackingId = readNonBlank("Tracking ID to pick up: ");

        String result = MANAGER.pickupPackage(trackingId);
        System.out.println(result);
    }

    /**
     * Displays active package records, or a message if there are none.
     */
    private static void displayPackages() {
        ArrayList<PackageRecord> records = MANAGER.getRecords();

        if (records.isEmpty()) {
            System.out.println("No active packages.");
            return;
        }

        for (PackageRecord record : records) {
            System.out.println(record);
        }
    }

    /**
     * Re-prompts until the user enters text that is not blank.
     */
    private static String readNonBlank(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = INPUT.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("Value cannot be blank.");
        }
    }
}
