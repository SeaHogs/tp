package vitalconnect.logic.parser;

import static vitalconnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static vitalconnect.logic.parser.CommandParserTestUtil.assertParseFailure;
import static vitalconnect.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import vitalconnect.logic.commands.EditContactCommand;
import vitalconnect.model.person.contactinformation.Address;
import vitalconnect.model.person.contactinformation.Email;
import vitalconnect.model.person.contactinformation.Phone;
import vitalconnect.model.person.identificationinformation.Nric;

public class EditContactCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditContactCommand.MESSAGE_USAGE);
    private EditContactCommandParser parser = new EditContactCommandParser();

    private Nric validNricAlreadyInModel = new Nric("S1234567D");
    private Nric validNricNotInModel = new Nric("S8380222G");
    private Email validEmail = new Email("email@test.com");
    private Phone validPhone = new Phone("12345678");
    private Address validAddress = new Address("address");


    @Test
    public void parse_missingParts_failure() {
        // no nric specified
        String userInput = " p/" + validPhone.value + " e/" + validEmail.value + " a/" + validAddress.value;
        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);

        // empty input
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = " " + "ic/" + validNricAlreadyInModel.nric
            + " e/" + validEmail.value + " p/" + validPhone.value + " a/" + validAddress.value;
        EditContactCommand expectedCommand =
            new EditContactCommand(validNricAlreadyInModel, validEmail, validPhone, validAddress);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
