package vitalconnect.model;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import vitalconnect.commons.core.GuiSettings;
import vitalconnect.commons.core.index.Index;
import vitalconnect.model.person.Person;
import vitalconnect.model.person.contactinformation.ContactInformation;
import vitalconnect.model.person.identificationinformation.IdentificationInformation;
import vitalconnect.model.person.identificationinformation.Nric;
import vitalconnect.model.person.medicalinformation.MedicalInformation;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Person> PREDICATE_SHOW_ALL_CONTACT = Person::hasContactInformation;
    Predicate<Person> PREDICATE_SHOW_ALL_MEDICAL = Person::hasMedicalInformation;
    /**
     * Set current user Predicates.
     */
    void setCurrentPredicate(Predicate<Person> predicate);

    /**
     * Get current user Predicates.
     */
    Predicate<Person> getCurrentPredicate();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' clinic file path.
     */
    Path getClinicFilePath();

    /**
     * Sets the user prefs' clinic file path.
     */
    void setClinicFilePath(Path clinicFilePath);

    /**
     * Replaces clinic data with the data in {@code clinic}.
     */
    void setClinic(ReadOnlyClinic clinic);

    /** Returns the Clinic */
    ReadOnlyClinic getClinic();

    /** Returns a copy of the Clinic */
    ReadOnlyClinic getClinicCopy();

    /** Returns a copy of the Appointments */
    List<Appointment> getAppointmentsCopy();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the clinic.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the clinic.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the clinic.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the clinic.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the clinic.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    boolean doesPersonExist(String name);
    boolean doesIcExist(String nric);
    void addAppointment(Appointment appointment);
    void setAppointments(List<Appointment> appointments);

    /**
     * Checks if the given appointment conflicts with any existing appointments.
     *
     * @param appointment The appointment to check.
     * @return true if there is a conflict, false otherwise.
     */
    List<Appointment> getConflictingAppointments(Appointment appointment);
    List<Appointment> getConflictingAppointmentsForExistingApt(Index index, Appointment appointment);
    ObservableList<Appointment> getFilteredAppointmentList();
    //void updateFilteredAppointmentList(Predicate<Appointment> predicate);
    ObservableList<Appointment> getFoundAppointmentList();
    List<Appointment> findAppointmentsByNric(Nric nric);
    void deleteAppointment(Appointment appointment);
    void clearAppointments();
    Person findPersonByNric(IdentificationInformation nric);
    Person findPersonByNric(Nric nric);
    void updatePersonContactInformation(Nric nric, ContactInformation contactInformation);

    void updatePersonMedicalInformation(Nric nric, MedicalInformation medicalInformation);
    void updateAppointment(Index index, Appointment appointment);
}
