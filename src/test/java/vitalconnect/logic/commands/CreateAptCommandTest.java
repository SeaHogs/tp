package vitalconnect.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static vitalconnect.logic.Messages.MESSAGE_PERSON_NOT_FOUND;
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
import vitalconnect.logic.parser.ParserUtil;
import vitalconnect.logic.parser.exceptions.ParseException;
import vitalconnect.model.Appointment;
import vitalconnect.model.Model;
import vitalconnect.model.ReadOnlyClinic;
import vitalconnect.model.ReadOnlyUserPrefs;
import vitalconnect.model.person.Person;
import vitalconnect.model.person.contactinformation.ContactInformation;
import vitalconnect.model.person.identificationinformation.IdentificationInformation;
import vitalconnect.model.person.identificationinformation.Name;
import vitalconnect.model.person.identificationinformation.Nric;
import vitalconnect.model.person.medicalinformation.MedicalInformation;

public class CreateAptCommandTest {

    @Test
    public void execute_icNotExist_throwsCommandException() throws ParseException {
        ModelStub modelStub = new ModelStubWithoutPerson();
        Nric patientIc = new Nric("S4848058F");
        LocalDateTime dateTimeStr = ParserUtil.parseTime("02/06/2026 1330");
        int duration = 2;
        CreateAptCommand createAptCommand = new CreateAptCommand(patientIc, dateTimeStr, duration);

        assertThrows(CommandException.class,
            MESSAGE_PERSON_NOT_FOUND, () -> createAptCommand.execute(modelStub));
    }

    @Test
    public void execute_invalidDuration_throwsCommandException() throws ParseException {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Nric patientIc = new Nric("S1234567D");
        LocalDateTime dateTime = ParserUtil.parseTime("02/02/2024 1330");
        int duration = 0; // invalid duration
        CreateAptCommand createAptCommand = new CreateAptCommand(patientIc, dateTime, duration);

        assertThrows(CommandException.class, () -> createAptCommand.execute(modelStub));
    }

    @Test
    public void execute_conflictingAppointment_throwsCommandException() throws ParseException {
        ModelStub modelStub = new ModelStubWithConflictingAppointment();
        Nric patientIc = new Nric("S1234567D");
        LocalDateTime dateTimeStr = ParserUtil.parseTime("02/02/2024 1330");
        int duration = 2;
        CreateAptCommand createAptCommand = new CreateAptCommand(patientIc, dateTimeStr, duration);

        assertThrows(CommandException.class, () -> createAptCommand.execute(modelStub));
    }

    @Test
    public void execute_appointmentInPast_throwsCommandException() {
        ModelStub modelStub = new ModelStubAcceptingPersonAdded();
        Nric patientIc = new Nric("S1234567D");
        // Set a date and time in the past
        LocalDateTime pastDateTime = LocalDateTime.now().minusDays(1);
        int duration = 2; // duration in units (each unit represents 15 minutes)

        CreateAptCommand createAptCommand = new CreateAptCommand(patientIc, pastDateTime, duration);

        assertThrows(CommandException.class,
                "Appointment time cannot be in the past.", () -> createAptCommand.execute(modelStub));
    }

    @Test
    public void execute_appointmentCreatedSuccessfully() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Nric patientIc = new Nric("S1234567D");
        LocalDateTime dateTimeStr = ParserUtil.parseTime("02/02/2025 1330");
        int duration = 2;

        CreateAptCommand createAptCommand = new CreateAptCommand(patientIc, dateTimeStr, duration);

        CommandResult commandResult = createAptCommand.execute(modelStub);

        String successString = String.format("Created an appointment successfully!\nName: "
                        + "Amy" + "\nNRIC: %s\nStart time: 2 Feb 2025 13:30\nEnd time: 2 Feb 2025 14:00",
                patientIc.toString());

        assertEquals(successString, commandResult.getFeedbackToUser());
        assertTrue(modelStub.appointmentsAdded.stream().anyMatch(appointment ->
                appointment.getPatientIc().equals(patientIc.toString())
                        && appointment.getDateTime().equals(dateTimeStr)));
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


    private class ModelStubWithoutPerson extends ModelStub {
        @Override
        public boolean doesPersonExist(String name) {
            return false;
        }
        @Override
        public boolean doesIcExist(String ic) {
            return false;
        }
    }


    /**
     * A Model stub that always accept the appointment being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Appointment> appointmentsAdded = new ArrayList<>();
        private final Person amy = new Person(new IdentificationInformation(new Name("Amy"), new Nric("S1234567D")),
                new MedicalInformation());
        @Override
        public boolean doesPersonExist(String name) {
            return true;
        }

        @Override
        public Person findPersonByNric(Nric nric) {
            if (amy.getIdentificationInformation().getNric().equals(nric)) {
                return amy;
            }
            return null;
        }

        @Override
        public boolean doesIcExist(String ic) {
            return true;
        }

        @Override
        public List<Appointment> getConflictingAppointments(Appointment appointment) {
            ArrayList<Appointment> conflictingAppointments = new ArrayList<>();
            return conflictingAppointments;
        }

        @Override
        public void addAppointment(Appointment appointment) {
            appointmentsAdded.add(appointment);
        }
    }

    private class ModelStubWithConflictingAppointment extends ModelStubAcceptingPersonAdded {
        @Override
        public List<Appointment> getConflictingAppointments(Appointment appointment) {
            ArrayList<Appointment> conflictingAppointments = new ArrayList<>();
            conflictingAppointments.add(appointment); // Simulate a conflict
            return conflictingAppointments;
        }
    }
}



