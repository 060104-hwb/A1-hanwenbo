import java.time.DayOfWeek;
import java.util.Set;

/**
 * Represents a registered nurse.
 */
public final class Nurse extends HealthProfessional {
    private final String registrationNumber;

    /**
     * Creates a nurse.
     *
     * @param id numeric identifier
     * @param name full name
     * @param workingDays available working days
     * @param registrationNumber nursing registration number
     */
    public Nurse(
            String id, String name, Set<DayOfWeek> workingDays, String registrationNumber) {
        super(id, name, workingDays);
        if (registrationNumber == null || registrationNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Nursing registration number cannot be blank.");
        }
        this.registrationNumber = registrationNumber.trim();
    }

    @Override
    public String getRole() {
        return "Nurse";
    }

    @Override
    public String getRoleSpecificDetails() {
        return "Registration: " + registrationNumber;
    }

    @Override
    public String getServiceDescription() {
        return "Provides nursing care and takes blood samples.";
    }
}
