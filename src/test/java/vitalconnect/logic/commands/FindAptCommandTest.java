package vitalconnect.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static vitalconnect.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import vitalconnect.commons.core.GuiSettings;
import vitalconnect.commons.core.index.Index;
import vitalconnect.logic.commands.exceptions.CommandException;
import vitalconnect.model.Appointment;
import vitalconnect.model.Model;
import vitalconnect.model.ReadOnlyClinic;
import vitalconnect.model.ReadOnlyUserPrefs;
import vitalconnect.model.person.Person;
import vitalconnect.model.person.contactinformation.ContactInformation;
import vitalconnect.model.person.identificationinformation.IdentificationInformation;
import vitalconnect.model.person.identificationinformation.Nric;
import vitalconnect.model.person.medicalinformation.MedicalInformation;

public class FindAptCommandTest {

    @Test
    public void execute_patientExistsWithAppointments_success() throws CommandException {
        // setup
        Nric existingNric = new Nric("S1234567D");
        ModelStub modelStub = new ModelStubWithAppointments(existingNric);
        FindAptCommand command = new FindAptCommand(existingNric);

        CommandResult commandResult = command.execute(modelStub);

        assertEquals("Here is the appointment for the patient: \nAppointment with Alice from 2024-06-14 "
                        + "15:30 to 2024-06-14 16:30\n"
                        + "\nNotice: You cannot delete an appointment by using the index of this list, "
                        + "you should delete an "
                        + "appointment by providing its index in the list of all appointment.",
                commandResult.getFeedbackToUser());
    }



    @Test
    public void execute_patientExistsNoAppointments_noAppointmentsMessage() throws CommandException {
        // setup
        Nric existingNric = new Nric("S1234567D");
        ModelStub modelStub = new ModelStubWithNoAppointments(existingNric);
        FindAptCommand command = new FindAptCommand(existingNric);

        // execution
        CommandResult commandResult = command.execute(modelStub);

        // verification
        assertEquals("The patient does not have an appointment.",
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_patientDoesNotExist_throwsCommandException() {
        Nric nonExistentNric = new Nric("S1234567D");
        ModelStub modelStub = new ModelStubWithNoPerson();
        FindAptCommand command = new FindAptCommand(nonExistentNric);

        assertThrows(CommandException.class, () -> command.execute(modelStub));
    }

    private class ModelStub implements Model {
        @Override
        public void setCurrentPredicate(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Predicate<Person> getCurrentPredicate() {
            throw new AssertionError("This method should not be called.");
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
        public boolean doesIcExist(String name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean doesPersonExist(String name) {
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
            return null;
        }

        @Override
        public Person findPersonByNric(IdentificationInformation info) {
            return null;
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

        }

        @Override
        public void updateAppointment(Index index, Appointment appointment) {
        }

    }


    private class ModelStubWithAppointments extends ModelStub {
        private Nric nric;

        ModelStubWithAppointments(Nric nric) {
            this.nric = nric;
        }

        @Override
        public boolean doesIcExist(String ic) {
            return nric.toString().equals(ic);
        }

        @Override
        public List<Appointment> findAppointmentsByNric(Nric nric) {
            // Assume there are existing appointments for the provided NRIC
            List<Appointment> appointments = new ArrayList<>();
            appointments.add(new Appointment("Alice", nric.toString(),
                    LocalDateTime.of(2024, 6, 14, 15, 30),
                    LocalDateTime.of(2024, 6, 14, 16, 30), 4));
            return appointments;
        }
    }

    private class ModelStubWithNoAppointments extends ModelStub {
        private Nric nric;

        ModelStubWithNoAppointments(Nric nric) {
            this.nric = nric;
        }

        @Override
        public boolean doesIcExist(String ic) {
            return nric.toString().equals(ic);
        }

        @Override
        public List<Appointment> findAppointmentsByNric(Nric nric) {
            // Assume there are no appointments for the provided NRIC
            return new ArrayList<>();
        }
    }

    private class ModelStubWithNoPerson extends ModelStub {
        @Override
        public boolean doesIcExist(String ic) {
            return false;
        }
    }
}

