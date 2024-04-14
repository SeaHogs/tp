package vitalconnect.model.person.meidcalinformation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static vitalconnect.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import vitalconnect.model.person.medicalinformation.Height;
import vitalconnect.model.person.medicalinformation.MedicalInformation;
import vitalconnect.model.person.medicalinformation.Weight;

public class MedicalInformationTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MedicalInformation(new Height(null), new Weight(null)));
        assertThrows(NullPointerException.class, () -> new MedicalInformation(new Height(null), new Weight("70")));
        assertThrows(NullPointerException.class, () -> new MedicalInformation(new Height("170"), new Weight(null)));
    }

    @Test
    public void constructor_invalidMedicalInformation_throwsIllegalArgumentException() {
        String invalidHeight = "awd";
        String invalidWeight = "ieee";
        String validHeight = "170";
        String validWeight = "70";

        Height validHeightObj = new Height(validHeight);
        Weight validWeightObj = new Weight(validWeight);

        assertThrows(IllegalArgumentException.class, () ->
                new MedicalInformation(new Height(invalidHeight), new Weight(invalidWeight)));
        assertThrows(IllegalArgumentException.class, () ->
                new MedicalInformation(validHeightObj, new Weight(invalidWeight)));
        assertThrows(IllegalArgumentException.class, () ->
                new MedicalInformation(new Height(invalidHeight), validWeightObj));

        assertDoesNotThrow(() -> new MedicalInformation(validHeightObj, validWeightObj));
    }

    @Test
    public void isEmpty() {
        MedicalInformation medicalInformation = new MedicalInformation();
        assertTrue(medicalInformation.isEmpty());

        medicalInformation = new MedicalInformation(new Height("170"), new Weight("70"));
        assertFalse(medicalInformation.isEmpty());
    }

    @Test
    public void getCopy() {
        MedicalInformation medicalInformation = new MedicalInformation(new Height("170"), new Weight("70"));
        MedicalInformation copy = medicalInformation.getCopy();
        assertEquals(medicalInformation, copy);
    }

    @Test
    public void equals() {
        MedicalInformation medicalInformation = new MedicalInformation(new Height("170"), new Weight("70"));
        MedicalInformation copy = medicalInformation.getCopy();

        // same values -> returns true
        assertEquals(medicalInformation, copy);

        // same object -> returns true
        assertEquals(medicalInformation, medicalInformation);

        // null -> returns false
        assertNotEquals(null, medicalInformation);

        // different types -> returns false
        assertNotEquals(5.0f, medicalInformation, String.valueOf(0.0));

        // different values -> returns false
        assertNotEquals(medicalInformation, new MedicalInformation(new Height("180"), new Weight("70")));
    }
}
