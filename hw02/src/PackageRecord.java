/**
 * COMP 240 - HW02: Campus Package Locker Tracker
 *
 * PackageRecord models one active package assignment and HAS-A PackageLocker.
 *
 * Lecture: Week 3 Slides 14-21.
 * Textbook: Wu, Sec. 4.4, pp. 168-173; Sec. 4.5, pp. 173-179.
 */
public class PackageRecord {
    private final String trackingId;
    private final String recipient;
    private final PackageLocker locker;

    /**
     * Builds a record after checking every required value.
     * A record needs a tracking ID, a recipient, and the locker holding the package.
     */
    public PackageRecord(String trackingId, String recipient, PackageLocker locker) {
        if (trackingId == null || trackingId.isBlank()) {
            throw new IllegalArgumentException("Tracking ID is required.");
        }
        if (recipient == null || recipient.isBlank()) {
            throw new IllegalArgumentException("Recipient is required.");
        }
        if (locker == null) {
            throw new IllegalArgumentException("A package record needs a locker.");
        }

        this.trackingId = trackingId.trim();
        this.recipient = recipient.trim();
        this.locker = locker;
    }

    // PROVIDED: manager needs this accessor for tracking-ID search.
    public String getTrackingId() {
        return trackingId;
    }

    // Accessor for the recipient's name.
    public String getRecipient() {
        return recipient;
    }

    // Accessor for the associated locker (HAS-A reference).
    public PackageLocker getLocker() {
        return locker;
    }

    // Readable display that follows the locker reference for its ID and location.
    @Override
    public String toString() {
        return trackingId
                + " | recipient: " + recipient
                + " | locker: " + locker.getLockerId()
                + " (" + locker.getLocation() + ", " + locker.getSizeCategory() + ")";
    }
}
