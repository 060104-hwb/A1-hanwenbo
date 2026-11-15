import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * Runs a scripted demonstration of the health appointment system.
 */
public final class AssignmentOne {
    private AssignmentOne() {
        // This utility class is not intended to be instantiated.
    }

    /**
     * Creates the objects, exercises appointment management, and prints results.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        printSection("PART 3: HEALTH PROFESSIONALS");

        HealthProfessional gpOne =
                new GeneralPractitioner(
                        "101",
                        "Dr Maya Chen",
                        EnumSet.of(
                                DayOfWeek.MONDAY,
                                DayOfWeek.WEDNESDAY,
                                DayOfWeek.FRIDAY),
                        "MBBS, FRACGP");
        HealthProfessional gpTwo =
                new GeneralPractitioner(
                        "102",
                        "Dr Noah Williams",
                        EnumSet.of(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY));
        HealthProfessional gpThree =
                new GeneralPractitioner(
                        "103",
                        "Dr Aisha Patel",
                        EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY),
                        "MBBS, FRACGP");
        HealthProfessional surgeon =
                new Surgeon(
                        "201",
                        "Dr Liam O'Connor",
                        EnumSet.of(DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY),
                        "FRACS",
                        "Orthopaedic surgery");
        HealthProfessional nurseOne =
                new Nurse(
                        "301",
                        "Nurse Emma Taylor",
                        EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.THURSDAY),
                        "NMW0001842");
        HealthProfessional nurseTwo =
                new Nurse(
                        "302",
                        "Nurse Sarah Nguyen",
                        EnumSet.of(DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY),
                        "NMW0002957");
        HealthProfessional dietitianOne =
                new Dietitian(
                        "401",
                        "Alex Morgan",
                        EnumSet.of(DayOfWeek.TUESDAY, DayOfWeek.FRIDAY),
                        "APD",
                        "Diabetes nutrition");
        HealthProfessional dietitianTwo =
                new Dietitian(
                        "402",
                        "Priya Singh",
                        EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY),
                        "APD",
                        "Sports nutrition");

        // A List<HealthProfessional> lets one loop handle every concrete type.
        List<HealthProfessional> professionals = new ArrayList<>();
        professionals.add(gpOne);
        professionals.add(gpTwo);
        professionals.add(gpThree);
        professionals.add(surgeon);
        professionals.add(nurseOne);
        professionals.add(nurseTwo);
        professionals.add(dietitianOne);
        professionals.add(dietitianTwo);

        int professionalNumber = 1;
        for (HealthProfessional professional : professionals) {
            System.out.println(professionalNumber + ". " + professional);
            professionalNumber++;
        }

        printSection("PARTS 4-5: APPOINTMENT MANAGEMENT");

        ClinicSchedule schedule =
                new ClinicSchedule(LocalTime.of(8, 0), LocalTime.of(16, 0), 30);
        AppointmentManager manager = new AppointmentManager(schedule);
        System.out.println("Clinic schedule: " + schedule.getDescription());

        Patient alice = new Patient("Alice Brown", "0400111001");
        Patient ben = new Patient("Ben Wilson", "0400111002");
        Patient carla = new Patient("Carla Rossi", "0400111003");
        Patient daniel = new Patient("Daniel Kim", "0400111004");

        printOperation(
                manager.addAppointment(
                        new Appointment(alice, gpOne, LocalTime.of(8, 0))));
        printOperation(
                manager.addAppointment(
                        new Appointment(ben, surgeon, LocalTime.of(9, 0))));
        printOperation(
                manager.addAppointment(
                        new Appointment(alice, nurseOne, LocalTime.of(9, 30))));
        printOperation(
                manager.addAppointment(
                        new Appointment(carla, dietitianOne, LocalTime.of(10, 0))));
        printOperation(
                manager.addAppointment(
                        new Appointment(ben, gpTwo, LocalTime.of(10, 30))));
        printOperation(
                manager.addAppointment(
                        new Appointment(daniel, nurseTwo, LocalTime.of(11, 0))));
        printOperation(
                manager.addAppointment(
                        new Appointment(carla, dietitianTwo, LocalTime.of(11, 30))));

        printAppointments("ALL APPOINTMENTS (SORTED BY TIME)", manager.getAllAppointments());

        printAppointments(
                "FILTERED BY PROFESSIONAL ID " + gpOne.getId(),
                manager.findByProfessionalId(gpOne.getId()));

        printAppointments(
                "FILTERED BY PATIENT PHONE " + alice.getMobileNumber(),
                manager.findByPatientPhone(alice.getMobileNumber()));

        printSection("ERROR CASES");

        printOperation(
                manager.addAppointment(
                        new Appointment(daniel, gpOne, LocalTime.of(8, 0))));
        printOperation(
                manager.addAppointment(
                        new Appointment(carla, gpThree, LocalTime.of(8, 15))));
        printOperation(
                manager.cancelByProfessionalAndTime(gpThree.getId(), LocalTime.of(14, 0)));
        printOperation(manager.cancelByPatientPhone(ben.getMobileNumber()));

        printSection("CANCELLATION AND UPDATED APPOINTMENT LIST");

        printOperation(
                manager.cancelByProfessionalAndTime(gpOne.getId(), LocalTime.of(8, 0)));
        printOperation(manager.cancelByPatientPhone(daniel.getMobileNumber()));

        printAppointments(
                "APPOINTMENTS AFTER CANCELLATIONS", manager.getAllAppointments());
        System.out.println("Current appointment count: " + manager.size());
    }

    private static void printSection(String title) {
        System.out.println();
        System.out.println("============================================================");
        System.out.println(title);
        System.out.println("============================================================");
    }

    private static void printOperation(String message) {
        System.out.println(message);
    }

    private static void printAppointments(String title, List<Appointment> appointments) {
        printSection(title);
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }

        int appointmentNumber = 1;
        for (Appointment appointment : appointments) {
            System.out.println(appointmentNumber + ". " + appointment);
            appointmentNumber++;
        }
    }
}
