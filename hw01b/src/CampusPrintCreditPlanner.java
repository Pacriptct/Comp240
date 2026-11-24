import java.util.Scanner;

/**
 * COMP 240 - HW01B
 * Campus Printing Credit Planner
 *
 * Extends the HW01A Campus Printing Credit Snapshot. The user enters a weekly
 * print credit, an average cost per page, and a number of print jobs, then the
 * page count for each job. The program totals the pages, works out the
 * projected charge, average pages per job, and credit status, and lets the
 * user run another planning session.
 *
 * Author: Dylan McLean
 */
public class CampusPrintCreditPlanner {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("=== Campus Printing Credit Planner ===");
        System.out.println("Plan your printing for the week and see if it fits in your credit.");
        System.out.println("Enter numbers only (no $ sign).");

        boolean runAgain = true;
        int sessionNumber = 1;

        while (runAgain) {
            System.out.println();
            System.out.println("--- Planning Session " + sessionNumber + " ---");

            // Week 1 inputs (now validated)
            double weeklyPrintCredit = readNonNegativeDouble(input,
                    "Enter your weekly printing credit: $");
            double averageCostPerPage = readNonNegativeDouble(input,
                    "Enter the average cost per page: $");

            // Week 2: multiple print jobs with a loop + accumulator
            int numberOfJobs = readPositiveInt(input,
                    "How many print jobs are you planning? ");

            int totalPages = 0;
            for (int job = 1; job <= numberOfJobs; job++) {
                int pages = readNonNegativeInt(input, "  Pages for job " + job + ": ");
                totalPages += pages;
            }

            // Calculations (Week 1 methods reused)
            double projectedCharge = calculateProjectedCharge(totalPages, averageCostPerPage);
            double averagePagesPerJob = calculateAveragePages(totalPages, numberOfJobs);
            double creditDifference = calculateCreditDifference(weeklyPrintCredit, projectedCharge);
            String status = classifyCreditStatus(creditDifference);

            displaySummary(weeklyPrintCredit, averageCostPerPage, numberOfJobs, totalPages,
                    averagePagesPerJob, projectedCharge, creditDifference, status);

            runAgain = readYesNo(input, "Plan another printing session? (y/n): ");
            sessionNumber++;
        }

        System.out.println("Goodbye! Campus Printing Credit Planner ended.");
        input.close();
    }

    // -------------------- WEEK 1 RESPONSIBILITIES --------------------

    /** Total cost of the planned printing. (Kept from HW01A) */
    public static double calculateProjectedCharge(int pages, double costPerPage) {
        return pages * costPerPage;
    }

    /** Credit left after the charge; negative means over. (Kept from HW01A) */
    public static double calculateCreditDifference(double credit, double projectedCharge) {
        return credit - projectedCharge;
    }

    // -------------------- WEEK 2 EXTENSIONS --------------------

    /** Average pages per job using decimal division. */
    public static double calculateAveragePages(int totalPages, int numberOfJobs) {
        return (double) totalPages / numberOfJobs;
    }

    /**
     * Classifies the credit status. The difference is rounded to whole cents
     * first, because doubles like 3 * 0.10 come out as 0.30000000000000004 and
     * would make an exact match look like it went over by a tiny amount.
     */
    public static String classifyCreditStatus(double creditDifference) {
        long cents = Math.round(creditDifference * 100);

        if (cents > 0) {
            return "CREDIT REMAINS";
        } else if (cents == 0) {
            return "CREDIT EXACTLY USED";
        } else {
            return "CREDIT EXCEEDED";
        }
    }

    /** Re-prompts until the user enters a decimal number that is 0 or more. */
    public static double readNonNegativeDouble(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (input.hasNextDouble()) {
                double value = input.nextDouble();
                if (value >= 0) {
                    return value;
                }
                System.out.println("  Value cannot be negative. Try again.");
            } else {
                System.out.println("  \"" + input.next() + "\" is not a number. Try again.");
            }
        }
    }

    /** Re-prompts until the user enters a whole number that is 1 or more. */
    public static int readPositiveInt(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (input.hasNextInt()) {
                int value = input.nextInt();
                if (value > 0) {
                    return value;
                }
                System.out.println("  Must be at least 1. Try again.");
            } else {
                System.out.println("  \"" + input.next() + "\" is not a whole number. Try again.");
            }
        }
    }

    /** Re-prompts until the user enters a whole number that is 0 or more. */
    public static int readNonNegativeInt(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (input.hasNextInt()) {
                int value = input.nextInt();
                if (value >= 0) {
                    return value;
                }
                System.out.println("  Page count cannot be negative. Try again.");
            } else {
                System.out.println("  \"" + input.next() + "\" is not a whole number. Try again.");
            }
        }
    }

    /** Re-prompts until the user answers y/yes or n/no. Returns true for yes. */
    public static boolean readYesNo(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            String answer = input.next().trim().toLowerCase();
            if (answer.equals("y") || answer.equals("yes")) {
                return true;
            } else if (answer.equals("n") || answer.equals("no")) {
                return false;
            }
            System.out.println("  Please enter y or n.");
        }
    }

    /** Prints the final summary for one planning session. */
    public static void displaySummary(double credit, double costPerPage, int numberOfJobs,
                                      int totalPages, double averagePages,
                                      double projectedCharge, double creditDifference,
                                      String status) {
        System.out.println();
        System.out.println("----- Printing Plan Summary -----");
        System.out.printf("Weekly print credit:     $%.2f%n", credit);
        System.out.printf("Average cost per page:   $%.2f%n", costPerPage);
        System.out.printf("Print jobs:              %d%n", numberOfJobs);
        System.out.printf("Total pages:             %d%n", totalPages);
        System.out.printf("Average pages per job:   %.2f%n", averagePages);
        System.out.printf("Projected charge:        $%.2f%n", projectedCharge);
        System.out.println("Status:                  " + status);

        if (status.equals("CREDIT REMAINS")) {
            System.out.printf("Credit remaining:        $%.2f%n", creditDifference);
        } else if (status.equals("CREDIT EXCEEDED")) {
            System.out.printf("Amount over credit:      $%.2f%n", -creditDifference);
        } else {
            System.out.println("Credit remaining:        $0.00");
        }
        System.out.println("---------------------------------");
    }
}
