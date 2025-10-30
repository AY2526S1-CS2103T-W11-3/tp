package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * Represents a Person's Lesson in the address book.
 * Guarantees: immutable; is always valid
 */
public class Lesson implements Comparable<Lesson> {

    private final LocalTime start;
    private final LocalTime end;
    private final LocalDate date;
    private final String sub;
    private final boolean isPresent;


    /**
     * Constructs a {@code Lesson} with isPresent set to false.
     *
     * @param start A valid time in HH:mm format
     * @param end A valid time after start in HH:mm format
     * @param date A valid date in YYYY-MM-DD format
     * @param sub A valid subject
     */
    public Lesson(String start, String end, String date, String sub) {
        requireAllNonNull(start, end, date, sub);
        this.start = LocalTime.parse(start);
        this.end = LocalTime.parse(end);
        this.date = LocalDate.parse(date);
        this.sub = sub;
        this.isPresent = false;
    }

    /**
     * Constructs a {@code Lesson} with isPresent set to false.
     *
     * @param start A valid time in HH:mm format
     * @param end A valid time after start in HH:mm format
     * @param date A valid date in YYYY-MM-DD format
     * @param sub A valid subject
     */
    public Lesson(LocalTime start, LocalTime end, LocalDate date, String sub) {
        requireAllNonNull(start, end, date, sub);
        this.start = start;
        this.end = end;
        this.date = date;
        this.sub = sub;
        this.isPresent = false;
    }

    /**
     * Constructs a {@code Lesson}.
     *
     * @param start A valid time in HH:mm format
     * @param end A valid time after start in HH:mm format
     * @param date A valid date in YYYY-MM-DD format
     * @param sub A valid subject
     * @param isPresent The attendance status
     */
    public Lesson(String start, String end, String date, String sub, boolean isPresent) {
        requireAllNonNull(start, end, date, sub);
        this.start = LocalTime.parse(start);
        this.end = LocalTime.parse(end);
        this.date = LocalDate.parse(date);
        this.sub = sub;
        this.isPresent = isPresent;
    }

    /**
     * Constructs a {@code Lesson} with a specific attendance status.
     *
     * @param start A valid time
     * @param end A valid time after start
     * @param date A valid date
     * @param sub A valid subject
     * @param isPresent The attendance status
     */
    public Lesson(LocalTime start, LocalTime end, LocalDate date, String sub, boolean isPresent) {
        requireAllNonNull(start, end, date, sub);
        this.start = start;
        this.end = end;
        this.date = date;
        this.sub = sub;
        this.isPresent = isPresent;
    }

    public LocalTime getStart() {
        return this.start;
    }

    public LocalTime getEnd() {
        return this.end;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getSub() {
        return this.sub;
    }

    public boolean isPresent() {
        return this.isPresent;
    }

    /**
     * Returns true if this lesson overlaps in time with {@code other} on the same date.
     * Lessons that end exactly when another begins are not considered overlapping.
     */
    public boolean overlapsWith(Lesson other) {
        requireAllNonNull(other);
        if (!date.equals(other.date)) {
            return false;
        }
        return start.isBefore(other.end) && end.isAfter(other.start);
    }

    /**
     * Returns a string with the lesson's details, excluding attendance.
     */
    public String getLessonDetails() {
        return sub + " class on " + date.toString() + " from " + start.toString() + " to " + end.toString();
    }

    /**
     * Compares this lesson with another lesson chronologically.
     * Lessons are ordered by date first, then by start time.
     *
     * @param other the lesson to compare to
     * @return negative if this lesson is before other, positive if after, 0 if same time
     */
    @Override
    public int compareTo(Lesson other) {
        // First compare by date
        int dateComparison = this.date.compareTo(other.date);
        if (dateComparison != 0) {
            return dateComparison;
        }
        // If same date, compare by start time
        return this.start.compareTo(other.start);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Lesson)) {
            return false;
        }

        Lesson otherLesson = (Lesson) other;
        return start.equals(otherLesson.start)
                && end.equals(otherLesson.end)
                && date.equals(otherLesson.date)
                && sub.equals(otherLesson.sub)
                && isPresent == otherLesson.isPresent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end, date, sub);
    }

    @Override
    public String toString() {
        String attendance = isPresent ? "[Present]" : "[Not Present]";
        return sub + " class: " + date.toString() + " from " + start.toString() + " to " + end.toString() + attendance;
    }

}
