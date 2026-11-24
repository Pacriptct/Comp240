/**
 * EmployeePass represents an employee transit pass.
 * It inherits common pass data and behavior from TransitPass.
 * Employees get a 75% discount, so they pay 25% of the base fare.
 */
public class EmployeePass extends TransitPass implements DiscountEligible {
    public EmployeePass(String passId, String holderName) {
        super(passId, holderName);
    }

    @Override
    public double discountRate() {
        return 0.75; // 75% discount means the rider pays 25%.
    }

    @Override
    public double calculateFare(double baseFare) {
        validateBaseFare(baseFare);
        return baseFare * (1.0 - discountRate());
    }
}
