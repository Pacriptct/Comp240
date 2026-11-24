import java.util.ArrayList;

/**
 * COMP 240 Project 1
 * Campus Resource Reservation System
 *
 * ReservationManager owns the resource and reservation collections and
 * handles every collection-wide operation and business rule.
 *
 * DEMO ANALOGUE: CheckoutManager.java (Slides 16-22)
 */
public class ReservationManager {

    private final ArrayList<CampusResource> resources = new ArrayList<>();
    private final ArrayList<Reservation> reservations = new ArrayList<>();

    /**
     * Adds a resource only if it isn't null and its ID isn't already used.
     */
    public boolean addResource(CampusResource resource) {
        if (resource == null) {
            return false;
        }
        if (findResourceById(resource.getResourceId()) != null) {
            return false;
        }
        resources.add(resource);
        return true;
    }

    /**
     * Exact-ID resource search (not case sensitive). Returns null if not found.
     */
    public CampusResource findResourceById(String resourceId) {
        if (resourceId == null) {
            return null;
        }
        String target = resourceId.trim();

        for (CampusResource resource : resources) {
            if (resource.getResourceId().equalsIgnoreCase(target)) {
                return resource;
            }
        }
        return null;
    }

    /**
     * Keyword search. A resource matches if the search text appears anywhere
     * in its name, type, or location (not case sensitive).
     */
    public ArrayList<CampusResource> searchResources(String searchText) {
        ArrayList<CampusResource> matches = new ArrayList<>();

        if (searchText == null || searchText.trim().isEmpty()) {
            return matches;
        }
        String keyword = searchText.trim().toLowerCase();

        for (CampusResource resource : resources) {
            if (resource.getName().toLowerCase().contains(keyword)
                    || resource.getType().toLowerCase().contains(keyword)
                    || resource.getLocation().toLowerCase().contains(keyword)) {
                matches.add(resource);
            }
        }
        return matches;
    }

    /**
     * Exact-ID reservation search (not case sensitive). Returns null if not found.
     */
    public Reservation findReservationById(String reservationId) {
        if (reservationId == null) {
            return null;
        }
        String target = reservationId.trim();

        for (Reservation reservation : reservations) {
            if (reservation.getReservationId().equalsIgnoreCase(target)) {
                return reservation;
            }
        }
        return null;
    }

    /**
     * Creates a reservation using ordered validation (validate before mutation).
     * Every rule is checked first. The reservations list is only changed
     * after all of them pass.
     */
    public String createReservation(String reservationId,
                                    String requester,
                                    String resourceId,
                                    String date,
                                    String timeSlot,
                                    int partySize) {

        // 5A: duplicate reservation ID
        if (findReservationById(reservationId) != null) {
            return "REJECTED: Reservation ID " + reservationId.trim().toUpperCase()
                    + " already exists.";
        }

        // 5B: resource has to exist
        CampusResource resource = findResourceById(resourceId);
        if (resource == null) {
            return "REJECTED: No resource found with ID " + resourceId + ".";
        }

        // 5C: the resource enforces its own capacity rule
        if (!resource.canAccommodate(partySize)) {
            return "REJECTED: " + resource.getName() + " holds up to "
                    + resource.getCapacity() + " people, but the party size is "
                    + partySize + ".";
        }

        // 5D: no conflict with an existing reservation
        for (Reservation existing : reservations) {
            if (existing.conflictsWith(resource, date, timeSlot)) {
                return "REJECTED: " + resource.getName() + " is already reserved on "
                        + date + " (" + timeSlot + ") by reservation "
                        + existing.getReservationId() + ".";
            }
        }

        // 5E: every rule passed, so now it is safe to create and add
        Reservation reservation;
        try {
            reservation = new Reservation(reservationId, requester, resource,
                    date, timeSlot, partySize);
        } catch (IllegalArgumentException ex) {
            return "REJECTED: " + ex.getMessage();
        }
        reservations.add(reservation);

        // 5F: success message
        return "SUCCESS: Reservation " + reservation.getReservationId()
                + " created for " + reservation.getRequester() + " at "
                + resource.getName() + " on " + reservation.getDate()
                + " (" + reservation.getTimeSlot() + ").";
    }

    /**
     * Cancels a reservation by ID. Returns false if it doesn't exist.
     */
    public boolean cancelReservation(String reservationId) {
        Reservation reservation = findReservationById(reservationId);
        if (reservation == null) {
            return false;
        }
        return reservations.remove(reservation);
    }

    /**
     * Defensive copies so outside code can't change the real lists.
     */
    public ArrayList<CampusResource> getResources() {
        return new ArrayList<>(resources);
    }

    public ArrayList<Reservation> getReservations() {
        return new ArrayList<>(reservations);
    }
}
