package vitalconnect.logic.commands;

import vitalconnect.logic.commands.exceptions.CommandException;
import vitalconnect.model.Model;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model) throws CommandException;

    /**
     * Executes the undo of the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the undo result for display or null if there is no undo
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult undo(Model model) throws CommandException;

}
