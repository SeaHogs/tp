package vitalconnect.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static vitalconnect.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
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


public class EditAppointmentCommandTest {

    @Test
    public void execute_appointmentEditedSuccessfully() throws Exception {
        ModelStubAcceptingAppointmentEdited modelStub = new ModelStubAcceptingAppointmentEdited();
        Index index = Index.fromOneBased(1);
        LocalDateTime dateTime = LocalDateTime.parse("02/06/2024 1330", DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        int duration = 2;

        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(index, dateTime, duration);
        String expectedMessage = EditAppointmentCommand.MESSAGE_SUCCESS + "index: 1\nUpdated appointment detail:\n"
                + "Appointment with Alice from 2024-06-02 13:30 to 2024-06-02 14:00";

        CommandResult commandResult = editAppointmentCommand.execute(modelStub);
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_appointmentIndexInvalid_throwsCommandException() {
        ModelStubAcceptingAppointmentEdited modelStub = new ModelStubAcceptingAppointmentEdited();
        Index outOfBoundIndex = Index.fromOneBased(modelStub.getFilteredAppointmentList().size() + 1);
        LocalDateTime dateTime = LocalDateTime.now().plusDays(1); // future date to avoid past date error
        int duration = 2;

        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(outOfBoundIndex, dateTime, duration);

        assertThrows(CommandException.class, () -> editAppointmentCommand.execute(modelStub));
    }

    @Test
    public void execute_appointmentTimeConflict_throwsCommandException() {
        ModelStub modelStub = new ModelStubWithConflictingAppointment();
        Index index = Index.fromOneBased(1);
        LocalDateTime dateTime = LocalDateTime.parse("02/06/2024 1330", DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        int duration = 2; // Assume this duration causes a conflict

        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(index, dateTime, duration);

        assertThrows(CommandException.class, () -> editAppointmentCommand.execute(modelStub));
    }

    @Test
    public void execute_pastDateTime_throwsCommandException() {
        ModelStubAcceptingAppointmentEdited modelStub = new ModelStubAcceptingAppointmentEdited();
        Index index = Index.fromOneBased(1);
        LocalDateTime pastDateTime = LocalDateTime.now().minusDays(1);
        int duration = 2;

        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(index, pastDateTime, duration);

        assertThrows(CommandException.class, () -> editAppointmentCommand.execute(modelStub));
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


    private class ModelStubAcceptingAppointmentEdited extends ModelStub {

        private final ArrayList<Appointment> appointments = new ArrayList<>();

        ModelStubAcceptingAppointmentEdited() {
            appointments.add(new Appointment("Alice", "S1234567A",
                    LocalDateTime.of(2024, 3, 14, 15, 30),
                    LocalDateTime.of(2024, 3, 14, 16, 30), 4));
        }

        @Override
        public ObservableList<Appointment> getFilteredAppointmentList() {
            return FXCollections.observableArrayList(appointments);
        }

        @Override
        public void updateAppointment(Index index, Appointment appointment) {
            appointments.set(index.getZeroBased(), appointment);
        }

        @Override
        public List<Appointment> getConflictingAppointmentsForExistingApt(Index index, Appointment appointment) {
            return new ArrayList<>(); // No conflicts
        }
    }

    private class ModelStubWithConflictingAppointment extends ModelStub {
        private final ArrayList<Appointment> appointments = new ArrayList<>();

        ModelStubWithConflictingAppointment() {
            appointments.add(new Appointment("Alice", "S1234567A",
                    LocalDateTime.of(2024, 3, 14, 15, 30),
                    LocalDateTime.of(2024, 3, 14, 16, 30), 4));
        }

        @Override
        public ObservableList<Appointment> getFilteredAppointmentList() {
            return FXCollections.observableArrayList(appointments);
        }

        @Override
        public void updateAppointment(Index index, Appointment appointment) {
            appointments.set(index.getZeroBased(), appointment);
        }

        @Override
        public List<Appointment> getConflictingAppointmentsForExistingApt(Index index, Appointment newAppointment) {
            // Check if the new appointment conflicts with existing ones
            // For the sake of this stub, we assume all appointments conflict
            return new ArrayList<>(appointments);
        }
    }

}

