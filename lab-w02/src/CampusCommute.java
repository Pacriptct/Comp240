import java.util.Scanner;

/**
 * COMP 240 - Week 2 Integrated Practical Lab
 * Campus Commute Cost Estimator
 *
 * Week 1 core: reads trips, miles per trip, MPG, and fuel price, then works out
 * weekly miles, gallons used, and weekly fuel cost.
 * Week 2 additions: cost classification with named thresholds, a switch for the
 * report mode, a while loop that rejects MPG <= 0, and calculation methods.
 *
 * Author: Dylan McLean
 */
public class CampusCommute {

    // Named thresholds for the cost classification (no magic numbers).
    public static final double LOW_LIMIT = 10.0;
    public static final double MODERATE_LIMIT = 25.0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Number of campus trips this week: ");
        int trips = input.nextInt();

        System.out.print("Average miles per trip: ");
        double milesPerTrip = input.nextDouble();

        // Validation loop: keep asking while MPG is invalid (0 or negative),
        // so we never divide by zero or a meaningless value.
        System.out.print("Vehicle miles per gallon (> 0): ");
        double milesPerGallon = input.nextDouble();

        while (milesPerGallon <= 0) {
            System.out.println("MPG must be greater than 0.");
            System.out.print("Re-enter vehicle miles per gallon: ");
            milesPerGallon = input.nextDouble();
        }

        System.out.print("Fuel price per gallon: ");
        double fuelPrice = input.nextDouble();

        double weeklyMiles = calculateWeeklyMiles(trips, milesPerTrip);
        double gallonsUsed = calculateGallonsUsed(weeklyMiles, milesPerGallon);
        double weeklyFuelCost = calculateFuelCost(weeklyMiles, milesPerGallon, fuelPrice);
        String costStatus = classifyCost(weeklyFuelCost);

        System.out.println();
        System.out.println("Weekly miles: " + weeklyMiles);
        System.out.println("Estimated gallons: " + gallonsUsed);
        System.out.printf("Estimated fuel cost: $%.2f%n", weeklyFuelCost);
        System.out.println("Weekly commute cost status: " + costStatus);

        // switch handles a small set of discrete choices (report mode).
        System.out.println();
        System.out.print("Report mode (1=summary, 2=cost only): ");
        int reportMode = input.nextInt();

        switch (reportMode) {
            case 1:
                System.out.println("Miles: " + weeklyMiles);
                System.out.printf("Fuel cost: $%.2f%n", weeklyFuelCost);
                System.out.println("Status: " + costStatus);
                break;
            case 2:
                System.out.printf("Fuel cost: $%.2f%n", weeklyFuelCost);
                break;
            default:
                System.out.println("Unknown report mode.");
        }

        input.close();
    }

    /** Total miles driven this week. */
    public static double calculateWeeklyMiles(int trips, double milesPerTrip) {
        return trips * milesPerTrip;
    }

    /** Gallons of fuel used for the week's miles. */
    public static double calculateGallonsUsed(double weeklyMiles, double milesPerGallon) {
        return weeklyMiles / milesPerGallon;
    }

    /** Weekly fuel cost: gallons used times the price per gallon. */
    public static double calculateFuelCost(double weeklyMiles,
                                           double milesPerGallon,
                                           double fuelPrice) {
        double gallonsUsed = calculateGallonsUsed(weeklyMiles, milesPerGallon);
        return gallonsUsed * fuelPrice;
    }

    /**
     * Classifies the weekly cost. Under $10.00 is LOW, $10.00 up to and
     * including $25.00 is MODERATE, and over $25.00 is HIGH.
     */
    public static String classifyCost(double weeklyFuelCost) {
        if (weeklyFuelCost < LOW_LIMIT) {
            return "LOW";
        } else if (weeklyFuelCost <= MODERATE_LIMIT) {
            return "MODERATE";
        } else {
            return "HIGH";
        }
    }
}
