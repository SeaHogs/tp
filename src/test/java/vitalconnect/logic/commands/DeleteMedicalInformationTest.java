package vitalconnect.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static vitalconnect.testutil.Assert.assertThrows;
import static vitalconnect.testutil.TypicalPersons.getTypicalClinic;

import java.util.ArrayList;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import vitalconnect.logic.Messages;
import vitalconnect.logic.commands.exceptions.CommandException;
import vitalconnect.model.Clinic;
import vitalconnect.model.Model;
import vitalconnect.model.ModelManager;
import vitalconnect.model.ReadOnlyClinic;
import vitalconnect.model.UserPrefs;
import vitalconnect.model.person.Person;
import vitalconnect.model.person.identificationinformation.Nric;
import vitalconnect.testutil.PersonBuilder;

public class DeleteMedicalInformationTest {
    private Model model = new ModelManager(getTypicalClinic(), new UserPrefs(), new ArrayList<>());

    @Test
    public void constructor_nullMedicalInformation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteMedicalCommand(null));
    }

    @Test
    public void execute_personNotFind_failure() {
        assertThrows(CommandException.class, Messages.MESSAGE_PERSON_NOT_FOUND, () ->
          new DeleteMedicalCommand(new Nric("S2519229Z")).execute(model));
    }

    @Test
    public void execute_deleteMedicalInformation_success() throws CommandException {
        ModelStubHasOnePersonWithNoneEmptyMI modelStub = new ModelStubHasOnePersonWithNoneEmptyMI();
        Person validPerson = modelStub.findPersonByNric(new Nric("S2519229Z"));
        CommandResult commandResult = new DeleteMedicalCommand(validPerson.getIdentificationInformation().getNric())
            .execute(modelStub);

        assertEquals(DeleteMedicalCommand.MESSAGE_SUCCESS,
            commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        Nric firstNric = new Nric("S2519229Z");
        Nric secondNric = new Nric("S2519229Z");
        DeleteMedicalCommand deleteMedicalCommand = new DeleteMedicalCommand(firstNric);
        DeleteMedicalCommand deleteMedicalCommandCopy = new DeleteMedicalCommand(secondNric);

        // same object -> returns true
        assertTrue(deleteMedicalCommand.equals(deleteMedicalCommand));

        // same values -> returns true
        DeleteMedicalCommand deleteMedicalCommandCopy2 = new DeleteMedicalCommand(firstNric);
        assertTrue(deleteMedicalCommand.equals(deleteMedicalCommandCopy2));

        // different types -> returns false
        assertFalse(deleteMedicalCommand.equals(1));

        // null -> returns false
        assertFalse(deleteMedicalCommand.equals(null));
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubHasOnePersonWithNoneEmptyMI extends DeleteContactCommandTest.ModelStub {
        final Person person = new PersonBuilder().withHeight("180").withWeight("70").withTags("testTag").build();

        @Override
        public Person findPersonByNric(Nric nric) {
            return this.person;
        }

        @Override
        public ReadOnlyClinic getClinic() {
            return new Clinic();
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
        }
    }
}
