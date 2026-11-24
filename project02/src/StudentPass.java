/**
 * StudentPass represents a student transit pass.
 * It inherits the common identity and active-state behavior from TransitPass.
 * Students get a 50% discount, so they pay half the base fare.
 */
public class StudentPass extends TransitPass implements DiscountEligible {
    public StudentPass(String passId, String holderName) {
        // Constructor chaining: super(...) runs the TransitPass constructor first,
        // so passId and holderName are validated and stored in the parent class.
        // StudentPass doesn't redeclare those fields, it just reuses them.
        super(passId, holderName);
    }

    @Override
    public double discountRate() {
        return 0.50;
    }

    @Override
    public double calculateFare(double baseFare) {
        validateBaseFare(baseFare);
        return baseFare * (1.0 - discountRate());
    }
}
