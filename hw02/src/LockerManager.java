import java.util.ArrayList;

/**
 * COMP 240 - HW02: Campus Package Locker Tracker
 *
 * LockerManager coordinates collections and business rules involving multiple objects.
 * Locker registration and locker search are PROVIDED as part of the ~30% baseline.
 *
 * Lecture: Week 3 Slides 13, 14-15, and 25.
 */
public class LockerManager {
    private final ArrayList<PackageLocker> lockers = new ArrayList<>();
    private final ArrayList<PackageRecord> records = new ArrayList<>();

    // PROVIDED: validates before mutating the locker collection.
    public boolean addLocker(PackageLocker locker) {
        if (locker == null || findLockerById(locker.getLockerId()) != null) {
            return false;
        }
        lockers.add(locker);
        return true;
    }

    // PROVIDED: model for the search TODO below.
    public PackageLocker findLockerById(String lockerId) {
        if (lockerId == null) {
            return null;
        }

        for (PackageLocker locker : lockers) {
            if (locker.getLockerId().equalsIgnoreCase(lockerId)) {
                return locker;
            }
        }
        return null;
    }

    /**
     * Search active records by tracking ID (same pattern as findLockerById).
     */
    public PackageRecord findRecordByTrackingId(String trackingId) {
        if (trackingId == null) {
            return null;
        }

        for (PackageRecord record : records) {
            if (record.getTrackingId().equalsIgnoreCase(trackingId.trim())) {
                return record;
            }
        }

        return null;
    }

    /**
     * Assign a package. Rule: VALIDATE FIRST, MUTATE LAST.
     * Nothing changes unless every check passes.
     */
    public String assignPackage(String trackingId, String recipient, String lockerId) {
        // Stage 1: reject a duplicate active tracking ID.
        if (findRecordByTrackingId(trackingId) != null) {
            return "Rejected: tracking ID " + trackingId + " is already assigned to a locker.";
        }

        // Stage 2: locate the requested locker.
        PackageLocker locker = findLockerById(lockerId);

        // Stage 3: reject a missing locker.
        if (locker == null) {
            return "Rejected: locker " + lockerId + " does not exist.";
        }

        // Stage 4: reject an occupied locker.
        if (locker.isOccupied()) {
            return "Rejected: locker " + locker.getLockerId() + " is already occupied.";
        }

        // Stage 5: create the collaborating PackageRecord object.
        PackageRecord record = new PackageRecord(trackingId, recipient, locker);

        // Stage 6: synchronize the collection and locker state.
        records.add(record);
        locker.markOccupied();

        // Stage 7: report successful completion.
        return "Assigned package " + record.getTrackingId() + " for " + record.getRecipient()
                + " to locker " + locker.getLockerId() + " (" + locker.getLocation() + ").";
    }

    /**
     * Pick up a package: find the record, free its locker, remove the record.
     */
    public String pickupPackage(String trackingId) {
        PackageRecord record = findRecordByTrackingId(trackingId);

        if (record == null) {
            return "No active package found with tracking ID " + trackingId + ".";
        }

        PackageLocker locker = record.getLocker();

        locker.markAvailable();
        records.remove(record);

        return "Package " + record.getTrackingId() + " picked up by " + record.getRecipient()
                + ". Locker " + locker.getLockerId() + " is now available.";
    }

    public ArrayList<PackageLocker> getLockers() {
        return new ArrayList<>(lockers);
    }

    public ArrayList<PackageRecord> getRecords() {
        return new ArrayList<>(records);
    }
}
