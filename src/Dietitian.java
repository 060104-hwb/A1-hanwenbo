import java.time.DayOfWeek;
import java.util.Set;

/**
 * Represents a dietitian with a specific area of dietary expertise.
 */
public final class Dietitian extends HealthProfessional {
    private final String accreditation;
    private final String dietaryFocus;

    /**
     * Creates a dietitian.
     *
     * @param id numeric identifier
     * @param name full name
     * @param workingDays available working days
     * @param accreditation dietetic accreditation
     * @param dietaryFocus main area of dietary practice
     */
    public Dietitian(
            String id,
            String name,
            Set<DayOfWeek> workingDays,
            String accreditation,
            String dietaryFocus) {
        super(id, name, workingDays);
        if (accreditation == null || accreditation.trim().isEmpty()) {
            throw new IllegalArgumentException("Dietitian accreditation cannot be blank.");
        }
        if (dietaryFocus == null || dietaryFocus.trim().isEmpty()) {
            throw new IllegalArgumentException("Dietary focus cannot be blank.");
        }
        this.accreditation = accreditation.trim();
        this.dietaryFocus = dietaryFocus.trim();
    }

    @Override
    public String getRole() {
        return "Dietitian";
    }

    @Override
    public String getRoleSpecificDetails() {
        return "Accreditation: " + accreditation + " | Focus: " + dietaryFocus;
    }

    @Override
    public String getServiceDescription() {
        return "Provides dietary assessments and personalised meal plans.";
    }
}
