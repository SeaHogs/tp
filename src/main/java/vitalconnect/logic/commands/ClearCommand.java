package vitalconnect.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import vitalconnect.logic.commands.exceptions.CommandException;
import vitalconnect.model.Appointment;
import vitalconnect.model.Clinic;
import vitalconnect.model.Model;
import vitalconnect.model.ReadOnlyClinic;

/**
 * Clears the clinic.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Clinic has been cleared!";

    private ReadOnlyClinic deletedClinic;
    private List<Appointment> deletedAppointments;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        deletedClinic = model.getClinicCopy();
        deletedAppointments = model.getAppointmentsCopy();
        model.setClinic(new Clinic());
        model.setAppointments(new ArrayList<Appointment>());
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public CommandResult undo(Model model) throws CommandException {
        model.setClinic(deletedClinic);
        model.setAppointments(deletedAppointments);
        return new CommandResult(String.format("Undo the clear successfully"),
        false, false, CommandResult.Type.SHOW_PERSONS);
    }
}
