# Cinema Booking System
A Java basedd Cineam booking System that takes in a movie title. number of rows and number of seats rows as input and allows booking tickets for the movie as well as checking the s

## Entities
- A Seat has row and a column number
- A Screen has a many Seats. It also has a max row and seats per row setting but each screen instance of a screen can hava a lower number of rows and seats. Screen also determines how the seats are numbered.
- A ScreenLayout determines the order seats are selected for a given Screen. 
- A Showtime is for a particular movie, ScreenLayout 
- A Ticket is for particular Showtime and specified number of seats.
- A Theater can have many Showtimes and many Screens

## Design Consideration
- Separation of concern, Single Responsibility Principle, Abstraction, Encapsultion and Immutability
- Flexiblity to support multiple screens, showtimes and theaters in future
- Flexiblity to support new seat selection logic in future
- Flexiblity to have screens with different max rows and seats per row settings in future
- Flexibility to have screens that follow a differnt seat identifier scheme

## Assumptions
- Movie Title is a single word
- The number portion of a TicketID is exacty 4 digits so the max id that can be generated is GIC9999
- There is only one showtime a movie. Although this can be supported with changes in future.
- There is only one Theater.
- No two users are using the system at the same time (there will be conflict as they both might get a reservation number with same seats and ticketID)

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
│           ├── com.gic.cinema.controller/
            |-- com.gic.cinema.view/
│           ├── com.gic.cinema.app/ 
│   └── test/
│       ├── java/
│           ├── com.gic.cinema.model/
│           ├── com.gic.cinema.controller/
            |-- com.gic.cinema.view/
│           ├── com.gic.cinema.app/
├── pom.xml
└── README.md

main folder contains source code organized under models (Entities), contorllers (business logic), view(UI). The unit tests are under the test folder.

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
