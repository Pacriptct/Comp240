/**
 * COMP 240 Project 1 - direct tests for the model and manager classes.
 * No JUnit needed. Each check prints PASS or FAIL.
 *
 * Compile/run from the project folder:
 *   javac -d out src/*.java tests/ReservationSystemTests.java
 *   java -cp out ReservationSystemTests
 */
public class ReservationSystemTests {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        System.out.println("=== CampusResource ===");
        CampusResource room = new CampusResource("r101", "Study Room A",
                "Study Room", "Thomas Library", 4);
        check("T01 valid resource stores ID in uppercase", room.getResourceId().equals("R101"));
        check("T02 getters return stored values",
                room.getName().equals("Study Room A") && room.getType().equals("Study Room")
                && room.getLocation().equals("Thomas Library") && room.getCapacity() == 4);
        check("T03 blank ID rejected", throwsIAE(() -> new CampusResource(" ", "A", "B", "C", 4)));
        check("T04 null name rejected", throwsIAE(() -> new CampusResource("X1", null, "B", "C", 4)));
        check("T05 blank type rejected", throwsIAE(() -> new CampusResource("X1", "A", "", "C", 4)));
        check("T06 blank location rejected", throwsIAE(() -> new CampusResource("X1", "A", "B", "", 4)));
        check("T07 zero capacity rejected", throwsIAE(() -> new CampusResource("X1", "A", "B", "C", 0)));
        check("T08 canAccommodate at capacity", room.canAccommodate(4));
        check("T09 canAccommodate over capacity is false", !room.canAccommodate(5));
        check("T10 canAccommodate zero is false", !room.canAccommodate(0));

        System.out.println("=== Reservation ===");
        Reservation res = new Reservation("res1", "Dylan", room, "2026-12-01", "10:00-11:00", 3);
        check("T11 reservation keeps HAS-A reference to the same resource object", res.getResource() == room);
        check("T12 getters return stored values",
                res.getReservationId().equals("RES1") && res.getRequester().equals("Dylan")
                && res.getDate().equals("2026-12-01") && res.getTimeSlot().equals("10:00-11:00")
                && res.getPartySize() == 3);
        check("T13 null resource rejected",
                throwsIAE(() -> new Reservation("R2", "A", null, "2026-12-01", "10:00-11:00", 2)));
        check("T14 blank requester rejected",
                throwsIAE(() -> new Reservation("R2", "", room, "2026-12-01", "10:00-11:00", 2)));
        check("T15 nonpositive party size rejected",
                throwsIAE(() -> new Reservation("R2", "A", room, "2026-12-01", "10:00-11:00", 0)));
        CampusResource other = new CampusResource("R102", "Study Room B", "Study Room", "Library", 6);
        check("T16 same resource/date/slot conflicts", res.conflictsWith(room, "2026-12-01", "10:00-11:00"));
        check("T17 different slot does not conflict", !res.conflictsWith(room, "2026-12-01", "11:00-12:00"));
        check("T18 different date does not conflict", !res.conflictsWith(room, "2026-12-02", "10:00-11:00"));
        check("T19 different resource does not conflict", !res.conflictsWith(other, "2026-12-01", "10:00-11:00"));
        check("T20 toString shows associated resource name", res.toString().contains("Study Room A"));

        System.out.println("=== ReservationManager ===");
        ReservationManager m = new ReservationManager();
        check("T21 addResource accepts new resource", m.addResource(room));
        check("T22 addResource rejects null", !m.addResource(null));
        check("T23 addResource rejects duplicate ID",
                !m.addResource(new CampusResource("R101", "Copy", "Study Room", "X", 2)));
        m.addResource(other);
        check("T24 findResourceById is case-insensitive", m.findResourceById("r102") == other);
        check("T25 findResourceById missing returns null", m.findResourceById("NOPE") == null);
        check("T26 keyword search matches type", m.searchResources("study room").size() == 2);
        check("T27 keyword search no match is empty", m.searchResources("pool").isEmpty());

        String r1 = m.createReservation("RES1", "Dylan", "R101", "2026-12-01", "10:00-11:00", 3);
        check("T28 valid reservation succeeds", r1.startsWith("SUCCESS") && m.getReservations().size() == 1);
        String r2 = m.createReservation("RES1", "Jordan", "R102", "2026-12-01", "10:00-11:00", 2);
        check("T29 duplicate reservation ID rejected", r2.startsWith("REJECTED") && r2.contains("already exists"));
        String r3 = m.createReservation("RES2", "Jordan", "Z999", "2026-12-01", "10:00-11:00", 2);
        check("T30 missing resource rejected", r3.startsWith("REJECTED") && r3.contains("No resource"));
        String r4 = m.createReservation("RES2", "Jordan", "R101", "2026-12-01", "11:00-12:00", 10);
        check("T31 over capacity rejected", r4.startsWith("REJECTED") && r4.contains("holds up to"));
        String r5 = m.createReservation("RES2", "Jordan", "R101", "2026-12-01", "10:00-11:00", 2);
        check("T32 conflicting reservation rejected", r5.startsWith("REJECTED") && r5.contains("already reserved"));
        check("T33 rejected requests did not change the list", m.getReservations().size() == 1);
        String r6 = m.createReservation("RES2", "Jordan", "R101", "2026-12-01", "11:00-12:00", 2);
        check("T34 same room, next slot succeeds", r6.startsWith("SUCCESS"));

        m.getReservations().clear();
        check("T35 defensive copy protects internal list", m.getReservations().size() == 2);
        check("T36 cancel missing ID returns false", !m.cancelReservation("RES9"));
        check("T37 cancel existing ID returns true", m.cancelReservation("res1"));
        check("T38 cancelled reservation is gone", m.findReservationById("RES1") == null
                && m.getReservations().size() == 1);
        String r7 = m.createReservation("RES3", "Sam", "R101", "2026-12-01", "10:00-11:00", 2);
        check("T39 slot can be rebooked after cancel", r7.startsWith("SUCCESS"));

        System.out.println();
        System.out.println("Passed: " + passed + "  Failed: " + failed);
    }

    private static void check(String name, boolean condition) {
        if (condition) {
            passed++;
            System.out.println("PASS  " + name);
        } else {
            failed++;
            System.out.println("FAIL  " + name);
        }
    }

    private static boolean throwsIAE(Runnable action) {
        try {
            action.run();
            return false;
        } catch (IllegalArgumentException ex) {
            return true;
        }
    }
}
