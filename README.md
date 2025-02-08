# Cinema Booking System
A Java basedd Cineam booking System that takes in a movie title. number of rows and number of seats rows as input and allows booking tickets for the movie as well as checking the s

## Entities
- A Movie theater can have many Showtimes and many Screens
- A Screen has a max row and seats per row 
- A Showtime is for a particular movie and shown at a particular Screen which determines the number of seats a showtime can have
- A Ticket is for particular Showtime and specified number of seats.
- A Seat has row and a

## Design Consideration
- Separation of concern, Single Responsibility Principle, Abstraction, Encapsultion and Immutability
- Flexiblity to extend to support multiple screens, showtimes and theaters in future- 
- Flexiblity to support new seat selection logic in future

## Assumptions
- Movie Title is a single word
- The number portion of a TicketID is exacty 4 digits so the max id that can be generated is GIC9999
- There is only one showtime a movie. Although this can be supported with changes to Theater Class.
- No two users are using the system as the same time (there will be conflict as they both might get a reservation number with same seats)

## Prerequisite

**Local Setup**
- Java JDK 21 or higher
- Maven 3.8+
- Lombok
- JUNIT5

For more see pom.xml

## Code base setup
git clone https://github.com/chelseaarjun/cinemabookingsystem.git

or uncompress

## Directory Structure
cinemabookingsystem/
├── src/
│   ├── main/
│   │   ├── java/
│           ├── com.gic.cinema.model/
│           ├── com.gic.cinema.service/
│           ├── com.gic.cinema.app/ 
│   └── test/
│       ├── java/
│           ├── com.gic.cinema.model/
│           ├── com.gic.cinema.service/
│           ├── com.gic.cinema.app/
├── pom.xml
└── README.md

main folder contains source code organized under models (Entities), service (business logic), app(UI interaction). The unit tests are under the test folder.

## Build
- Build Process
  - Compile your source code:
bash
javac -d classes src/*.java
Create a JAR file:
bash
jar cvfe myapp.jar Main -C classes .
Check dependencies (optional):
bash
jdeps -s myapp.jar

**Docker Setup**
- Docker Desktop or Docker Engine
- Git

## Local Installation & Running

1. Clone the repository
