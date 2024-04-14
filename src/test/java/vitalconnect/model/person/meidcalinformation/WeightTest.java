package vitalconnect.model.person.meidcalinformation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static vitalconnect.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import vitalconnect.model.person.medicalinformation.Weight;

public class WeightTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Weight(null));
    }

    @Test
    public void constructor_impossibleWeight_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Weight("1000"));
    }

    @Test
    public void constructor_negativeWeight_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Weight("-112"));
    }

    @Test
    public void isValidWeight_return_false() {
        assertFalse(Weight.isValidWeight("650000000000000000000000000"));
        assertFalse(Weight.isValidWeight("-170"));
        assertFalse(Weight.isValidWeight("words"));
        assertFalse(Weight.isValidWeight(" "));
        assertFalse(Weight.isValidWeight("0"));
    }

    @Test
    public void isValidWeight_return_true() {
        assertTrue(Weight.isValidWeight("170"));
        assertTrue(Weight.isValidWeight("111"));
        assertTrue(Weight.isValidWeight("649"));
    }

    @Test
    public void equals() {
        Weight weight = new Weight("170");

        // same values -> returns true
        assertEquals(weight, new Weight("170"));

        // same object -> returns true
        assertEquals(weight, weight);

        // null -> returns false
        assertNotEquals(null, weight);

        // different types -> returns false
        assertNotEquals(5.0f, weight, String.valueOf(0.0));

        // different values -> returns false
        assertNotEquals(weight, new Weight("180"));
    }
}
