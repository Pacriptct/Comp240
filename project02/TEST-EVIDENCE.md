# Project 2 Test & Evidence: Smart Transit Fare & Pass Processing Engine

Every test was run on this project's final code through the normal console menu. The full console output for every test is in [tests/test-runs.txt](tests/test-runs.txt).

## New pass category tests (N1–N8 from the step-by-step guide)

| Test | Input | Expected | Actual | Pass? |
|---|---|---|---|---|
| N1 | SeniorPass S400, base $10.00 | $4.00 | "Ride T1 processed for Sam Senior (SeniorPass). Base fare $10.00, charged fare $4.00." | Pass |
| N2 | SeniorPass S400, base $5.00 | $2.00 | Charged fare $2.00 | Pass |
| N3 | CommunityPass C500, base $10.00 | $7.00 | "Ride T3 processed for Cora Community (CommunityPass). Base fare $10.00, charged fare $7.00." | Pass |
| N4 | CommunityPass C500, base $5.00 | $3.50 | Charged fare $3.50 | Pass |
| N5 | Menu option 1 | Both new passes listed in the pass collection | `S400 \| Sam Senior \| SeniorPass \| ACTIVE` and `C500 \| Cora Community \| CommunityPass \| ACTIVE` listed with the other three | Pass |
| N6 | Rides on all 5 pass types through option 3, base $10.00 each | Each type charges its own fare | Student $5.00, Employee $2.50, Visitor $10.00, Senior $4.00, Community $7.00, all through the same `processRide` call | Pass |
| N7 | Base $0.00 on S400 and C500 | $0.00 | Both charged $0.00 and recorded | Pass |
| N8 | Base -5 on S400, -1.50 on C500 | Validation response | Both "Rejected: Base fare cannot be negative." No trips recorded, total stays $0.00 | Pass |

## Core feature tests

| Test | What | Input | Actual | Pass? |
|---|---|---|---|---|
| P1 | Display passes | Option 1 | All 5 seeded passes with their class name and ACTIVE | Pass |
| P2 | Search | `s400` (lowercase), then `X999` | Found S400 SeniorPass; "Pass not found." | Pass |
| P3 | Trips + total | 7 rides from N1–N6, then options 4 and 6 | All 7 trips listed; total $34.00 (4 + 2 + 7 + 3.50 + 5 + 2.50 + 10) | Pass |
| P4 | Ride rejections and input recovery | Duplicate trip T1, missing pass Z999, blank trip ID, `abc` as fare | "trip ID is blank or has already been used", "no pass found with ID Z999", "Value cannot be blank." and "Enter a numeric value." then re-prompted. Only the 2 valid trips recorded | Pass |
| P5 | Deactivate | Deactivate S400, ride on S400, deactivate X999 | "Pass deactivated.", S400 shows INACTIVE, ride rejected "pass S400 is inactive", "Pass not found." | Pass |
| P6 | Summary + filtered report | 3 rides (C500 $10, S400 $10, C500 $5), options 6 and 7 | Total $14.50; `c500` shows only T1 and T3; E200 shows "No trips found for pass E200." | Pass |
| P7 | Bad menu input + exit | `abc`, `9`, `0` | "Enter a whole number.", "Unknown menu choice.", "Transit Fare Engine closed." | Pass |

## Design checks

| Check | Evidence |
|---|---|
| `TransitPass` is abstract | Compiling `new TransitPass("X1", "Nobody")` fails with "TransitPass is abstract; cannot be instantiated" ([tests/abstract-check.txt](tests/abstract-check.txt)) |
| No type checks in the manager | `FareManager` has no `instanceof` and no switch on pass type. `processRide` only calls `pass.calculateFare(baseFare)` |
| Shared state not duplicated | Subclasses declare no fields; `passId`, `holderName`, `active` live only in `TransitPass` |

## Defect / retest

| What failed | Why | Fix | Retest |
|---|---|---|---|
| N8: a base fare of -5 on S400 crashed the whole program with `IllegalArgumentException: Base fare cannot be negative.` (stack trace: `validateBaseFare` <- `SeniorPass.calculateFare` <- `FareManager.processRide` <- `main`) | The shared validation threw correctly, but nothing caught it, so it went all the way up to `main` | Wrapped `pass.calculateFare(baseFare)` in `processRide` with a try/catch that returns "Rejected: " plus the validation message. The rule stays in `validateBaseFare`, and no `TripRecord` is created | Same input now prints "Rejected: Base fare cannot be negative.", option 4 shows "No successful trips recorded.", and the program keeps running. N8 passes for both new pass types |

Before/after output: [tests/defect-retest.txt](tests/defect-retest.txt)
