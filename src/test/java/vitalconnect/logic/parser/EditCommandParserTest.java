package vitalconnect.logic.parser;

import static vitalconnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static vitalconnect.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static vitalconnect.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_NAME;
import static vitalconnect.logic.parser.CliSyntax.PREFIX_NRIC;
import static vitalconnect.logic.parser.CommandParserTestUtil.assertParseFailure;
import static vitalconnect.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import vitalconnect.logic.commands.EditCommand;
import vitalconnect.model.person.identificationinformation.IdentificationInformation;

public class EditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no nric specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no name specified
        assertParseFailure(parser, VALID_NRIC_AMY, MESSAGE_INVALID_FORMAT);

        // no nric and no name specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = " " + PREFIX_NRIC + VALID_NRIC_AMY + " " + PREFIX_NAME + VALID_NAME_AMY;

        EditCommand expectedCommand = new EditCommand(new IdentificationInformation(VALID_NAME_AMY, VALID_NRIC_AMY));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
