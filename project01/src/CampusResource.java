/**
 * COMP 240 Project 1
 * Campus Resource Reservation System
 *
 * CampusResource represents one reservable campus resource (a study room,
 * a lab, a gym court, etc). It owns its own state and the capacity rule.
 *
 * DEMO ANALOGUE: EquipmentItem.java (Slides 7-11)
 */
public class CampusResource {

    private final String resourceId;
    private final String name;
    private final String type;
    private final String location;
    private final int capacity;

    /**
     * Builds a resource after checking that every value makes sense.
     * A resource needs an ID, name, type, and location that aren't blank,
     * and a capacity of at least 1 person.
     */
    public CampusResource(String resourceId, String name,
                          String type, String location, int capacity) {

        if (isBlank(resourceId)) {
            throw new IllegalArgumentException("Resource ID cannot be blank.");
        }
        if (isBlank(name)) {
            throw new IllegalArgumentException("Resource name cannot be blank.");
        }
        if (isBlank(type)) {
            throw new IllegalArgumentException("Resource type cannot be blank.");
        }
        if (isBlank(location)) {
            throw new IllegalArgumentException("Resource location cannot be blank.");
        }
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0.");
        }

        this.resourceId = resourceId.trim().toUpperCase();
        this.name = name.trim();
        this.type = type.trim();
        this.location = location.trim();
        this.capacity = capacity;
    }

    public String getResourceId() {
        return resourceId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }

    /**
     * Resource-owned capacity rule. A party fits if it has at least one
     * person and does not go over this resource's capacity.
     */
    public boolean canAccommodate(int partySize) {
        return partySize > 0 && partySize <= capacity;
    }

    @Override
    public String toString() {
        return String.format("%-6s | %-18s | %-12s | %-26s | capacity %d",
                resourceId, name, type, location, capacity);
    }

    private static boolean isBlank(String text) {
        return text == null || text.trim().isEmpty();
    }
}
