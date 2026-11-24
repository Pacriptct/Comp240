# COMP 240 Week 2 Integrated Lab: Evidence

**Dylan McLean** | Campus Commute Cost Estimator | Final source: [src/CampusCommute.java](src/CampusCommute.java)

## Week 1 recovery gate

Known test: 10 trips, 4.5 miles per trip, 30 MPG, $3.25 per gallon.

| Value | Expected | Week 1 version | Final version (after method extraction) |
|---|---|---|---|
| Weekly miles | 45.0 | 45.0 | 45.0 |
| Gallons used | 1.5 | 1.5 | 1.5 |
| Fuel cost | $4.88 (4.875) | $4.88 | $4.88 |

Trace: the 4.5 typed in goes Scanner `nextDouble()` -> `milesPerTrip` -> `calculateWeeklyMiles(10, 4.5)` returns 45.0 -> `calculateFuelCost(45.0, 30, 3.25)` returns 1.5 * 3.25 = 4.875 -> printed as $4.88.

The known test gives the same result after the methods were extracted, so the refactor didn't change any behavior.

## Decision layer

The two Boolean expressions are `weeklyFuelCost < LOW_LIMIT` and `weeklyFuelCost <= MODERATE_LIMIT`, with the named constants `LOW_LIMIT = 10.0` and `MODERATE_LIMIT = 25.0`.

Exactly $10.00 is MODERATE because the first test uses `<`, and 10.0 < 10.0 is false, so it falls through to the second test. Exactly $25.00 is MODERATE because the second test uses `<=`, and 25.0 <= 25.0 is true. Only values above $25.00 make it to the else branch and get HIGH.

Prediction for the known test before running: $4.88 is under $10.00, so LOW. The output matched.

## T1–T6 results

Full console output: [tests/test-runs.txt](tests/test-runs.txt)

| Test | Inputs | Expected | Actual | Pass? |
|---|---|---|---|---|
| T1 Normal | 10, 4.5, 30, 3.25 | 45.0 mi, 1.5 gal, $4.88, LOW | 45.0, 1.5, $4.88, LOW | Pass |
| T2 LOW→MODERATE | 10, 6, 30, 5.00 (exactly $10.00) and 10, 6, 30, 4.99 | $10.00 MODERATE, $9.98 LOW | $10.00 MODERATE; $9.98 LOW | Pass |
| T3 MODERATE→HIGH | 10, 15, 30, 5.00 (exactly $25.00) and 10, 15, 30, 5.01 | $25.00 MODERATE, above $25 HIGH | $25.00 MODERATE; $25.05 HIGH | Pass |
| T4 Zero usage | 0, 4.5, 30, 3.25 | $0.00, LOW | 0.0 mi, 0.0 gal, $0.00, LOW | Pass |
| T5 Invalid MPG | MPG 0, then -5, then 30 | Re-prompts until MPG > 0 | "MPG must be greater than 0." printed twice, then continued with 30 and gave $4.88 | Pass |
| T6 Report choice | Report mode 1, 2, then 7 | Summary / cost only / default | 1 printed miles, cost, and status; 2 printed only the cost; 7 printed "Unknown report mode." No fall-through between cases | Pass |

## Defect encountered

| | |
|---|---|
| What failed | In the Week 1 recovery version, entering 0 for MPG (10, 4.5, 0, 3.25) printed "Estimated gallons: Infinity" and "Estimated fuel cost: $Infinity" instead of a real number. |
| Evidence / localizing | Miles was still correct (45.0), so the problem was after that line. Gallons is `weeklyMiles / milesPerGallon`, and dividing a double by 0 in Java gives Infinity instead of crashing. That Infinity then carried into the cost. |
| Correction | Added the while loop `while (milesPerGallon <= 0)` that prints "MPG must be greater than 0." and asks again, so the division only runs with a positive MPG. |
| Retest | T5: 0 and -5 were both rejected and re-prompted, then 30 gave 1.5 gallons and $4.88. Before/after output is in [tests/week1-recovery-and-defect.txt](tests/week1-recovery-and-defect.txt) and [tests/test-runs.txt](tests/test-runs.txt). |

## Method explanation: `classifyCost`

- **Responsibility:** decides which cost category a weekly fuel cost falls in.
- **Parameter:** `double weeklyFuelCost`, the cost that was already calculated.
- **Return value:** a `String`, either "LOW", "MODERATE", or "HIGH".

It doesn't read input or print anything. It just takes a number and returns a label, so `main()` can print it, and it could be reused anywhere a cost needs classifying.

## Exit reflection

| Prompt | Response |
|---|---|
| What Week 1 capability had to work before you could add Week 2 control flow? | The Scanner input and the miles, gallons, and cost calculations had to be correct first, since the classification and the report both depend on weeklyFuelCost being right. |
| How did if/else make the program behave differently based on data? | The if/else chain checks the cost against the two limits, so the same program prints LOW, MODERATE, or HIGH depending on what numbers get entered. |
| How did the validation loop make the program more robust? | Before the loop, 0 MPG made the program print Infinity for gallons and cost, but now it keeps re-prompting until the MPG is greater than 0, so bad input never reaches the division. |
| Which method has the clearest single responsibility, and why? | classifyCost has the clearest single job because it only takes a cost and returns a label, without doing any input, output, or other math. |
| What test gave you the strongest evidence that your program was working correctly? | The boundary tests T2 and T3 were the strongest, because exactly $10.00 and $25.00 both came out MODERATE and $25.05 came out HIGH, which shows the < and <= are in the right places. |
