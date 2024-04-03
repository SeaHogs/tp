package vitalconnect.model;

import java.util.function.Predicate;

/**
 * Tests that an {@code Appointment}'s patient NRIC matches the given NRIC.
 */
public class AptWithNricPredicate implements Predicate<Appointment> {
    private final String nric;

    public AptWithNricPredicate(String nric) {
        this.nric = nric;
    }

    @Override
    public boolean test(Appointment appointment) {
        return appointment.getPatientIc().equalsIgnoreCase(nric);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AptWithNricPredicate)) {
            return false;
        }

        AptWithNricPredicate otherPredicate = (AptWithNricPredicate) other;
        return nric.equals(otherPredicate.nric);
    }
}

