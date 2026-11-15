import java.time.DayOfWeek;
import java.util.Set;

/**
 * Represents a general practitioner.
 */
public final class GeneralPractitioner extends HealthProfessional {
    private final String credentials;

    /**
     * Creates a general practitioner with explicit professional credentials.
     *
     * @param id numeric identifier
     * @param name full name
     * @param workingDays available working days
     * @param credentials medical credentials
     */
    public GeneralPractitioner(
            String id, String name, Set<DayOfWeek> workingDays, String credentials) {
        super(id, name, workingDays);
        if (credentials == null || credentials.trim().isEmpty()) {
            throw new IllegalArgumentException("General practitioner credentials cannot be blank.");
        }
        this.credentials = credentials.trim();
    }

    /**
     * Creates a general practitioner with a sensible default credential.
     *
     * @param id numeric identifier
     * @param name full name
     * @param workingDays available working days
     */
    public GeneralPractitioner(String id, String name, Set<DayOfWeek> workingDays) {
        this(id, name, workingDays, "General registration");
    }

    @Override
    public String getRole() {
        return "General Practitioner";
    }

    @Override
    public String getRoleSpecificDetails() {
        return "Credentials: " + credentials;
    }

    @Override
    public String getServiceDescription() {
        return "Provides general consultations and prescriptions.";
    }
}
