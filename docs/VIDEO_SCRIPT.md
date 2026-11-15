# Five-Minute Video Script

Use screen recording software and keep the video at or below five minutes.
Open the relevant source files as you speak.

## 0:00-0:25 - Introduction

"My appointment system models eight health professionals, patients,
current-day appointments, clinic time slots, and an appointment manager. I
will explain how the design applies the four pillars of object-oriented
programming."

## 0:25-1:10 - Abstraction

Open `HealthProfessional.java` and `AssignmentOne.java`.

"Abstraction separates what every health professional must provide from how a
specific professional provides it. `HealthProfessional` is abstract. It stores
the ID, name, and working days, then declares abstract methods for the role,
role-specific details, and service. In the main method I only need the shared
`HealthProfessional` type to ask each object to display itself."

## 1:10-2:00 - Encapsulation

Open `HealthProfessional.java`, `Patient.java`, and
`AppointmentManager.java`.

"Encapsulation protects valid object state. Fields are private and final.
Validation is completed in constructors, such as numeric-only professional IDs
and mobile numbers with 8 to 15 digits. Working days are copied into an
unmodifiable set, and the manager returns sorted copies rather than exposing its
internal appointment list. Callers can read data through controlled methods but
cannot bypass the rules."

## 2:00-2:55 - Inheritance

Open `HealthProfessional.java` and then `GeneralPractitioner.java`,
`Surgeon.java`, `Nurse.java`, and `Dietitian.java`.

"Inheritance removes duplication. Every concrete professional inherits common
ID, name, working-day, validation, equality, and display behaviour. Each
subclass adds only its own fields and overrides the role-specific methods.
Because the base class is abstract, a generic unspecialised professional
cannot be created accidentally."

## 2:55-3:45 - Polymorphism

Open the professional list in `AssignmentOne.java`.

"Polymorphism appears in the `List<HealthProfessional>` loop. The variable type
is the abstract superclass, but each object responds according to its concrete
class. The general practitioner, surgeon, nurse, and dietitian each supply
different role details and services through the same methods. `Appointment`
also implements `Comparable`, so `Collections.sort` can compare appointments
through a common contract."

## 3:45-4:35 - System demonstration

Run the program and show these sections:

1. all professionals;
2. appointments sorted by time;
3. filtering by professional ID and patient phone;
4. duplicate-booking and invalid-slot warnings;
5. successful cancellation and updated results.

"The appointment manager prevents double-booking and invalid times. Filters and
cancellations cover both required inputs. All output lists are sorted by time,
and success or warning messages make each operation's result explicit."

## 4:35-5:00 - Close

"Abstraction defines the contract, inheritance shares common behaviour,
encapsulation protects data, and polymorphism allows all professional types to
work through one interface. Together they produce a maintainable Java model for
the appointment scenario."
