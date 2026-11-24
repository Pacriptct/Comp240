/**
 * COMP 240 Project 1
 * Campus Resource Reservation System
 *
 * Reservation represents one reservation transaction. It HAS-A
 * CampusResource, meaning it holds a reference to the actual resource
 * object instead of just storing the resource ID as text.
 *
 * DEMO ANALOGUE: Checkout.java (Slides 12-15)
 */
public class Reservation {

    private final String reservationId;
    private final String requester;

    // HAS-A association: a Reservation refers to a CampusResource object.
    private final CampusResource resource;

    private final String date;
    private final String timeSlot;
    private final int partySize;

    /**
     * Builds a reservation after checking it is structurally valid.
     * Business rules (capacity, conflicts, duplicate IDs) are checked by
     * ReservationManager before this constructor is ever called.
     */
    public Reservation(String reservationId, String requester,
                       CampusResource resource, String date,
                       String timeSlot, int partySize) {

        if (isBlank(reservationId)) {
            throw new IllegalArgumentException("Reservation ID cannot be blank.");
        }
        if (isBlank(requester)) {
            throw new IllegalArgumentException("Requester cannot be blank.");
        }
        if (resource == null) {
            throw new IllegalArgumentException("A reservation must have a resource.");
        }
        if (isBlank(date)) {
            throw new IllegalArgumentException("Date cannot be blank.");
        }
        if (isBlank(timeSlot)) {
            throw new IllegalArgumentException("Time slot cannot be blank.");
        }
        if (partySize <= 0) {
            throw new IllegalArgumentException("Party size must be greater than 0.");
        }

        this.reservationId = reservationId.trim().toUpperCase();
        this.requester = requester.trim();
        this.resource = resource;
        this.date = date.trim();
        this.timeSlot = timeSlot.trim();
        this.partySize = partySize;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getRequester() {
        return requester;
    }

    public CampusResource getResource() {
        return resource;
    }

    public String getDate() {
        return date;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public int getPartySize() {
        return partySize;
    }

    /**
     * Project 1 conflict rule: two reservations conflict when they are for
     * the same resource, on the same date, in the same time slot.
     */
    public boolean conflictsWith(CampusResource otherResource,
                                 String otherDate,
                                 String otherTimeSlot) {
        if (otherResource == null || otherDate == null || otherTimeSlot == null) {
            return false;
        }

        boolean sameResource = resource.getResourceId()
                .equalsIgnoreCase(otherResource.getResourceId());
        boolean sameDate = date.equalsIgnoreCase(otherDate.trim());
        boolean sameSlot = timeSlot.equalsIgnoreCase(otherTimeSlot.trim());

        return sameResource && sameDate && sameSlot;
    }

    /**
     * Follows the associated CampusResource reference to show the resource
     * name and location along with the reservation info.
     */
    @Override
    public String toString() {
        return String.format("%-6s | %-14s | %s (%s) @ %s | %s %s | party of %d",
                reservationId, requester,
                resource.getName(), resource.getResourceId(), resource.getLocation(),
                date, timeSlot, partySize);
    }

    private static boolean isBlank(String text) {
        return text == null || text.trim().isEmpty();
    }
}
