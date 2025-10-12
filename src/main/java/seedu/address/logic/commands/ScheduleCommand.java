package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Changes the lesson of an existing person in the address book.
 */
public class ScheduleCommand extends Command {

    public static final String COMMAND_WORD = "schedule";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("Hello from schedule");
    }
}
