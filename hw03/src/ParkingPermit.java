public class ParkingPermit {
    private final String permitId;
    private final String holderName;
    private final String permitType;
    private final String zone;
    private final double fee;
    private boolean active;

    public ParkingPermit(String permitId, String holderName,
                         String permitType, String zone, double fee) {
        if (permitId == null || permitId.isBlank()) {
            throw new IllegalArgumentException("Permit ID is required.");
        }
        if (holderName == null || holderName.isBlank()) {
            throw new IllegalArgumentException("Holder name is required.");
        }
        if (permitType == null || permitType.isBlank()) {
            throw new IllegalArgumentException("Permit type is required.");
        }
        if (zone == null || zone.isBlank()) {
            throw new IllegalArgumentException("Zone is required.");
        }
        if (fee < 0) {
            throw new IllegalArgumentException("Fee cannot be negative.");
        }

        this.permitId = permitId.trim();
        this.holderName = holderName.trim();
        this.permitType = permitType.trim();
        this.zone = zone.trim();
        this.fee = fee;
        this.active = true;
    }

    public String getPermitId() { return permitId; }
    public String getHolderName() { return holderName; }
    public String getPermitType() { return permitType; }
    public String getZone() { return zone; }
    public double getFee() { return fee; }
    public boolean isActive() { return active; }

    public void deactivate() {
        active = false;
    }

    public String toString() {
        return String.format("%s | %s | %s | Zone %s | $%.2f | %s",
                permitId, holderName, permitType, zone, fee,
                active ? "ACTIVE" : "INACTIVE");
    }
}
