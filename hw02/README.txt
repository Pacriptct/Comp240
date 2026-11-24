COMP 240 - HW02: Campus Package Locker Tracker
Dylan McLean

PackageLocker
Represents one campus package locker. It stores the locker ID, location, size category, and whether it is occupied, and it changes its own state through markOccupied() and markAvailable().

PackageRecord
Represents one active package assignment with a tracking ID and recipient. It HAS-A reference to the PackageLocker object holding the package instead of copying the locker's fields.

LockerManager
Owns the ArrayLists of lockers and active package records. It handles searching by locker ID and tracking ID, and enforces the assign and pickup rules (no duplicate tracking IDs, no missing or occupied lockers), validating before changing anything.

LockerApp
The console menu. It reads input, hands the work to LockerManager, and prints the results, and it re-prompts on invalid menu numbers or blank text.

Compile:  javac src/*.java -d out
Run:      java -cp out LockerApp

UML diagram: docs/HW02-UML.png
Test evidence: docs/COMP240-HW02-TEST-EVIDENCE.docx
