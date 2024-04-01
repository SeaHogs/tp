package vitalconnect.model.person.contactinformation;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's phone number in the clinic.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone {


    public static final String MESSAGE_CONSTRAINTS =
            "Phone numbers should only contain numbers, and it should be 3 to 15 digits long.\n"
                    + "It should not be empty.";
    public static final String MESSAGE_CONSTRAINTS_EDIT =
            "Phone numbers should only contain numbers, and it should be 3 to 15 digits long. ";
    public static final String VALIDATION_REGEX = "\\b\\d{3,15}\\b";
    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public Phone(String phone) {
        requireNonNull(phone);
        value = phone;
    }

    public Phone() {
        value = "";
    }

    public boolean isEmpty() {
        return value.equals("");
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidEditPhone(String test) {
        return test.matches(VALIDATION_REGEX) || test.equals("");
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Phone)) {
            return false;
        }

        Phone otherPhone = (Phone) other;
        return value.equals(otherPhone.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
