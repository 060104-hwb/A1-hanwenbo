import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

/**
 * Represents information and behaviour shared by all health professionals.
 *
 * <p>The class is abstract because a real health professional always has a
 * specific role. Subclasses provide the role-specific details and services.</p>
 */
public abstract class HealthProfessional {
    private final String id;
    private final String name;
    private final Set<DayOfWeek> workingDays;

    /**
     * Creates a health professional with validated, immutable common data.
     *
     * @param id numeric identifier
     * @param name full name
     * @param workingDays days on which the professional is available
     */
    protected HealthProfessional(String id, String name, Set<DayOfWeek> workingDays) {
        if (id == null || !id.trim().matches("\\d+")) {
            throw new IllegalArgumentException("Professional ID must contain numbers only.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Professional name cannot be blank.");
        }
        if (workingDays == null || workingDays.isEmpty()) {
            throw new IllegalArgumentException("At least one working day is required.");
        }

        this.id = id.trim();
        this.name = name.trim();
        this.workingDays = Collections.unmodifiableSet(EnumSet.copyOf(workingDays));
    }

    /**
     * Returns the numeric professional ID.
     *
     * @return professional ID
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the professional's full name.
     *
     * @return full name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns an unmodifiable view of the professional's working days.
     *
     * @return working days
     */
    public Set<DayOfWeek> getWorkingDays() {
        return workingDays;
    }

    /**
     * Returns the specific role name, such as "General Practitioner".
     *
     * @return role name
     */
    public abstract String getRole();

    /**
     * Returns information unique to the concrete professional type.
     *
     * @return role-specific details
     */
    public abstract String getRoleSpecificDetails();

    /**
     * Describes a service associated with the concrete professional type.
     *
     * @return description of the professional's service
     */
    public abstract String getServiceDescription();

    /**
     * Combines common and role-specific information in a readable line.
     *
     * @return complete professional summary
     */
    public String getSummary() {
        return String.format(
                "%s | %s | ID: %s | Working days: %s | %s | Service: %s",
                name,
                getRole(),
                id,
                formatWorkingDays(),
                getRoleSpecificDetails(),
                getServiceDescription());
    }

    private String formatWorkingDays() {
        StringBuilder result = new StringBuilder();
        for (DayOfWeek day : workingDays) {
            if (result.length() > 0) {
                result.append(", ");
            }
            result.append(day.getDisplayName(TextStyle.FULL, Locale.ENGLISH));
        }
        return result.toString();
    }

    /**
     * Uses the professional ID as the business identity for equality.
     *
     * @param other object to compare
     * @return true when both objects represent the same professional ID
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HealthProfessional)) {
            return false;
        }
        HealthProfessional professional = (HealthProfessional) other;
        return id.equals(professional.id);
    }

    /**
     * Produces a hash code consistent with equality by professional ID.
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Returns the complete summary when an object is printed.
     *
     * @return readable professional summary
     */
    @Override
    public String toString() {
        return getSummary();
    }
}
