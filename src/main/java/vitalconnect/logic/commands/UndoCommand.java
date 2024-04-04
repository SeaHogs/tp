package vitalconnect.logic.commands;

import static java.util.Objects.requireNonNull;

import vitalconnect.logic.CommandHistoryManager;
import vitalconnect.logic.commands.exceptions.CommandException;
import vitalconnect.model.Model;

/**
 * Undo the latest command in the Command History
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_SUCCESS = "Undo executed successfully";
    public static final String MESSAGE_NOTHING_TO_UNDO = "Unable to undo as there is no commands in the history";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        CommandResult result = null;
        while (result == null) {
            Command command = CommandHistoryManager.getInstance().popCommandToHistory();
            if (command == null) {
                throw new CommandException(MESSAGE_NOTHING_TO_UNDO);
            }

            try {
                result = command.undo(model);
            } catch (Exception e) {
                throw e;
            }
        }

        return new CommandResult(MESSAGE_SUCCESS, result.isShowHelp(), result.isExit(), result.getType());
    }

    @Override
    public CommandResult undo(Model model) throws CommandException {
        return null;
    }
}
