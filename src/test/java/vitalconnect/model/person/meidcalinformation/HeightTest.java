package vitalconnect.model.person.meidcalinformation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static vitalconnect.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import vitalconnect.model.person.medicalinformation.Height;
public class HeightTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Height(null));
    }

    @Test
    public void constructor_impossibleHeight_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Height("1000"));
    }

    @Test
    public void constructor_negativeHeight_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Height("-112"));
    }

    @Test
    public void isValidHeight_return_false() {
        assertFalse(Height.isValidHeight("300000000000000000000000000"));
        assertFalse(Height.isValidHeight("-170"));
        assertFalse(Height.isValidHeight("words"));
        assertFalse(Height.isValidHeight(" "));
        assertFalse(Height.isValidHeight("0"));
    }

    @Test
    public void isValidHeight_return_true() {
        assertTrue(Height.isValidHeight("170"));
        assertTrue(Height.isValidHeight("111"));
        assertTrue(Height.isValidHeight("271"));
    }

    @Test
    public void equals() {
        Height height = new Height("170");

        // same values -> returns true
        assertEquals(height, new Height("170"));

        // same object -> returns true
        assertEquals(height, height);

        // null -> returns false
        assertNotEquals(null, height);

        // different types -> returns false
        assertNotEquals(5.0f, height, String.valueOf(0.0));

        // different values -> returns false
        assertNotEquals(height, new Height("180"));
    }
}
