package vitalconnect.logic.commands;

import static vitalconnect.testutil.TypicalPersons.getTypicalClinic;

import java.util.ArrayList;

import vitalconnect.model.Model;
import vitalconnect.model.ModelManager;
import vitalconnect.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalClinic(), new UserPrefs(), new ArrayList<>());

}
