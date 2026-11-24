/** Immutable record of one successfully processed ride. Demonstrates HAS-A. */
public class TripRecord {
    private final String tripId;
    private final TransitPass pass; // TripRecord HAS-A TransitPass reference.
    private final String route;
    private final double baseFare;
    private final double chargedFare;

    public TripRecord(String tripId, TransitPass pass, String route,
                      double baseFare, double chargedFare) {
        if (tripId == null || tripId.isBlank()) throw new IllegalArgumentException("Trip ID is required.");
        if (pass == null) throw new IllegalArgumentException("Pass is required.");
        if (route == null || route.isBlank()) throw new IllegalArgumentException("Route is required.");
        if (baseFare < 0 || chargedFare < 0) throw new IllegalArgumentException("Fare values cannot be negative.");
        this.tripId = tripId;
        this.pass = pass;
        this.route = route;
        this.baseFare = baseFare;
        this.chargedFare = chargedFare;
    }

    public String getTripId() { return tripId; }
    public TransitPass getPass() { return pass; }
    public String getRoute() { return route; }
    public double getBaseFare() { return baseFare; }
    public double getChargedFare() { return chargedFare; }

    @Override
    public String toString() {
        return tripId + " | " + pass.getPassId() + " | " + route
                + " | base $" + String.format("%.2f", baseFare)
                + " | charged $" + String.format("%.2f", chargedFare);
    }
}
