package vitalconnect.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import vitalconnect.commons.core.index.Index;
import vitalconnect.commons.util.ToStringBuilder;
import vitalconnect.logic.Messages;
import vitalconnect.logic.commands.exceptions.CommandException;
import vitalconnect.model.Appointment;
import vitalconnect.model.Model;
import vitalconnect.model.ReadOnlyClinic;
import vitalconnect.model.person.Person;
/**
 * Deletes a person identified using it's displayed index from the clinic.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer within the patient list index range)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS =
            "Deleted Person: %1$s, and this patient's appointments.";

    private final Index targetIndex;
    private Person deletedPerson;
    private ReadOnlyClinic deletedClinic;
    private List<Appointment> deletedAppointments;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        
        deletedClinic = model.getClinicCopy();
        deletedAppointments = model.getAppointmentsCopy();

        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
        deletedPerson = personToDelete.copyPerson();
        model.deletePerson(personToDelete);

        List<Appointment> aptToDelete =
                model.findAppointmentsByNric(personToDelete.getIdentificationInformation().getNric());
        for (Appointment apt: aptToDelete) {
            model.deleteAppointment(apt);
        }
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return targetIndex.equals(otherDeleteCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }

    @Override
    public CommandResult undo(Model model) throws CommandException {
        model.setClinic(deletedClinic);
        model.setAppointments(deletedAppointments);
        return new CommandResult(String.format("Undo the delete successfully"),
        false, false, CommandResult.Type.SHOW_PERSONS);
    }
}
