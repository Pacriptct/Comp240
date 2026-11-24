/**
 * Common parent type for all transit passes.
 *
 * TransitPass is abstract because a "generic" pass with no fare rule shouldn't
 * exist. Every concrete pass type has to supply its own calculateFare(...).
 */
public abstract class TransitPass {
    // Unique identifier used when searching for a pass.
    private final String passId;

    // Name of the rider who owns/uses the pass.
    private final String holderName;

    // Shared state: inactive passes cannot be used for new rides.
    private boolean active;

    /** Establishes valid shared state for every pass object. */
    public TransitPass(String passId, String holderName) {
        if (passId == null || passId.isBlank()) {
            throw new IllegalArgumentException("Pass ID is required.");
        }
        if (holderName == null || holderName.isBlank()) {
            throw new IllegalArgumentException("Holder name is required.");
        }
        this.passId = passId;
        this.holderName = holderName;
        this.active = true;
    }

    // Accessors expose read-only views of private shared state.
    public String getPassId() { return passId; }
    public String getHolderName() { return holderName; }
    public boolean isActive() { return active; }

    /** Changes the pass state from active to inactive. */
    public void deactivate() { active = false; }

    /**
     * Abstract fare contract. Each subclass overrides this with its own rule.
     * FareManager calls it through a TransitPass reference, and Java runs the
     * version that belongs to the object's actual type (dynamic dispatch).
     */
    public abstract double calculateFare(double baseFare);

    /** Shared validation helper available to subclasses. */
    protected void validateBaseFare(double baseFare) {
        if (baseFare < 0) {
            throw new IllegalArgumentException("Base fare cannot be negative.");
        }
    }

    @Override
    public String toString() {
        return passId + " | " + holderName + " | " + getClass().getSimpleName()
                + " | " + (active ? "ACTIVE" : "INACTIVE");
    }
}
