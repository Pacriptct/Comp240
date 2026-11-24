/**
 * Capability contract for pass types that can report a discount rate.
 * A rate of 0.50 means 50% is removed from the base fare.
 */
public interface DiscountEligible {
    double discountRate();
}
