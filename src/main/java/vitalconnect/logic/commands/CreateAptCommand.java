package vitalconnect.logic.commands;

import static vitalconnect.logic.Messages.MESSAGE_PERSON_NOT_FOUND;
import static vitalconnect.logic.parser.CliSyntax.DURATION_UNIT;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import vitalconnect.logic.commands.exceptions.CommandException;
import vitalconnect.model.Appointment;
import vitalconnect.model.Model;
import vitalconnect.model.person.Person;
import vitalconnect.model.person.identificationinformation.Name;
import vitalconnect.model.person.identificationinformation.Nric;

/**
 * Represents a command to create an appointment for a patient in the address book.
 * This command schedules appointments by specifying the patient's NRIC
 * and the desired date and time for the appointment.
 */
public class CreateAptCommand extends Command {

    public static final String COMMAND_WORD = "adda";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds an appointment for a patient in the patient list.\n "
            + "Format: " + COMMAND_WORD + " ic/ NRIC s/ START TIME d/ DURATION\n"
            + "(One unit of duration represent 15 minutes.)\n"
            + "Example: " + COMMAND_WORD + " "
            + "ic/S1234567D time/ 02/02/2024 1330 d/2\n"
            + "It means creating an appointment for S1234567D start on 2024 Feb. 2 13:30 and end at 14:00.\n"
            + "Note: Ensure the date and time are in dd/MM/yyyy HHmm format and duration should be larger than 0.";

    private final Nric nric;
    private final LocalDateTime dateTime;
    private final LocalDateTime endDateTime;
    private String patientName = null;
    private final int duration;
    private final int unit = DURATION_UNIT;


    /**
     * Constructs a {@code CreateAptCommand} to schedule an appointment.
     *
     * @param nric The NRIC of the patient for whom the appointment is being created.
     * @param dateTime The date and time of the appointment, in DD/MM/YYYY HHMM format.
     * @param duration The time duration of the appointment.
     */
    public CreateAptCommand(Nric nric, LocalDateTime dateTime, int duration) {
        this.nric = nric;
        this.dateTime = dateTime;
        this.duration = duration;
        this.endDateTime = dateTime.plusMinutes(unit * duration);
    }


    /**
     * Executes the command to create an appointment in the address book.
     *
     * The method verifies the existence of a person with the specified NRIC in the address book.
     * If the person exists and the date and time format is valid, an appointment is scheduled.
     * If the person does not exist or the date and time are incorrectly formatted,
     * a {@code CommandException} is thrown.
     *
     * @param model The model of the address book in which the appointment is to be created.
     * @return A {@code CommandResult} object containing the success message upon
     *         successful creation of the appointment, and the type of command result.
     * @throws CommandException If the specified patient does not exist or if the
     *                          date and time string is in an incorrect format.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (dateTime.isBefore(LocalDateTime.now())) {
            throw new CommandException("Appointment time cannot be in the past.");
        }
        Person person = model.findPersonByNric(nric);
        if (person == null) {
            throw new CommandException(MESSAGE_PERSON_NOT_FOUND);
        }
        Name name = person.getIdentificationInformation().getName();
        this.patientName = name.toString();
        String patientIc = nric.toString();
        Appointment appointment = new Appointment(patientName, patientIc, dateTime, endDateTime, duration);
        List<Appointment> conflictingAppointments = model.getConflictingAppointments(appointment);
        if (!conflictingAppointments.isEmpty()) {
            String conflictMessage = buildConflictMessage(conflictingAppointments);
            throw new CommandException("Appointment time conflicts detected:\n" + conflictMessage);
        }
        model.addAppointment(appointment);
        return new CommandResult(String.format("Created an appointment successfully!\n"
                        + "Name: %s\nNRIC: %s\nStart time: %s\nEnd time: %s",
          patientName, patientIc, dateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy HH:mm")),
                endDateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy HH:mm"))),
          false, false, CommandResult.Type.SHOW_APPOINTMENTS);
    }

    /**
     * Builds a message listing all conflicting appointments.
     *
     * @param appointments List of conflicting appointments.
     * @return A string detailing the conflicting appointments.
     */
    private String buildConflictMessage(List<Appointment> appointments) {
        StringBuilder message = new StringBuilder();
        for (Appointment appointment : appointments) {
            message.append(String.format("Appointment with %s (%s) from %s to %s\n",
                    appointment.getPatientName(),
                    appointment.getPatientIc(),
                    appointment.getDateTime().format(DateTimeFormatter.ofPattern("d MMM yyyy HH:mm")),
                    appointment.getEndDateTime().format(DateTimeFormatter.ofPattern("d MMM yyyy HH:mm"))));
        }
        return message.toString();
    }
    /**
     * Returns the NRIC of the patient associated with this appointment.
     *
     * @return The patient's NRIC as a {@code String}.
     */
    public Nric getPatientIc() {
        return nric;
    }

    /**
     * Returns the name of the patient associated with this appointment.
     *
     * @return The patient's name as a {@code String}.
     */
    public String getPatientName() {
        return patientName;
    }
    /**
     * Returns the date and time of the appointment as a string.
     *
     * @return The date and time of the appointment in "dd/MM/yyyy HHmm" format.
     */
    public LocalDateTime getDateTimeStr() {
        return dateTime;
    }
    public LocalDateTime getEndTimeStr() {
        return endDateTime;
    }
    
    @Override
    public CommandResult undo(Model model) throws CommandException {
        return null;
    }
}
