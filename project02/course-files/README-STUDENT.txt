COMP 240 — PROJECT 2 BASELINE v3.2
Smart Transit Fare & Pass Processing Engine
TEAM / GROUP ASSIGNMENT
Approximate baseline completion: 50%

IMPORTANT BASELINE GUARANTEE
----------------------------
The starter code is intentionally written so that it COMPILES AND RUNS before
any TODO is completed. Some TODO locations therefore contain temporary fallback
behavior. A successful baseline run does NOT mean the project requirements are
complete.

WHY THIS VERSION IS ~50% COMPLETE
---------------------------------
Already supplied:
- project/class structure and comments;
- private shared state, constructors, getters, and validation foundation;
- ArrayList collections and search/registration infrastructure;
- TripRecord HAS-A relationship;
- console menu and safe input helpers;
- seed data and display/search operations.

Team-owned work is concentrated on Week 6–7 OOP concepts plus integration:
TODO 1  Convert TransitPass to an abstract superclass and make calculateFare
        an abstract contract AFTER concrete subclasses are ready.
TODO 2  StudentPass: constructor-chaining explanation, implement
        DiscountEligible, and override calculateFare.
TODO 3  EmployeePass: implement DiscountEligible and override calculateFare.
TODO 4  VisitorPass: explicitly override the inherited fare operation.
TODO 5  FareManager.processRide: polymorphic dispatch through TransitPass.
TODO 6  Deactivate a pass through object behavior.
TODO 7  Aggregate total collected fare.
TODO 8  Filter TripRecord objects by pass ID.
TODO 9  Complete the process-ride console workflow.
TODO 10 Complete the filtered-report console workflow.

WEEK 6–7 CONCEPT CHECKPOINTS
----------------------------
Inheritance:
- StudentPass, EmployeePass, and VisitorPass are specialized TransitPass types.
- Do not duplicate passId, holderName, or active in subclasses.

Method overriding:
- Each concrete pass type ultimately supplies calculateFare(double).
- Use @Override so the compiler verifies your intent.

Interface:
- StudentPass and EmployeePass should formally implement DiscountEligible.
- Their fare logic should use discountRate().

Polymorphism:
- FareManager stores ArrayList<TransitPass>.
- processRide must call pass.calculateFare(baseFare) without instanceof or a
  subtype switch. The runtime object chooses the override.

Abstract class:
- Do TODO 1 only after TODOs 2–4 compile. This staged order keeps the starter
  runnable and teaches why an abstract contract is introduced.

TEAM WORKFLOW SUGGESTION
------------------------
Assign primary ownership but review/merge together:
Member A: hierarchy + abstraction (TODOs 1–4)
Member B: FareManager integration (TODOs 5–8)
Member C: UI/reporting + test evidence (TODOs 9–10 and tests)
Every member must understand the complete design and be able to explain the
inheritance hierarchy, interface, overriding, and dynamic dispatch.

RECOMMENDED BUILD ORDER
-----------------------
1. Compile/run untouched baseline.
2. Complete StudentPass, EmployeePass, VisitorPass.
3. Compile/test each fare rule.
4. Convert TransitPass to abstract and compile again.
5. Complete FareManager.processRide and manager TODOs.
6. Complete UI TODOs.
7. Execute the complete Project 2 test/evidence matrix.

BASELINE COMPILE/RUN
--------------------
javac *.java
java TransitFareApp
Choose 1 to display seeded passes, 2 to search, or 0 to exit.


HINT STYLE
----------
TODO comments identify the required Java construct and include a small pattern/example to clarify the expected approach without supplying the completed implementation.


TEAM EXTENSION: TWO NEW PASS CATEGORIES FROM SCRATCH
----------------------------------------------------
Your team must create TWO additional Java classes from scratch:

1. SeniorPass.java
   - SeniorPass IS-A TransitPass.
   - Senior riders pay 40% of the base fare (60% discount).
   - It participates in the same discount contract used by other discounted passes.
   - Expected example: base fare $10.00 -> charged fare $4.00.

2. CommunityPass.java
   - CommunityPass IS-A TransitPass.
   - Community riders pay 70% of the base fare (30% discount).
   - It participates in the same discount contract used by other discounted passes.
   - Expected example: base fare $10.00 -> charged fare $7.00.

No starter Java files are supplied for these two categories. Use StudentPass.java,
EmployeePass.java, VisitorPass.java, TransitPass.java, DiscountEligible.java, and
TransitFareApp.java as structural/code-pattern references. Add at least one seeded
object of each new category so both are exercised through the common TransitPass
collection and normal ride-processing workflow.

Read COMP240-PROJ02-NEW-PASS-CATEGORIES-STEP-BY-STEP-v1.0.docx before implementing.
