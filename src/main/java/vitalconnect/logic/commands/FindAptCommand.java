package vitalconnect.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

//import vitalconnect.logic.Messages;
//import vitalconnect.model.AptWithNricPredicate;
import vitalconnect.logic.commands.exceptions.CommandException;
import vitalconnect.model.Appointment;
import vitalconnect.model.Model;
import vitalconnect.model.person.identificationinformation.Nric;


/**
 * Finds and lists all appointments in VitalConnect whose NRIC matches the argument keyword.
 * Keyword matching is case-sensitive.
 */
public class FindAptCommand extends Command {

    public static final String COMMAND_WORD = "finda";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds and lists all appointments for a patient in the patient list.\n"
            + "Format: " + COMMAND_WORD + " ic/NRIC\n"
            + "Example: " + COMMAND_WORD + " ic/S1234567D";

    private final Nric nric;

    public FindAptCommand(Nric nric) {
        this.nric = nric;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String result = "";

        if (!model.doesIcExist(nric.toString())) {
            throw new CommandException("OOPS! The patient does not exist in the patient list.");
        }

        List<Appointment> appointments = model.findAppointmentsByNric(nric);
        if (appointments == null) {
            result = "The patient does not have an appointment.";
        }
        // Assuming you have a method to format the appointments list into a string
        result = formatAppointmentsList(appointments);
        return new CommandResult(String.format("Here is the appointment for the patient: \n" + "%s\n"
                + "Notice: You cannot delete an appointment by using the index of this list, you should delete an "
                + "appointment by providing its index in the list of all appointment.", result),
                false, false, CommandResult.Type.SHOW_FOUNDAPT);
    }

    private String formatAppointmentsList(List<Appointment> appointments) {
        StringBuilder sb = new StringBuilder();
        for (Appointment appointment : appointments) {
            sb.append(appointment.toString()).append("\n");
        }
        return sb.toString();
    }
}

