import java.util.ArrayList;

public class PermitManager {
    // WEEK 5 FIXED-ARRAY CONNECTION:
    // Campus parking zones are a known, fixed set.
    private final String[] validZones = {"A", "B", "C"};

    // WEEK 5 DYNAMIC-COLLECTION CONNECTION:
    // The number of issued permits can grow during execution.
    private final ArrayList<ParkingPermit> permits = new ArrayList<>();

    public boolean isValidZone(String zone) {
        if (zone == null) return false;

        // Traverse the fixed array.
        for (String validZone : validZones) {
            if (validZone.equalsIgnoreCase(zone.trim())) {
                return true;
            }
        }
        return false;
    }

    public boolean addPermit(ParkingPermit permit) {
        if (permit == null) return false;
        if (!isValidZone(permit.getZone())) return false;
        if (findById(permit.getPermitId()) != null) return false;

        permits.add(permit);
        return true;
    }

    public ParkingPermit findById(String permitId) {
        if (permitId == null) return null;

        // Search an ArrayList of objects by a unique identifier.
        for (ParkingPermit permit : permits) {
            if (permit.getPermitId().equalsIgnoreCase(permitId.trim())) {
                return permit;
            }
        }
        return null;
    }

    public ArrayList<ParkingPermit> getAllPermits() {
        return new ArrayList<>(permits);
    }

    // TODO 1 — WEEK 5: FILTERING AN ARRAYLIST OF OBJECTS
    // Build a new result list with only the permits in the requested zone.
    public ArrayList<ParkingPermit> findByZone(String zone) {
        ArrayList<ParkingPermit> results = new ArrayList<>();
        if (zone == null) return results;

        String target = zone.trim();
        for (ParkingPermit permit : permits) {
            if (permit.getZone().equalsIgnoreCase(target)) {
                results.add(permit);
            }
        }
        return results;
    }

    // TODO 2 — WEEK 5: COUNTING / AGGREGATION
    // Count only the permits whose active state is true.
    public int countActivePermits() {
        int count = 0;
        for (ParkingPermit permit : permits) {
            if (permit.isActive()) {
                count++;
            }
        }
        return count;
    }

    // Total number of issued permits (active or not).
    public int countAllPermits() {
        return permits.size();
    }

    // TODO 3 — WEEK 5: FILTERING BY OBJECT STATE
    // Return a NEW ArrayList containing only active permit objects.
    public ArrayList<ParkingPermit> getActivePermits() {
        ArrayList<ParkingPermit> active = new ArrayList<>();
        for (ParkingPermit permit : permits) {
            if (permit.isActive()) {
                active.add(permit);
            }
        }
        return active;
    }

    // TODO 4 — PROJECT 1 TRANSFER: MANAGER-OWNED BUSINESS RULE
    // Reject a missing or already-inactive permit. Otherwise deactivate it.
    public boolean deactivatePermit(String permitId) {
        ParkingPermit permit = findById(permitId);

        if (permit == null) return false;
        if (!permit.isActive()) return false;

        permit.deactivate();
        return true;
    }
}
