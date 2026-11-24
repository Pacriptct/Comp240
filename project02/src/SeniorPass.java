/**
 * SeniorPass represents a senior rider's transit pass.
 * SeniorPass IS-A TransitPass and is discount eligible.
 * Seniors get a 60% discount, so they pay 40% of the base fare.
 */
public class SeniorPass extends TransitPass implements DiscountEligible {
    public SeniorPass(String passId, String holderName) {
        // Constructor chaining: the TransitPass constructor validates and stores
        // the shared pass ID, holder name, and active state.
        super(passId, holderName);
    }

    @Override
    public double discountRate() {
        return 0.60;
    }

    @Override
    public double calculateFare(double baseFare) {
        validateBaseFare(baseFare);
        return baseFare * (1.0 - discountRate());
    }
}
