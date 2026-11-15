import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents one current-day appointment between a patient and a professional.
 *
 * <p>Appointments are ordered primarily by time. Patient mobile number and
 * professional ID are tie-breakers so display order is deterministic.</p>
 */
public final class Appointment implements Comparable<Appointment> {
    private final Patient patient;
    private final HealthProfessional professional;
    private final LocalTime time;
    private final LocalDate appointmentDate;

    /**
     * Creates an appointment for the current day.
     *
     * @param patient patient attending the appointment
     * @param professional chosen health professional
     * @param time appointment start time
     */
    public Appointment(Patient patient, HealthProfessional professional, LocalTime time) {
        if (patient == null) {
            throw new IllegalArgumentException("An appointment requires a patient.");
        }
        if (professional == null) {
            throw new IllegalArgumentException("An appointment requires a health professional.");
        }
        if (time == null) {
            throw new IllegalArgumentException("An appointment requires a time.");
        }

        this.patient = patient;
        this.professional = professional;
        this.time = time;
        this.appointmentDate = LocalDate.now();
    }

    /**
     * Returns the patient.
     *
     * @return patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Returns the selected health professional.
     *
     * @return health professional
     */
    public HealthProfessional getProfessional() {
        return professional;
    }

    /**
     * Returns the appointment time.
     *
     * @return appointment time
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Returns the appointment date.
     *
     * @return current-day appointment date
     */
    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    /**
     * Checks whether an appointment uses a professional and time pair.
     *
     * @param professionalId health professional ID
     * @param appointmentTime proposed appointment time
     * @return true when both values match this appointment
     */
    public boolean matches(String professionalId, LocalTime appointmentTime) {
        return professional.getId().equals(professionalId) && time.equals(appointmentTime);
    }

    /**
     * Compares appointments by time and then by deterministic tie-breakers.
     *
     * @param other appointment to compare
     * @return negative, zero, or positive according to display order
     */
    @Override
    public int compareTo(Appointment other) {
        int timeComparison = time.compareTo(other.time);
        if (timeComparison != 0) {
            return timeComparison;
        }

        int phoneComparison =
                patient.getMobileNumber().compareTo(other.patient.getMobileNumber());
        if (phoneComparison != 0) {
            return phoneComparison;
        }
        return professional.getId().compareTo(other.professional.getId());
    }

    /**
     * Returns a readable, multi-line appointment summary.
     *
     * @return appointment summary
     */
    @Override
    public String toString() {
        return String.format(
                "Date: %s | Time: %s%n"
                        + "  Patient: %s%n"
                        + "  Professional: %s",
                appointmentDate, time, patient, professional.getSummary());
    }
}
