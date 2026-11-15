/**
 * Stores the patient details required for an appointment.
 */
public final class Patient {
    private final String name;
    private final String mobileNumber;

    /**
     * Creates a validated patient.
     *
     * @param name patient name
     * @param mobileNumber mobile number containing 8 to 15 digits
     */
    public Patient(String name, String mobileNumber) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Patient name cannot be blank.");
        }
        if (mobileNumber == null || !mobileNumber.trim().matches("\\d{8,15}")) {
            throw new IllegalArgumentException(
                    "Patient mobile number must contain between 8 and 15 digits.");
        }
        this.name = name.trim();
        this.mobileNumber = mobileNumber.trim();
    }

    /**
     * Returns the patient's name.
     *
     * @return patient name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the patient's mobile number.
     *
     * @return mobile number
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * Returns a readable patient summary.
     *
     * @return patient summary
     */
    @Override
    public String toString() {
        return name + " (" + mobileNumber + ")";
    }
}
