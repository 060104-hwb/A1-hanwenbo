import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages current-day appointments for a clinic.
 *
 * <p>Mutating methods return a message so the caller can display either a
 * success confirmation or a warning. Collections returned to callers are
 * defensive copies, preventing external code from changing manager state.</p>
 */
public final class AppointmentManager {
    private final ClinicSchedule schedule;
    private final List<Appointment> appointments;

    /**
     * Creates an empty appointment manager.
     *
     * @param schedule clinic appointment schedule
     */
    public AppointmentManager(ClinicSchedule schedule) {
        if (schedule == null) {
            throw new IllegalArgumentException("Appointment manager requires a schedule.");
        }
        this.schedule = schedule;
        this.appointments = new ArrayList<>();
    }

    /**
     * Validates and adds an appointment.
     *
     * @param appointment appointment to add
     * @return success or warning message
     */
    public String addAppointment(Appointment appointment) {
        if (appointment == null) {
            return "WARNING: No appointment was supplied.";
        }
        if (!schedule.containsSlot(appointment.getTime())) {
            return "WARNING: " + appointment.getTime()
                    + " is not a valid clinic appointment slot.";
        }
        if (isProfessionalBooked(
                appointment.getProfessional().getId(), appointment.getTime())) {
            return "WARNING: "
                    + appointment.getProfessional().getName()
                    + " is already booked at "
                    + appointment.getTime()
                    + ".";
        }

        appointments.add(appointment);
        return "SUCCESS: Appointment added for "
                + appointment.getPatient().getName()
                + " with "
                + appointment.getProfessional().getName()
                + " at "
                + appointment.getTime()
                + ".";
    }

    /**
     * Returns every appointment sorted by time.
     *
     * @return sorted appointment copy
     */
    public List<Appointment> getAllAppointments() {
        return sortedCopy(appointments);
    }

    /**
     * Returns appointments for one professional, sorted by time.
     *
     * @param professionalId health professional ID
     * @return matching appointments
     */
    public List<Appointment> findByProfessionalId(String professionalId) {
        List<Appointment> matches = new ArrayList<>();
        if (professionalId == null) {
            return matches;
        }

        String searchId = professionalId.trim();
        for (Appointment appointment : appointments) {
            if (appointment.getProfessional().getId().equals(searchId)) {
                matches.add(appointment);
            }
        }
        Collections.sort(matches);
        return matches;
    }

    /**
     * Returns appointments for one patient mobile number, sorted by time.
     *
     * @param mobileNumber patient mobile number
     * @return matching appointments
     */
    public List<Appointment> findByPatientPhone(String mobileNumber) {
        List<Appointment> matches = new ArrayList<>();
        if (mobileNumber == null) {
            return matches;
        }

        String searchNumber = mobileNumber.trim();
        for (Appointment appointment : appointments) {
            if (appointment.getPatient().getMobileNumber().equals(searchNumber)) {
                matches.add(appointment);
            }
        }
        Collections.sort(matches);
        return matches;
    }

    /**
     * Cancels an appointment by professional ID and appointment time.
     *
     * @param professionalId health professional ID
     * @param time appointment time
     * @return success or warning message
     */
    public String cancelByProfessionalAndTime(String professionalId, LocalTime time) {
        if (professionalId == null || time == null) {
            return "WARNING: Professional ID and appointment time are required.";
        }

        String searchId = professionalId.trim();
        for (int index = 0; index < appointments.size(); index++) {
            if (appointments.get(index).matches(searchId, time)) {
                appointments.remove(index);
                return "SUCCESS: Appointment for professional "
                        + searchId
                        + " at "
                        + time
                        + " was cancelled.";
            }
        }
        return "WARNING: No matching appointment exists for professional "
                + searchId
                + " at "
                + time
                + ".";
    }

    /**
     * Cancels the single appointment associated with a mobile number.
     *
     * <p>If the number is linked to more than one appointment, the operation
     * is rejected so the manager does not cancel the wrong booking.</p>
     *
     * @param mobileNumber patient mobile number
     * @return success or warning message
     */
    public String cancelByPatientPhone(String mobileNumber) {
        if (mobileNumber == null || mobileNumber.trim().isEmpty()) {
            return "WARNING: Patient mobile number is required.";
        }

        String searchNumber = mobileNumber.trim();
        List<Appointment> matches = findByPatientPhone(searchNumber);
        if (matches.isEmpty()) {
            return "WARNING: No appointment exists for patient phone "
                    + searchNumber
                    + ".";
        }
        if (matches.size() > 1) {
            return "WARNING: More than one appointment uses this phone number. "
                    + "Cancel by professional ID and time instead.";
        }

        appointments.remove(matches.get(0));
        return "SUCCESS: Appointment for patient phone "
                + searchNumber
                + " was cancelled.";
    }

    /**
     * Returns the current number of appointments.
     *
     * @return appointment count
     */
    public int size() {
        return appointments.size();
    }

    /**
     * Returns the clinic schedule used by this manager.
     *
     * @return clinic schedule
     */
    public ClinicSchedule getSchedule() {
        return schedule;
    }

    private boolean isProfessionalBooked(String professionalId, LocalTime time) {
        for (Appointment appointment : appointments) {
            if (appointment.matches(professionalId, time)) {
                return true;
            }
        }
        return false;
    }

    private List<Appointment> sortedCopy(List<Appointment> source) {
        List<Appointment> copy = new ArrayList<>(source);
        Collections.sort(copy);
        return copy;
    }
}
