import java.util.ArrayList;

/**
 * Manages all transit passes and successful trip records.
 * This class contains collection-wide business operations.
 */
public class FareManager {
    // Stores different TransitPass subclasses through one parent type.
    private final ArrayList<TransitPass> passes = new ArrayList<>();

    // Stores each successfully processed ride.
    private final ArrayList<TripRecord> trips = new ArrayList<>();

    /** Adds a pass when its ID is unique. */
    public boolean registerPass(TransitPass pass) {
        if (pass == null || findPassById(pass.getPassId()) != null) return false;
        passes.add(pass);
        return true;
    }

    /** Searches the pass collection and returns the matching pass. */
    public TransitPass findPassById(String passId) {
        if (passId == null || passId.isBlank()) return null;
        for (TransitPass pass : passes) {
            if (pass.getPassId().equalsIgnoreCase(passId)) return pass;
        }
        return null;
    }

    /** Checks whether a trip ID has already been used. */
    private boolean tripIdExists(String tripId) {
        if (tripId == null || tripId.isBlank()) return false;
        for (TripRecord trip : trips) {
            if (trip.getTripId().equalsIgnoreCase(tripId)) return true;
        }
        return false;
    }

    /**
     * Processes one ride using polymorphism.
     * The pass is handled only as a TransitPass. Calling pass.calculateFare(...)
     * runs the override for whatever subtype the object really is, so there is
     * no instanceof check or switch on pass type here.
     */
    public String processRide(String tripId, String passId, String route, double baseFare) {
        if (tripId == null || tripId.isBlank() || tripIdExists(tripId)) {
            return "Rejected: trip ID is blank or has already been used.";
        }

        TransitPass pass = findPassById(passId);
        if (pass == null) {
            return "Rejected: no pass found with ID " + passId + ".";
        }
        if (!pass.isActive()) {
            return "Rejected: pass " + pass.getPassId() + " is inactive.";
        }

        // The pass's own calculateFare(...) runs the shared validateBaseFare(...)
        // check. If the fare is invalid it throws, so turn that into a rejection
        // message instead of letting it crash the program. Nothing is recorded.
        double chargedFare;
        try {
            chargedFare = pass.calculateFare(baseFare);
        } catch (IllegalArgumentException ex) {
            return "Rejected: " + ex.getMessage();
        }

        TripRecord trip = new TripRecord(tripId, pass, route, baseFare, chargedFare);
        trips.add(trip);

        return String.format("Ride %s processed for %s (%s). Base fare $%.2f, charged fare $%.2f.",
                tripId, pass.getHolderName(), pass.getClass().getSimpleName(), baseFare, chargedFare);
    }

    /** Deactivates a pass through the pass object's own behavior. */
    public boolean deactivatePass(String passId) {
        TransitPass pass = findPassById(passId);
        if (pass != null) {
            pass.deactivate();
            return true;
        }
        return false;
    }

    /** Adds up the charged fare from every successful trip. */
    public double totalCollectedFare() {
        double total = 0.0;
        for (TripRecord trip : trips) {
            total += trip.getChargedFare();
        }
        return total;
    }

    /** Builds a new list of trips that used the given pass. */
    public ArrayList<TripRecord> findTripsByPassId(String passId) {
        ArrayList<TripRecord> matches = new ArrayList<>();
        if (passId == null) return matches;

        for (TripRecord trip : trips) {
            if (trip.getPass().getPassId().equalsIgnoreCase(passId.trim())) {
                matches.add(trip);
            }
        }
        return matches;
    }

    public ArrayList<TransitPass> getPasses() { return new ArrayList<>(passes); }
    public ArrayList<TripRecord> getTrips() { return new ArrayList<>(trips); }
}
