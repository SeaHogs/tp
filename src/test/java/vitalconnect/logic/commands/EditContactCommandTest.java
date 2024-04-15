package vitalconnect.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import vitalconnect.commons.core.GuiSettings;
import vitalconnect.commons.core.index.Index;
import vitalconnect.logic.commands.exceptions.CommandException;
import vitalconnect.model.Appointment;
import vitalconnect.model.Clinic;
import vitalconnect.model.Model;
import vitalconnect.model.ReadOnlyClinic;
import vitalconnect.model.ReadOnlyUserPrefs;
import vitalconnect.model.person.Person;
import vitalconnect.model.person.contactinformation.Address;
import vitalconnect.model.person.contactinformation.ContactInformation;
import vitalconnect.model.person.contactinformation.Email;
import vitalconnect.model.person.contactinformation.Phone;
import vitalconnect.model.person.identificationinformation.IdentificationInformation;
import vitalconnect.model.person.identificationinformation.Nric;
import vitalconnect.model.person.medicalinformation.MedicalInformation;
import vitalconnect.testutil.PersonBuilder;

public class EditContactCommandTest {
    private Nric validNricAlreadyInModel = new Nric("S1234567D");
    private Email validEmail = new Email("email@test.com");
    private Phone validPhone = new Phone("12345678");
    private Address validAddress = new Address("address");

    @Test
    public void execute_editEmail_success() throws CommandException {
        EditContactCommandTest.ModelStubHasOnePersonWithEmptyCI modelStub =
            new EditContactCommandTest.ModelStubHasOnePersonWithEmptyCI();
        CommandResult commandResult = new EditContactCommand(validNricAlreadyInModel, validEmail, null, null)
            .execute(modelStub);

        assertEquals(EditContactCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_editPhone_success() throws CommandException {
        EditContactCommandTest.ModelStubHasOnePersonWithEmptyCI modelStub =
            new EditContactCommandTest.ModelStubHasOnePersonWithEmptyCI();
        CommandResult commandResult = new EditContactCommand(validNricAlreadyInModel, null, validPhone, null)
            .execute(modelStub);

        assertEquals(EditContactCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_editAddress_success() throws CommandException {
        EditContactCommandTest.ModelStubHasOnePersonWithEmptyCI modelStub =
            new EditContactCommandTest.ModelStubHasOnePersonWithEmptyCI();
        CommandResult commandResult = new EditContactCommand(validNricAlreadyInModel, null, null, validAddress)
            .execute(modelStub);

        assertEquals(EditContactCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_editAll_success() throws CommandException {
        EditContactCommandTest.ModelStubHasOnePersonWithEmptyCI modelStub =
            new EditContactCommandTest.ModelStubHasOnePersonWithEmptyCI();
        CommandResult commandResult = new EditContactCommand(validNricAlreadyInModel,
            validEmail, validPhone, validAddress).execute(modelStub);
        assertEquals(EditContactCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setCurrentPredicate(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Predicate<Person> getCurrentPredicate() {
            return null;
        }
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getClinicFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setClinicFilePath(Path clinicFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setClinic(ReadOnlyClinic newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyClinic getClinic() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyClinic getClinicCopy() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void clearAppointments() {
            throw new AssertionError("This method should not be called.");
        }
        
        @Override
        public List<Appointment> getAppointmentsCopy() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean doesPersonExist(String name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean doesIcExist(String name) {
            throw new AssertionError("This method should not be called.");
        }

        @Test
        public void addAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAppointments(List<Appointment> appointments) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public List<Appointment> getConflictingAppointments(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public List<Appointment> getConflictingAppointmentsForExistingApt(Index index, Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Appointment> getFilteredAppointmentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Appointment> getFoundAppointmentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public List<Appointment> findAppointmentsByNric(Nric nric) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Person findPersonByNric(Nric nric) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Person findPersonByNric(IdentificationInformation info) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updatePersonContactInformation(Nric nric, ContactInformation contactInformation) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * @param nric
         * @param medicalInformation
         */
        @Override
        public void updatePersonMedicalInformation(Nric nric, MedicalInformation medicalInformation) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateAppointment(Index index, Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubHasOnePersonWithEmptyCI extends EditContactCommandTest.ModelStub {
        final Person person = new PersonBuilder().withEmail("abc@test.com").build();

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

        @Override
        public void updatePersonContactInformation(Nric nric, ContactInformation contactInformation) {
        }
    }
}
