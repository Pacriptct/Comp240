# HW03: Campus Parking Permit Manager

Arrays and collections of objects. Console app that tracks campus parking permits: display, search by ID, filter by zone, active/total counts, and deactivation.

## Run

```
cd hw03
javac -d out src/*.java
java -cp out ParkingPermitApp
```

## Why a fixed array for zones but an ArrayList for permits

The valid zones (A, B, C) are a set number that never changes while the program runs, so a fixed `String[]` is all that's needed to check if a zone is valid. Permits are different because new ones can be issued at any time and we don't know how many there will be, so `ArrayList<ParkingPermit>` is used since it can grow and has methods like `add` and `size` built in.

## What I implemented

| TODO | Where | What it does |
|---|---|---|
| 1 | `PermitManager.findByZone` | Builds a new list of permits in the given zone |
| 2 | `PermitManager.countActivePermits` | Counts permits that are still active |
| 3 | `PermitManager.getActivePermits` | Builds a new list of only active permits |
| 4 | `PermitManager.deactivatePermit` | Rejects missing or already-inactive permits, otherwise deactivates |
| 5 | `ParkingPermitApp.displayByZone` | Reads and validates a zone, prints the filtered list |
| 6 | `ParkingPermitApp.displaySummary` | Prints total, active, and inactive counts |
| 7 | `ParkingPermitApp.deactivatePermit` | Reads an ID, delegates to the manager, prints success or rejection |

I also added `countAllPermits()` to the manager so the app gets the total from the manager instead of counting itself.

## Testing

Filled-in evidence sheet: [docs/COMP240-HW03-TEST-EVIDENCE.docx](docs/COMP240-HW03-TEST-EVIDENCE.docx). The scripted input and full console output it's based on are in `tests/`.
