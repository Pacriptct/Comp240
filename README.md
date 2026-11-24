# COMP 240: Object-Oriented Programming (Java)

Coursework from COMP 240 at Wittenberg University. Each project is self-contained in its own folder and compiles on its own.

| Project | What it is | Concepts |
|---|---|---|
| [HW01A](hw01a/) | Campus Printing Credit Snapshot: estimates whether planned printing fits in a weekly print credit | Variables and data types, Scanner input, arithmetic, methods with parameters and return values, formatted output |
| [HW01B](hw01b/) | Campus Printing Credit Planner: extends HW01A to handle multiple print jobs, credit status, input validation, and repeated sessions | if/else decisions, for and while loops, accumulators, input validation and re-prompting, method decomposition, floating-point boundary handling |
| [HW02](hw02/) | Campus Package Locker Tracker: assign packages to campus lockers and pick them up, with duplicate and occupied-locker checks | Classes and constructors, encapsulation, HAS-A association, ArrayList collections, manager-owned rules, UML |
| [HW03](hw03/) | Campus Parking Permit Manager: tracks permits with zone filtering, active counts, and deactivation | Fixed arrays vs ArrayList, traversal, search by ID, filtering, counting, manager-owned state changes |
| [Project 1](project01/) | Campus Resource Reservation System: console app for booking study rooms, labs, and courts with capacity and time-slot conflict checks | Classes and objects, encapsulation, constructor validation, HAS-A association, ArrayList collections, searching, validate-before-mutation, input validation |
| [Project 2](project02/) | Smart Transit Fare & Pass Processing Engine: processes rides for five pass types, each with its own fare rule, through one polymorphic workflow | Inheritance, abstract classes, interfaces, method overriding, polymorphism / dynamic dispatch, constructor chaining, UML |

## Running a project

```
cd <project folder>
javac -d out src/*.java
java -cp out <MainClass>
```

Each project folder has its own README and test evidence.
