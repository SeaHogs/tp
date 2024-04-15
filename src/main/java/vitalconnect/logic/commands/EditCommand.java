package vitalconnect.logic.commands;

import static java.util.Objects.requireNonNull;
import static vitalconnect.logic.Messages.MESSAGE_PERSON_NOT_FOUND;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_NAME;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_NRIC;

import java.util.List;

import vitalconnect.commons.util.ToStringBuilder;
import vitalconnect.logic.Messages;
import vitalconnect.logic.commands.exceptions.CommandException;
import vitalconnect.model.Appointment;
import vitalconnect.model.Model;
import vitalconnect.model.person.Person;
import vitalconnect.model.person.identificationinformation.IdentificationInformation;

/**
 * Edits the details of an existing person in the clinic.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the identification details of a person. "
            + "Parameters:\n"
            + PREFIX_NRIC + "NRIC "
            + PREFIX_NAME + "NEW_NAME \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NRIC + "S1234567D "
            + PREFIX_NAME + "John Smith ";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the clinic.";

    private final IdentificationInformation info;
    private IdentificationInformation editedInfo;

    /**
     * @param info of the person in the filtered person list to edit
     */
    public EditCommand(IdentificationInformation info) {
        requireNonNull(info);

        this.info = new IdentificationInformation(info);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Person p = model.findPersonByNric(info);
        if (p == null) {
            throw new CommandException(MESSAGE_PERSON_NOT_FOUND);
        }

        Person editedPerson = p.copyPerson();
        editedInfo = p.getIdentificationInformation().getCopy();
        editedPerson.setIdentificationInformation(info);

        model.setPerson(p, editedPerson);

        // Appointments need to be manually updated
        List<Appointment> appointments = model.findAppointmentsByNric(info.getNric());
        for (Appointment appointment : appointments) {
            appointment.setPatientName(info.getName().toString());
        }
        model.updateFilteredPersonList(model.getCurrentPredicate());
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        EditCommand otherEditCommand = (EditCommand) other;
        return info.equals(otherEditCommand.info);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("info", info)
                .toString();
    }

    @Override
    public CommandResult undo(Model model) throws CommandException {
        EditCommand cmd = new EditCommand(editedInfo);
        return cmd.execute(model);
    }
}
