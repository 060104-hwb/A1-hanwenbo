# PROG2004 Assessment 1 - Health Appointment System

This project models a same-day health appointment system using Java. It was
built for Parts 1-6 of the PROG2004 Assessment 1 brief.

## Requirements

- Java 17 or later
- A terminal capable of running `java` and `javac`

## Run the demonstration

From the project directory:

```sh
mkdir -p out
javac -d out src/*.java
java -cp out AssignmentOne
```

The program prints all health professionals, adds and displays appointments,
filters by professional ID and patient phone number, demonstrates booking
errors, and cancels appointments by both supported input methods.

## Source files

- `AssignmentOne.java` - scripted end-to-end demonstration and console output
- `HealthProfessional.java` - abstract base class for shared professional data
- `GeneralPractitioner.java`, `Surgeon.java`, `Nurse.java`, `Dietitian.java` -
  concrete professional types
- `Patient.java` - validated patient name and mobile number
- `Appointment.java` - current-day appointment and time ordering
- `ClinicSchedule.java` - configurable list of valid clinic time slots
- `AppointmentManager.java` - add, filter, sort, and cancel operations

## Design choices

### Encapsulation

Object fields are private and validation happens in constructors. Collections
are copied into unmodifiable views where appropriate. External code can read
details through getters but cannot place an object into an invalid state.

### Abstraction

`HealthProfessional` is abstract because an unspecialised professional should
not be instantiated. It defines common data and requires each concrete type to
provide a role, role-specific details, and a service description.

### Inheritance

The four professional classes inherit common ID, name, working-day, validation,
and display behaviour from `HealthProfessional` without duplicating that code.

### Polymorphism

The demonstration stores every concrete professional in a
`List<HealthProfessional>`. Overridden methods cause each object to display its
own role-specific details and service while using one processing loop.
`Appointment` also implements `Comparable<Appointment>`, allowing
`Collections.sort` to order different appointments consistently.

### Appointment and scheduling design

An appointment is composed of a `Patient`, a `HealthProfessional`, and a
`LocalTime`. All bookings are tied to `LocalDate.now()`. `ClinicSchedule`
centralises valid slot generation, so changing the clinic hours or slot length
does not require changes to unrelated booking code.

The manager rejects:

- a time outside the configured slots;
- a second booking for the same professional at the same time;
- cancellation of a non-existent appointment;
- phone-based cancellation when the number matches multiple bookings, because
  the intended appointment would be ambiguous.

## Submission reminder

Before submitting, rename the project and ZIP using your MySCU username:

```text
A1-username/
A1-username.zip
```

Attach your own WakaTime report, add your GitHub and video links, and follow
the assessment instructions for any required declarations.
