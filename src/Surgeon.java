import java.time.DayOfWeek;
import java.util.Set;

/**
 * Represents a surgeon with a particular surgical specialty.
 */
public final class Surgeon extends HealthProfessional {
    private final String credentials;
    private final String specialty;

    /**
     * Creates a surgeon.
     *
     * @param id numeric identifier
     * @param name full name
     * @param workingDays available working days
     * @param credentials surgical credentials
     * @param specialty surgical specialty
     */
    public Surgeon(
            String id,
            String name,
            Set<DayOfWeek> workingDays,
            String credentials,
            String specialty) {
        super(id, name, workingDays);
        if (credentials == null || credentials.trim().isEmpty()) {
            throw new IllegalArgumentException("Surgeon credentials cannot be blank.");
        }
        if (specialty == null || specialty.trim().isEmpty()) {
            throw new IllegalArgumentException("Surgical specialty cannot be blank.");
        }
        this.credentials = credentials.trim();
        this.specialty = specialty.trim();
    }

    @Override
    public String getRole() {
        return "Surgeon";
    }

    @Override
    public String getRoleSpecificDetails() {
        return "Credentials: " + credentials + " | Specialty: " + specialty;
    }

    @Override
    public String getServiceDescription() {
        return "Performs consultations and surgical procedures in " + specialty + ".";
    }
}
