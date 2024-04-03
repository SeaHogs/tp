package vitalconnect.logic.parser;

import static vitalconnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_NRIC;

//import java.util.Arrays;
import java.util.stream.Stream;

import vitalconnect.logic.commands.FindAptCommand;
import vitalconnect.logic.parser.exceptions.ParseException;
//import vitalconnect.model.AptWithNricPredicate;
import vitalconnect.model.person.identificationinformation.Nric;

public class FindAptCommandParser implements Parser<FindAptCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindAptCommand
     * and returns a FindAptCommand object for execution.
     *
     * @param args The input arguments to be parsed.
     * @return A new FindAptCommand object.
     * @throws ParseException If the provided arguments do not conform to the expected format.
     */
    public FindAptCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NRIC);
        if (!arePrefixesPresent(argMultimap, PREFIX_NRIC) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAptCommand.MESSAGE_USAGE));
        }

        Nric nric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get());

        return new FindAptCommand(nric);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

