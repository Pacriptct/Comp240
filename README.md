# COMP 240: Object-Oriented Programming (Java)

Coursework from COMP 240 at Wittenberg University. Each project is self-contained in its own folder and compiles on its own.

| Project | What it is | Concepts |
|---|---|---|
| [HW01A](hw01a/) | Campus Printing Credit Snapshot: estimates whether planned printing fits in a weekly print credit | Variables and data types, Scanner input, arithmetic, methods with parameters and return values, formatted output |
| [HW03](hw03/) | Campus Parking Permit Manager: tracks permits with zone filtering, active counts, and deactivation | Fixed arrays vs ArrayList, traversal, search by ID, filtering, counting, manager-owned state changes |
| [Project 1](project01/) | Campus Resource Reservation System: console app for booking study rooms, labs, and courts with capacity and time-slot conflict checks | Classes and objects, encapsulation, constructor validation, HAS-A association, ArrayList collections, searching, validate-before-mutation, input validation |

## Running a project

```
cd <project folder>
javac -d out src/*.java
java -cp out <MainClass>
```

Each project folder has its own README and test evidence.
