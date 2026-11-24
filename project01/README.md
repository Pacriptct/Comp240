# COMP 240 Project 1: Campus Resource Reservation System

Console app for reserving campus resources (study rooms, labs, courts). Built from the course baseline code.

## Run

```
cd project01
javac -d out src/*.java
java -cp out CampusReservationApp
```

Testing evidence is in [TEST-EVIDENCE.md](TEST-EVIDENCE.md).

## Classes

| Class | Job |
|---|---|
| `CampusResource` | One resource. Validates its own fields and owns the capacity rule (`canAccommodate`). |
| `Reservation` | One reservation. HAS-A `CampusResource`. Owns the conflict rule (same resource + date + time slot). |
| `ReservationManager` | Owns both ArrayLists. Add/find/search, create with ordered validation, cancel. |
| `CampusReservationApp` | Menu/UI only. Reads input, calls the manager, prints results. Seeds 5 resources at startup. |

## Reservation rules (checked in this order, before anything is added)

1. Reservation ID can't already exist
2. Resource ID has to exist
3. Party size has to fit the resource's capacity
4. Can't conflict with an existing reservation (same resource, date, and time slot)

## UML

```mermaid
classDiagram
    class CampusResource {
        -String resourceId
        -String name
        -String type
        -String location
        -int capacity
        +getResourceId() String
        +getName() String
        +getType() String
        +getLocation() String
        +getCapacity() int
        +canAccommodate(int partySize) boolean
        +toString() String
    }
    class Reservation {
        -String reservationId
        -String requester
        -CampusResource resource
        -String date
        -String timeSlot
        -int partySize
        +getReservationId() String
        +getRequester() String
        +getResource() CampusResource
        +getDate() String
        +getTimeSlot() String
        +getPartySize() int
        +conflictsWith(CampusResource, String, String) boolean
        +toString() String
    }
    class ReservationManager {
        -ArrayList~CampusResource~ resources
        -ArrayList~Reservation~ reservations
        +addResource(CampusResource) boolean
        +findResourceById(String) CampusResource
        +searchResources(String) ArrayList~CampusResource~
        +findReservationById(String) Reservation
        +createReservation(String, String, String, String, String, int) String
        +cancelReservation(String) boolean
        +getResources() ArrayList~CampusResource~
        +getReservations() ArrayList~Reservation~
    }
    class CampusReservationApp {
        -Scanner INPUT$
        -ReservationManager MANAGER$
        +main(String[]) void
    }
    Reservation --> "1" CampusResource : has-a
    ReservationManager "1" o-- "*" CampusResource : owns
    ReservationManager "1" o-- "*" Reservation : owns
    CampusReservationApp --> ReservationManager : uses
```

`course-files/` has the original baseline README and slide reference map.
