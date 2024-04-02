package vitalconnect.logic.parser;

import static java.util.Objects.requireNonNull;
import static vitalconnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_NAME;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_NRIC;

import java.util.stream.Stream;

import vitalconnect.logic.commands.EditCommand;
import vitalconnect.logic.parser.exceptions.ParseException;
import vitalconnect.model.person.identificationinformation.IdentificationInformation;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_NRIC);

        if (!arePrefixesPresent(argMultimap, PREFIX_NRIC)
        || !argMultimap.getPreamble().isEmpty()) {
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
    }
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_NRIC);

        if (argMultimap.getValue(PREFIX_NAME).isPresent() && argMultimap.getValue(PREFIX_NRIC).isPresent()) {
            IdentificationInformation info = new IdentificationInformation(
                    ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()),
                    ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get()));

            return new EditCommand(info);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
