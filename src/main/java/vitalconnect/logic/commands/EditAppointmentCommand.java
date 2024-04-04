package vitalconnect.logic.commands;

import static vitalconnect.logic.parser.CliSyntax.DURATION_UNIT;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_DURATION;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_TIME;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import vitalconnect.commons.core.index.Index;
import vitalconnect.logic.commands.exceptions.CommandException;
import vitalconnect.model.Appointment;
import vitalconnect.model.Model;

/**
 * Edits the details of an existing appointment in the clinic.
 */
public class EditAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "edita";
    public static final String MESSAGE_SUCCESS = "Appointment updated successfully: \n";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Edit details for an appointment.\n "
        + "Parameters: " + COMMAND_WORD + " INDEX (Index of appointment in the appointment list) "
        + "[" + PREFIX_TIME + "START TIME" + "]"
        + "[" + PREFIX_DURATION + "DURATION" + "] "
        + "(One unit of duration represent 15 minutes.)\n"
        + "Example: " + COMMAND_WORD + " "
        + "1 s/ 02/02/2024 1330 d/2\n"
        + "It means changing the appointment number 1 to 2024 Feb. 2 13:30 and end at 14:00.\n"
        + "Note: Ensure the date and time are in dd/MM/yyyy HHmm format and duration should be larger than 0.";

    private final Index index;
    private final LocalDateTime dateTime;
    private LocalDateTime endDateTime;
    private final int duration;
    private final int unit = DURATION_UNIT;

    private Appointment editedApt;
    private Appointment aptReference;

    /**
     * Constructs a {@code EditAppointmentCommand} to edit an appointment.
     */
    public EditAppointmentCommand(Index index, LocalDateTime dateTime, int duration) {
        this.index = index;
        this.dateTime = dateTime;
        this.duration = duration;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Appointment> lastShownList = model.getFilteredAppointmentList();

        if (lastShownList.size() < 1) {
            throw new CommandException("OOPS! The appointment list is empty.");
        }

        if (index.getOneBased() < 1 || index.getOneBased() > lastShownList.size()) {
            throw new CommandException("OOPS! The edit of the appointment failed as the index of "
              + "appointment is out of range.");
        }

        Appointment appointmentToEdit = lastShownList.get(index.getZeroBased()).getCopy();
        editedApt = appointmentToEdit.getCopy();
        if (dateTime != null && duration > 0) {
            // both updated
            checkAppointmentTime(dateTime);
            endDateTime = dateTime.plusMinutes(unit * duration);
            appointmentToEdit.setDateTime(dateTime);
            appointmentToEdit.setEndDateTime(endDateTime);
            appointmentToEdit.setDuration(duration);
        }

        if (dateTime != null && duration <= 0) {
            // only time updated
            checkAppointmentTime(dateTime);
            endDateTime = dateTime.plusMinutes(appointmentToEdit.getDuration() * unit);
            appointmentToEdit.setDateTime(dateTime);
            appointmentToEdit.setEndDateTime(endDateTime);
        }

        if (dateTime == null && duration > 0) {
            // only duration updated
            endDateTime = appointmentToEdit.getDateTime().plusMinutes(unit * duration);
            appointmentToEdit.setEndDateTime(endDateTime);
            appointmentToEdit.setDuration(duration);
        }

        String conflictMessage = isConflict(appointmentToEdit, model);
        if (!conflictMessage.equals("")) {
            throw new CommandException("Appointment time conflicts detected:\n" + conflictMessage);
        }
        aptReference = appointmentToEdit;
        model.updateAppointment(index, appointmentToEdit);
        return new CommandResult(MESSAGE_SUCCESS + "index: " + index.getOneBased()
          + "\nUpdated appointment detail:\n" + appointmentToEdit.toString(),
          false, false, CommandResult.Type.SHOW_APPOINTMENTS);
    }

    private void checkAppointmentTime(LocalDateTime dateTime) throws CommandException {
        if (dateTime.isBefore(LocalDateTime.now())) {
            throw new CommandException("Appointment time cannot be in the past.");
        }
    }

    private String isConflict(Appointment appointment, Model model) throws CommandException {
        List<Appointment> conflictingAppointments = model.getConflictingAppointmentsForExistingApt(index, appointment);
        String conflictMessage = "";
        if (!conflictingAppointments.isEmpty()) {
            conflictMessage = buildConflictMessage(conflictingAppointments);
            return conflictMessage;
        }
        return conflictMessage;
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

    @Override
    public CommandResult undo(Model model) throws CommandException {
        model.deleteAppointment(aptReference);
        model.addAppointment(editedApt);
        return new CommandResult(String.format("Undo the edit successfully"),
        false, false, CommandResult.Type.SHOW_APPOINTMENTS);
    }
}
