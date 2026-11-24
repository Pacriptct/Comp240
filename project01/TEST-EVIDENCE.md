# Project 1 Test & Evidence: Campus Resource Reservation System

All tests below were run on this project's code (not the demo app). Java 21.

## How to rerun

```
cd project01
javac -d out src/*.java tests/ReservationSystemTests.java
java -cp out ReservationSystemTests
java -cp out CampusReservationApp < tests/console-test-input.txt
```

## Part 1: Console workflow tests (menu)

Input file: `tests/console-test-input.txt`. Full output: `tests/console-test-output.txt`.

| ID | What's being tested | Input | Expected | Result |
|---|---|---|---|---|
| C01 | Display all resources | 1 | All 5 seeded resources listed | PASS |
| C02 | Search by exact ID (lowercase input) | 2, r101 | R101 Study Room A found | PASS |
| C03 | Search by ID that doesn't exist | 2, Z999 | Not-found message | PASS |
| C04 | Keyword search with matches | 3, study | 2 study rooms listed | PASS |
| C05 | Keyword search with no matches | 3, pool | No-match message | PASS |
| C06 | Create valid reservation | 4, RES1, R101, 2026-12-01, 10:00-11:00, party 3 | SUCCESS message | PASS |
| C07 | Conflict: same room/date/slot | 4, RES2, R101, 2026-12-01, 10:00-11:00, party 2 | REJECTED: already reserved by RES1 | PASS |
| C08 | Over capacity | 4, RES3, R101, party 9 (capacity 4) | REJECTED: holds up to 4 | PASS |
| C09 | Duplicate reservation ID | 4, RES1 again on L201 | REJECTED: ID already exists | PASS |
| C10 | Resource doesn't exist | 4, RES4, X000 | REJECTED: no resource found | PASS |
| C11 | Blank text + bad number input | 4, RES5, blank name, then 'abc', then -2, then 8 | Re-prompts until valid, then SUCCESS | PASS |
| C12 | Display reservations | 6 | RES1 and RES5 listed with resource name + location | PASS |
| C13 | Cancel reservation that doesn't exist | 5, RES9 | Not-found message | PASS |
| C14 | Cancel existing (lowercase input) | 5, res5 | Cancelled message, list now shows only RES1 | PASS |
| C15 | Invalid menu choice / non-number | 7, abc | Unsupported choice / valid integer message | PASS |
| C16 | Exit | 0 | Program ends | PASS |

## Part 2: Direct class tests (39 checks)

`tests/ReservationSystemTests.java` calls the classes directly to cover rules the menu can't reach, like constructor validation, null/duplicate resources, the HAS-A reference, and the defensive copy.

```
=== CampusResource ===
PASS  T01 valid resource stores ID in uppercase
PASS  T02 getters return stored values
PASS  T03 blank ID rejected
PASS  T04 null name rejected
PASS  T05 blank type rejected
PASS  T06 blank location rejected
PASS  T07 zero capacity rejected
PASS  T08 canAccommodate at capacity
PASS  T09 canAccommodate over capacity is false
PASS  T10 canAccommodate zero is false
=== Reservation ===
PASS  T11 reservation keeps HAS-A reference to the same resource object
PASS  T12 getters return stored values
PASS  T13 null resource rejected
PASS  T14 blank requester rejected
PASS  T15 nonpositive party size rejected
PASS  T16 same resource/date/slot conflicts
PASS  T17 different slot does not conflict
PASS  T18 different date does not conflict
PASS  T19 different resource does not conflict
PASS  T20 toString shows associated resource name
=== ReservationManager ===
PASS  T21 addResource accepts new resource
PASS  T22 addResource rejects null
PASS  T23 addResource rejects duplicate ID
PASS  T24 findResourceById is case-insensitive
PASS  T25 findResourceById missing returns null
PASS  T26 keyword search matches type
PASS  T27 keyword search no match is empty
PASS  T28 valid reservation succeeds
PASS  T29 duplicate reservation ID rejected
PASS  T30 missing resource rejected
PASS  T31 over capacity rejected
PASS  T32 conflicting reservation rejected
PASS  T33 rejected requests did not change the list
PASS  T34 same room, next slot succeeds
PASS  T35 defensive copy protects internal list
PASS  T36 cancel missing ID returns false
PASS  T37 cancel existing ID returns true
PASS  T38 cancelled reservation is gone
PASS  T39 slot can be rebooked after cancel

Passed: 39  Failed: 0
```

## Part 3: Full console transcript

```
================================================
      CAMPUS RESOURCE RESERVATION SYSTEM
================================================
------------------ MENU -------------------
1. Display all resources
2. Search resource by ID
3. Search resources by name/type
4. Create reservation
5. Cancel reservation
6. Display reservations
0. Exit
Choose an option: All resources (5):
R101   | Study Room A       | Study Room   | Thomas Library 1st Floor   | capacity 4
R102   | Study Room B       | Study Room   | Thomas Library 2nd Floor   | capacity 6
L201   | Computer Lab       | Lab          | Science Center 210         | capacity 24
C301   | Conference Room    | Meeting Room | Hollenbeck Hall 105        | capacity 12
G401   | Basketball Court   | Athletic     | HPER Center                | capacity 20

------------------ MENU -------------------
1. Display all resources
2. Search resource by ID
3. Search resources by name/type
4. Create reservation
5. Cancel reservation
6. Display reservations
0. Exit
Choose an option: Enter resource ID: Found: R101   | Study Room A       | Study Room   | Thomas Library 1st Floor   | capacity 4

------------------ MENU -------------------
1. Display all resources
2. Search resource by ID
3. Search resources by name/type
4. Create reservation
5. Cancel reservation
6. Display reservations
0. Exit
Choose an option: Enter resource ID: No resource found with ID Z999.

------------------ MENU -------------------
1. Display all resources
2. Search resource by ID
3. Search resources by name/type
4. Create reservation
5. Cancel reservation
6. Display reservations
0. Exit
Choose an option: Enter name, type, or location keyword: 2 match(es):
R101   | Study Room A       | Study Room   | Thomas Library 1st Floor   | capacity 4
R102   | Study Room B       | Study Room   | Thomas Library 2nd Floor   | capacity 6

------------------ MENU -------------------
1. Display all resources
2. Search resource by ID
3. Search resources by name/type
4. Create reservation
5. Cancel reservation
6. Display reservations
0. Exit
Choose an option: Enter name, type, or location keyword: No resources matched "pool".

------------------ MENU -------------------
1. Display all resources
2. Search resource by ID
3. Search resources by name/type
4. Create reservation
5. Cancel reservation
6. Display reservations
0. Exit
Choose an option: Reservation ID: Requester name: Resource ID: Date (YYYY-MM-DD): Time slot (ex: 10:00-11:00): Party size: SUCCESS: Reservation RES1 created for Dylan McLean at Study Room A on 2026-12-01 (10:00-11:00).

------------------ MENU -------------------
1. Display all resources
2. Search resource by ID
3. Search resources by name/type
4. Create reservation
5. Cancel reservation
6. Display reservations
0. Exit
Choose an option: Reservation ID: Requester name: Resource ID: Date (YYYY-MM-DD): Time slot (ex: 10:00-11:00): Party size: REJECTED: Study Room A is already reserved on 2026-12-01 (10:00-11:00) by reservation RES1.

------------------ MENU -------------------
1. Display all resources
2. Search resource by ID
3. Search resources by name/type
4. Create reservation
5. Cancel reservation
6. Display reservations
0. Exit
Choose an option: Reservation ID: Requester name: Resource ID: Date (YYYY-MM-DD): Time slot (ex: 10:00-11:00): Party size: REJECTED: Study Room A holds up to 4 people, but the party size is 9.

------------------ MENU -------------------
1. Display all resources
2. Search resource by ID
3. Search resources by name/type
4. Create reservation
5. Cancel reservation
6. Display reservations
0. Exit
Choose an option: Reservation ID: Requester name: Resource ID: Date (YYYY-MM-DD): Time slot (ex: 10:00-11:00): Party size: REJECTED: Reservation ID RES1 already exists.

------------------ MENU -------------------
1. Display all resources
2. Search resource by ID
3. Search resources by name/type
4. Create reservation
5. Cancel reservation
6. Display reservations
0. Exit
Choose an option: Reservation ID: Requester name: Resource ID: Date (YYYY-MM-DD): Time slot (ex: 10:00-11:00): Party size: REJECTED: No resource found with ID X000.

------------------ MENU -------------------
1. Display all resources
2. Search resource by ID
3. Search resources by name/type
4. Create reservation
5. Cancel reservation
6. Display reservations
0. Exit
Choose an option: Reservation ID: Requester name: This field cannot be blank.
Requester name: Resource ID: Date (YYYY-MM-DD): Time slot (ex: 10:00-11:00): Party size: Please enter a valid integer.
Party size: Please enter a number greater than 0.
Party size: SUCCESS: Reservation RES5 created for Sam Park at Conference Room on 2026-12-03 (13:00-14:00).

------------------ MENU -------------------
1. Display all resources
2. Search resource by ID
3. Search resources by name/type
4. Create reservation
5. Cancel reservation
6. Display reservations
0. Exit
Choose an option: All reservations (2):
RES1   | Dylan McLean   | Study Room A (R101) @ Thomas Library 1st Floor | 2026-12-01 10:00-11:00 | party of 3
RES5   | Sam Park       | Conference Room (C301) @ Hollenbeck Hall 105 | 2026-12-03 13:00-14:00 | party of 8

------------------ MENU -------------------
1. Display all resources
2. Search resource by ID
3. Search resources by name/type
4. Create reservation
5. Cancel reservation
6. Display reservations
0. Exit
Choose an option: Reservation ID to cancel: No reservation found with ID RES9.

------------------ MENU -------------------
1. Display all resources
2. Search resource by ID
3. Search resources by name/type
4. Create reservation
5. Cancel reservation
6. Display reservations
0. Exit
Choose an option: Reservation ID to cancel: Reservation RES5 was cancelled.

------------------ MENU -------------------
1. Display all resources
2. Search resource by ID
3. Search resources by name/type
4. Create reservation
5. Cancel reservation
6. Display reservations
0. Exit
Choose an option: All reservations (1):
RES1   | Dylan McLean   | Study Room A (R101) @ Thomas Library 1st Floor | 2026-12-01 10:00-11:00 | party of 3

------------------ MENU -------------------
1. Display all resources
2. Search resource by ID
3. Search resources by name/type
4. Create reservation
5. Cancel reservation
6. Display reservations
0. Exit
Choose an option: Unsupported menu choice.

------------------ MENU -------------------
1. Display all resources
2. Search resource by ID
3. Search resources by name/type
4. Create reservation
5. Cancel reservation
6. Display reservations
0. Exit
Choose an option: Please enter a valid integer.
Choose an option: 
Reservation system ended.
```
