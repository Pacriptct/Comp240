/**
 * CommunityPass represents a community program rider's transit pass.
 * CommunityPass IS-A TransitPass and is discount eligible.
 * Community riders get a 30% discount, so they pay 70% of the base fare.
 */
public class CommunityPass extends TransitPass implements DiscountEligible {
    public CommunityPass(String passId, String holderName) {
        super(passId, holderName);
    }

    @Override
    public double discountRate() {
        return 0.30;
    }

    @Override
    public double calculateFare(double baseFare) {
        validateBaseFare(baseFare);
        return baseFare * (1.0 - discountRate());
    }
}
