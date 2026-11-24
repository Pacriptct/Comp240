/**
 * COMP 240 - HW02: Campus Package Locker Tracker
 *
 * PROVIDED IMPLEMENTATION (~30% baseline)
 * This complete class is the worked model for the classes you will finish.
 *
 * Lecture: Week 3 Slides 6-13.
 * Textbook: Wu, Ch. 4, pp. 152-160; Sec. 4.5, pp. 173-179.
 */
public class PackageLocker {
    private final String lockerId;
    private final String location;
    private final String sizeCategory;
    private boolean occupied;

    public PackageLocker(String lockerId, String location, String sizeCategory) {
        if (lockerId == null || lockerId.isBlank()) {
            throw new IllegalArgumentException("Locker ID is required.");
        }
        if (location == null || location.isBlank()) {
            throw new IllegalArgumentException("Location is required.");
        }
        if (sizeCategory == null || sizeCategory.isBlank()) {
            throw new IllegalArgumentException("Size category is required.");
        }

        this.lockerId = lockerId;
        this.location = location;
        this.sizeCategory = sizeCategory;
        this.occupied = false;
    }

    public String getLockerId() { return lockerId; }
    public String getLocation() { return location; }
    public String getSizeCategory() { return sizeCategory; }
    public boolean isOccupied() { return occupied; }

    public void markOccupied() { occupied = true; }
    public void markAvailable() { occupied = false; }

    @Override
    public String toString() {
        return lockerId + " | " + location + " | " + sizeCategory
                + " | " + (occupied ? "OCCUPIED" : "AVAILABLE");
    }
}
