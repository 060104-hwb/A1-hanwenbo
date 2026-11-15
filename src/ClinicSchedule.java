import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the clinic's configurable daily appointment slots.
 */
public final class ClinicSchedule {
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final int slotMinutes;
    private final List<LocalTime> slots;

    /**
     * Creates a schedule from a start time, end time, and slot length.
     *
     * @param startTime first appointment start time
     * @param endTime time at which the clinic closes
     * @param slotMinutes number of minutes in each slot
     */
    public ClinicSchedule(LocalTime startTime, LocalTime endTime, int slotMinutes) {
        if (startTime == null || endTime == null) {
            throw new IllegalArgumentException("Schedule times cannot be null.");
        }
        if (!startTime.isBefore(endTime) || slotMinutes <= 0) {
            throw new IllegalArgumentException("Schedule must have a positive duration and slot size.");
        }

        long totalMinutes = Duration.between(startTime, endTime).toMinutes();
        if (totalMinutes % slotMinutes != 0) {
            throw new IllegalArgumentException(
                    "The clinic duration must be divisible by the slot length.");
        }

        this.startTime = startTime;
        this.endTime = endTime;
        this.slotMinutes = slotMinutes;
        this.slots = Collections.unmodifiableList(createSlots());
    }

    private List<LocalTime> createSlots() {
        List<LocalTime> generatedSlots = new ArrayList<>();
        LocalTime currentTime = startTime;
        while (currentTime.isBefore(endTime)) {
            generatedSlots.add(currentTime);
            currentTime = currentTime.plusMinutes(slotMinutes);
        }
        return generatedSlots;
    }

    /**
     * Returns all valid start times in chronological order.
     *
     * @return unmodifiable list of appointment slots
     */
    public List<LocalTime> getSlots() {
        return slots;
    }

    /**
     * Checks whether a time is a valid appointment start time.
     *
     * @param time proposed appointment time
     * @return true when the time belongs to the configured schedule
     */
    public boolean containsSlot(LocalTime time) {
        return time != null && slots.contains(time);
    }

    /**
     * Returns a readable description of the schedule.
     *
     * @return schedule description
     */
    public String getDescription() {
        return startTime + " to " + endTime + " in " + slotMinutes + "-minute slots";
    }
}
