package vitalconnect.logic.parser;

import static java.util.Objects.requireNonNull;
import static vitalconnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_DURATION;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_TIME;

import java.time.LocalDateTime;

import vitalconnect.commons.core.index.Index;
import vitalconnect.logic.commands.EditAppointmentCommand;
import vitalconnect.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditAppointmentCommand object
 */
public class EditAppointmentCommandParser implements Parser<EditAppointmentCommand> {
    @Override
    public EditAppointmentCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(userInput, PREFIX_TIME, PREFIX_DURATION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditAppointmentCommand.MESSAGE_USAGE), pe);
        }

        LocalDateTime dateTime = null;
        int duration = 0;
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TIME, PREFIX_DURATION);
        if (argMultimap.getValue(PREFIX_TIME).isPresent()) {
            dateTime = ParserUtil.parseTime(argMultimap.getValue(PREFIX_TIME).get());
        }

        if (argMultimap.getValue(PREFIX_DURATION).isPresent()) {
            duration = ParserUtil.parseDuration(argMultimap.getValue(PREFIX_DURATION).get());
        }

        if (!argMultimap.getValue(PREFIX_TIME).isPresent() && !argMultimap.getValue(PREFIX_DURATION).isPresent()) {
            throw new ParseException("At least one field to edit must be provided.");
        }

        return new EditAppointmentCommand(index, dateTime, duration);
    }
}
