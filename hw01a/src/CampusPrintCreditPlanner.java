import java.util.Scanner;

/**
 * COMP 240 - HW01A
 * Campus Printing Credit Snapshot
 *
 * Reads a student's weekly print credit, planned pages, and average cost per
 * page, then shows the projected charge and how much credit is left (or how
 * far over the credit the plan goes). Week 1 version: no decisions or loops.
 *
 * Author: Dylan McLean
 */
public class CampusPrintCreditPlanner {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== Campus Printing Credit Snapshot ===");
        System.out.println("This estimates whether your planned printing fits in your weekly credit.");
        System.out.println("Enter numbers only (no $ sign). Pages must be a whole number.");
        System.out.println();

        // A1: weekly printing credit
        System.out.print("Enter your weekly printing credit: $");
        double weeklyPrintCredit = input.nextDouble();

        // A2: pages planned this week
        System.out.print("Enter the number of pages you plan to print: ");
        int plannedPages = input.nextInt();

        // A3: average cost per page
        System.out.print("Enter the average cost per page: $");
        double averageCostPerPage = input.nextDouble();

        // A4: projected charge from the method
        double projectedCharge = calculateProjectedCharge(plannedPages, averageCostPerPage);

        // A5: credit difference from the method
        double creditDifference = calculateCreditDifference(weeklyPrintCredit, projectedCharge);

        // A6: display results
        System.out.println();
        System.out.println("--- Printing Credit Snapshot ---");
        System.out.printf("Weekly print credit:     $%.2f%n", weeklyPrintCredit);
        System.out.printf("Planned pages:           %d%n", plannedPages);
        System.out.printf("Average cost per page:   $%.2f%n", averageCostPerPage);
        System.out.printf("Projected charge:        $%.2f%n", projectedCharge);
        System.out.printf("Credit difference:       $%.2f%n", creditDifference);
        System.out.println("(positive = credit left over, negative = over your credit)");

        input.close();
    }

    /** Computes the projected printing charge. */
    public static double calculateProjectedCharge(int pages, double costPerPage) {
        // A7: total cost = pages times cost per page
        return pages * costPerPage;
    }

    /** Computes available credit minus projected charge. */
    public static double calculateCreditDifference(double credit, double projectedCharge) {
        // A8: what's left after the charge (negative means over budget)
        return credit - projectedCharge;
    }
}
