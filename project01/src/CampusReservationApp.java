import java.util.ArrayList;
import java.util.Scanner;

/**
 * COMP 240 Project 1
 * Campus Resource Reservation System
 *
 * CampusReservationApp is the console/UI coordinator. It reads input,
 * hands the real work to ReservationManager, and prints the results.
 * No business rules live in this class.
 *
 * DEMO ANALOGUE: EquipmentCheckoutApp.java (Slides 23-31)
 */
public class CampusReservationApp {

    private static final Scanner INPUT = new Scanner(System.in);
    private static final ReservationManager MANAGER =
            new ReservationManager();

    public static void main(String[] args) {

        seedResources();

        System.out.println("================================================");
        System.out.println("      CAMPUS RESOURCE RESERVATION SYSTEM");
        System.out.println("================================================");

        boolean running = true;

        while (running) {
            displayMenu();
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1:
                    displayResources();
                    break;
                case 2:
                    searchResourceById();
                    break;
                case 3:
                    searchResourcesByKeyword();
                    break;
                case 4:
                    createReservation();
                    break;
                case 5:
                    cancelReservation();
                    break;
                case 6:
                    displayReservations();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Unsupported menu choice.");
            }

            System.out.println();
        }

        System.out.println("Reservation system ended.");
        INPUT.close();
    }

    /**
     * Seed data so the system has resources to test with as soon as it starts.
     */
    private static void seedResources() {
        MANAGER.addResource(new CampusResource("R101", "Study Room A",
                "Study Room", "Thomas Library 1st Floor", 4));
        MANAGER.addResource(new CampusResource("R102", "Study Room B",
                "Study Room", "Thomas Library 2nd Floor", 6));
        MANAGER.addResource(new CampusResource("L201", "Computer Lab",
                "Lab", "Science Center 210", 24));
        MANAGER.addResource(new CampusResource("C301", "Conference Room",
                "Meeting Room", "Hollenbeck Hall 105", 12));
        MANAGER.addResource(new CampusResource("G401", "Basketball Court",
                "Athletic", "HPER Center", 20));
    }

    private static void displayMenu() {
        System.out.println("------------------ MENU -------------------");
        System.out.println("1. Display all resources");
        System.out.println("2. Search resource by ID");
        System.out.println("3. Search resources by name/type");
        System.out.println("4. Create reservation");
        System.out.println("5. Cancel reservation");
        System.out.println("6. Display reservations");
        System.out.println("0. Exit");
    }

    private static void displayResources() {
        ArrayList<CampusResource> resources = MANAGER.getResources();

        if (resources.isEmpty()) {
            System.out.println("No resources are available.");
            return;
        }

        System.out.println("All resources (" + resources.size() + "):");
        for (CampusResource resource : resources) {
            System.out.println(resource);
        }
    }

    private static void searchResourceById() {
        String id = readNonBlank("Enter resource ID: ");
        CampusResource resource = MANAGER.findResourceById(id);

        if (resource == null) {
            System.out.println("No resource found with ID " + id + ".");
        } else {
            System.out.println("Found: " + resource);
        }
    }

    private static void searchResourcesByKeyword() {
        String keyword = readNonBlank("Enter name, type, or location keyword: ");
        ArrayList<CampusResource> matches = MANAGER.searchResources(keyword);

        if (matches.isEmpty()) {
            System.out.println("No resources matched \"" + keyword + "\".");
            return;
        }

        System.out.println(matches.size() + " match(es):");
        for (CampusResource resource : matches) {
            System.out.println(resource);
        }
    }

    /**
     * INPUT -> DELEGATE -> OUTPUT. The capacity and conflict rules are
     * handled by the manager, not here.
     */
    private static void createReservation() {
        String reservationId = readNonBlank("Reservation ID: ");
        String requester = readNonBlank("Requester name: ");
        String resourceId = readNonBlank("Resource ID: ");
        String date = readNonBlank("Date (YYYY-MM-DD): ");
        String timeSlot = readNonBlank("Time slot (ex: 10:00-11:00): ");
        int partySize = readPositiveInt("Party size: ");

        // Arguments passed in the same order the manager method expects.
        String result = MANAGER.createReservation(reservationId, requester,
                resourceId, date, timeSlot, partySize);

        System.out.println(result);
    }

    private static void cancelReservation() {
        String reservationId = readNonBlank("Reservation ID to cancel: ");

        if (MANAGER.cancelReservation(reservationId)) {
            System.out.println("Reservation " + reservationId.toUpperCase()
                    + " was cancelled.");
        } else {
            System.out.println("No reservation found with ID " + reservationId + ".");
        }
    }

    private static void displayReservations() {
        ArrayList<Reservation> reservations = MANAGER.getReservations();

        if (reservations.isEmpty()) {
            System.out.println("There are no reservations.");
            return;
        }

        System.out.println("All reservations (" + reservations.size() + "):");
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = INPUT.nextLine().trim();

            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static int readPositiveInt(String prompt) {
        while (true) {
            int value = readInt(prompt);
            if (value > 0) {
                return value;
            }
            System.out.println("Please enter a number greater than 0.");
        }
    }

    private static String readNonBlank(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = INPUT.nextLine().trim();
            if (!line.isEmpty()) {
                return line;
            }
            System.out.println("This field cannot be blank.");
        }
    }
}
