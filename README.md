# COMP 240: Object-Oriented Programming in Java

Coursework from COMP 240 at Wittenberg University (Fall 2026). The assignments start with basic console programs and build up to a Swing banking app with saved data, undo, and automated tests. Each assignment is in its own folder, compiles on its own, and includes its own README and test evidence.

**Author:** Dylan McLean ([@Pacriptct](https://github.com/Pacriptct))

## Highlights

**[Project 3: BankApp](project03/)** *(group project)*
A Swing banking app with customer signup, checking and savings accounts, deposits, withdrawals, transfers, transaction history, and undo. It saves its data as CSV or Java serialization, writes to a temp file first so a crash can't corrupt the save, and refuses to load a damaged file instead of overwriting it. Money is stored as `BigDecimal`, and 22 automated tests cover the rules and storage.

**[Project 2: Smart Transit Fare Engine](project02/)** *(group project)*
Processes rides for five pass types, each with its own fare rule, through one polymorphic workflow. It uses an abstract base class, an interface, and method overriding, and the manager never checks which type of pass it has. I wrote two of the pass types from scratch.

**[Project 1: Campus Resource Reservation System](project01/)**
Books study rooms, labs, and courts. It checks capacity and time-slot conflicts and validates every request before changing anything. It has 39 direct class tests plus a scripted run through the console menu.

## All assignments

| Assignment | What it does | Main concepts |
|---|---|---|
| [Lab W02](lab-w02/) | Campus commute fuel cost estimator with a LOW / MODERATE / HIGH rating | if/else chains, named constants, `switch`, while-loop validation, methods |
| [HW01A](hw01a/) | Checks whether planned printing fits in a weekly print credit | Variables and types, `Scanner` input, arithmetic, methods, formatted output |
| [HW01B](hw01b/) | Extends HW01A to handle multiple print jobs, repeat sessions, and bad input | Loops, accumulators, input validation, decomposition, floating-point boundaries |
| [HW02](hw02/) | Campus package locker tracker: assign packages and pick them up | Classes, constructors, encapsulation, HAS-A, `ArrayList`, UML |
| [HW03](hw03/) | Campus parking permit manager with zone filtering and deactivation | Arrays vs `ArrayList`, search, filter, count, manager-owned rules |
| [Project 1](project01/) | Campus resource reservation system | Object collaboration, validate before mutating, searching, UML |
| [Project 2](project02/) *(group)* | Transit fare engine for five pass types | Inheritance, abstract classes, interfaces, overriding, polymorphism |
| [Project 3](project03/) *(group)* | Swing banking app with saved state and undo | GUI/controller split, file I/O, exceptions, collections, records, enums, tests |

## Skills

### Object-oriented design
- **Encapsulation:** private fields, read-only getters, and constructors that reject bad values, so an object can't end up in an invalid state (HW02, Project 1, Project 3)
- **Composition (HAS-A):** objects that hold references to other objects instead of copying their data, like a reservation holding its room or a package record holding its locker (HW02, Project 1, Project 2)
- **Inheritance and abstract classes:** shared state and behavior in a parent class, with subclasses that only add what's different (Project 2: `TransitPass`, Project 3: `BankAccount`)
- **Interfaces:** contracts like `DiscountEligible`, `AccountOperations`, and `Storage` that let different classes be used the same way (Project 2, Project 3)
- **Polymorphism:** one loop or method call that works on every subtype, with no `instanceof` checks (Project 2)
- **Separation of concerns:** the UI only reads input and prints, and a manager or controller class owns the data and rules (every multi-class assignment)

### Java language and libraries
- Collections: `ArrayList`, `LinkedList`, `Queue`, `Deque` as a stack (for undo), fixed arrays, and sorting with `Comparator`
- `BigDecimal` for money, `LocalDateTime` for timestamps, records, and enums (Project 3)
- Exceptions: throwing them for invalid state and catching them so bad input doesn't crash the program (Project 2, Project 3)
- File I/O: CSV reading and writing, Java object serialization, and atomic saves through a temp file (Project 3)
- Swing GUI wired to a controller (Project 3)
- Console input with `Scanner`, re-prompting on invalid input (all console assignments)

### Testing and debugging
- Test plans that cover normal, boundary, invalid, and regression cases, recorded against the real program output
- Automated test classes in plain Java with no libraries (Project 1: 39 checks, Project 3: 22 tests)
- Documented defects with the observed problem, how I narrowed it down, the fix, and the retest. Examples include a floating-point equality bug where `3 * 0.10` came out as `0.30000000000000004` (HW01B) and a negative fare that crashed the program (Project 2)

### Tools and workflow
- Compiling and running with `javac` and `java` from the command line
- Git with one branch per assignment, merged into `main`
- UML class diagrams that match the code (HW02, Project 1, Project 2)

## Skills by assignment

| Skill | Lab W02 | HW01A | HW01B | HW02 | HW03 | Proj 1 | Proj 2 | Proj 3 |
|---|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|
| Methods and decomposition | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ |
| Decisions and loops | ✓ | | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ |
| Input validation | ✓ | | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ |
| Classes and encapsulation | | | | ✓ | ✓ | ✓ | ✓ | ✓ |
| HAS-A composition | | | | ✓ | | ✓ | ✓ | ✓ |
| ArrayList / collections | | | | ✓ | ✓ | ✓ | ✓ | ✓ |
| Inheritance and abstract classes | | | | | | | ✓ | ✓ |
| Interfaces and polymorphism | | | | | | | ✓ | ✓ |
| Exceptions | | | | ✓ | ✓ | ✓ | ✓ | ✓ |
| File I/O and persistence | | | | | | | | ✓ |
| GUI (Swing) | | | | | | | | ✓ |
| UML | | | | ✓ | | ✓ | ✓ | |
| Automated tests | | | | | | ✓ | | ✓ |
| Documented test evidence | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ | ✓ |

## Running an assignment

Requires JDK 17 or newer.

```
cd <assignment folder>
javac -d out src/*.java
java -cp out <MainClass>
```

| Folder | Main class |
|---|---|
| `lab-w02` | `CampusCommute` |
| `hw01a`, `hw01b` | `CampusPrintCreditPlanner` |
| `hw02` | `LockerApp` |
| `hw03` | `ParkingPermitApp` |
| `project01` | `CampusReservationApp` |
| `project02` | `TransitFareApp` |
| `project03` | Uses a package, see [its README](project03/README.md) |

## About the code

All assignments started from instructor-provided starter code, usually a partial skeleton with TODOs to complete. Where it was included, the original course handout is in the assignment's `course-files/` or `docs/` folder. Projects 2 and 3 were group projects.
