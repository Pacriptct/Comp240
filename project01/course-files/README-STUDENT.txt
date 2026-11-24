COMP 240 — PROJECT 1
CAMPUS RESOURCE RESERVATION SYSTEM
STUDENT BASELINE CODE v2.0 — 30/70 SCAFFOLD

PURPOSE
-------
This starter package is intentionally incomplete.

Approximately 30% of the implementation responsibility has been supplied.
Approximately 70% remains for you to design, implement, test, and explain.

The 30/70 ratio applies ONLY to this Student Project 1 baseline code.
The separate Media Equipment Checkout System instructor demo is a complete,
runnable reference application.

FILES
-----
1. CampusResource.java
2. Reservation.java
3. ReservationManager.java
4. CampusReservationApp.java
5. README-STUDENT.txt
6. DEMO-SLIDE-REFERENCE-MAP.txt

HOW TO USE THE DEMO REFERENCES
------------------------------
Comments in the Java files point to analogous slides in:

COMP240-PROJ01-INSTRUCTOR-LECTURE-v4.3-FINAL-QC.pptx

The demo slides show complete Java/OOP patterns using a DIFFERENT application:
the Media Equipment Checkout System.

Use those slides to answer questions such as:
- Which class should own this state?
- Where should this validation occur?
- How does an object association work?
- How does a manager search an ArrayList?
- What does "validate before mutation" mean?
- How should the UI class delegate work?

DO NOT simply rename EquipmentItem to CampusResource, Checkout to Reservation,
or copy equipment-domain rules into Project 1. Your Campus Resource Reservation
System has its own requirements and business rules.

IMPORTANT BASELINE NOTE
-----------------------
The supplied code is designed to compile, but it is NOT a completed solution.
Several methods contain placeholder return values or TODO output. Compilation
success is therefore not evidence that Project 1 requirements are satisfied.

EXPECTED STUDENT OWNERSHIP
--------------------------
You remain responsible for the substantive implementation, including:
- constructor validation;
- remaining accessor methods;
- capacity/business-rule behavior;
- reservation conflict behavior;
- exact-ID and useful additional search;
- duplicate checks;
- reservation creation using ordered validation;
- cancellation;
- major application workflows;
- input validation;
- Test & Evidence documentation.

Compile:
    javac CampusResource.java Reservation.java ReservationManager.java CampusReservationApp.java

Run:
    java CampusReservationApp

Your final submission must satisfy the Project 1 specification and Test & Evidence
requirements, not merely remove TODO comments.
