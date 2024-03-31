package vitalconnect.model.person.medicalinformation;

import static java.util.Objects.requireNonNull;
import static vitalconnect.commons.util.AppUtil.checkArgument;

/**
 * Represents a MedicalHistoryTag in the clinic.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class MedicalHistoryTag {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String tagName;

    /**
     * Constructs a {@code MedicalHistoryTag}.
     *
     * @param tagName A valid medical history tag name.
     */
    public MedicalHistoryTag(String tagName) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.tagName = tagName;
    }

    /**
     * Returns true if a given string is a valid medical history tag name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MedicalHistoryTag)) {
            return false;
        }

        MedicalHistoryTag otherMedicalHistoryTag = (MedicalHistoryTag) other;
        return tagName.equals(otherMedicalHistoryTag.tagName);
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + tagName + ']';
    }

}

