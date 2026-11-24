/**
 * VisitorPass represents a visitor transit pass.
 * Visitors pay the full valid base fare, so it does not implement DiscountEligible.
 */
public class VisitorPass extends TransitPass {
    public VisitorPass(String passId, String holderName) {
        super(passId, holderName);
    }

    @Override
    public double calculateFare(double baseFare) {
        validateBaseFare(baseFare);
        return baseFare;
    }
}
