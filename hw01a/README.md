# HW01A: Campus Printing Credit Snapshot

Week 1 console program. Reads a weekly print credit, planned pages, and average cost per page, then prints the projected charge and the signed credit difference (positive means credit left, negative means over).

## Run

```
cd hw01a
javac -d out src/*.java
java -cp out CampusPrintCreditPlanner
```

## Methods

| Method | Does | Returns |
|---|---|---|
| `calculateProjectedCharge(int pages, double costPerPage)` | Total cost of the planned printing | `pages * costPerPage` |
| `calculateCreditDifference(double credit, double projectedCharge)` | Credit left after the charge | `credit - projectedCharge` |

`main()` just reads input, calls the two methods, and prints the results.

## Scope

Week 1 only: variables, Scanner input, arithmetic, methods, and formatted output. No if/else, loops, or input validation yet. Those come in HW01B.

## Testing

Filled-in evidence sheet: [docs/COMP240-HW01A-TEST-EVIDENCE.docx](docs/COMP240-HW01A-TEST-EVIDENCE.docx). Raw output from the test runs and both debugging episodes is in `tests/`.
