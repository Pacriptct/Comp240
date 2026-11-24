# HW01B: Campus Printing Credit Planner

Week 2 extension of [HW01A](../hw01a/). The one-shot snapshot became a planner that handles multiple print jobs, classifies the credit status, validates input, and supports repeated sessions.

## Run

```
cd hw01b
javac -d out src/*.java
java -cp out CampusPrintCreditPlanner
```

## What changed from HW01A

| HW01A | HW01B |
|---|---|
| One planned pages value | A for loop reads pages for each print job and keeps a running total |
| Numbers only | `classifyCreditStatus` picks CREDIT REMAINS, CREDIT EXACTLY USED, or CREDIT EXCEEDED |
| Bad input crashed it | `readNonNegativeDouble`, `readPositiveInt`, and `readNonNegativeInt` re-prompt until the value is valid |
| Runs once | A while loop with `readYesNo` runs as many sessions as you want |
| Output in main() | `displaySummary` prints the summary, including the remaining or over amount |

`calculateProjectedCharge` and `calculateCreditDifference` are unchanged from HW01A.

## Exact-credit boundary

Doubles can't store some decimals exactly. `3 * 0.10` is `0.30000000000000004`, so a plan that uses exactly $0.30 of a $0.30 credit came out as a tiny negative difference and showed CREDIT EXCEEDED. `classifyCreditStatus` rounds the difference to whole cents before comparing, which fixes it.

## Testing

Filled-in evidence sheet with the final design note: [docs/COMP240-HW01B-TEST-EVIDENCE.docx](docs/COMP240-HW01B-TEST-EVIDENCE.docx). Raw output from all tests and both debugging episodes is in `tests/`.
