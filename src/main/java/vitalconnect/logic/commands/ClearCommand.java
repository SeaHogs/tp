package vitalconnect.logic.commands;

import static java.util.Objects.requireNonNull;

import vitalconnect.logic.commands.exceptions.CommandException;
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

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        deletedClinic = model.getClinicCopy();
        model.setClinic(new Clinic());
        return new CommandResult(MESSAGE_SUCCESS);
    }
    
    @Override
    public CommandResult undo(Model model) throws CommandException {
        model.setClinic(deletedClinic);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
