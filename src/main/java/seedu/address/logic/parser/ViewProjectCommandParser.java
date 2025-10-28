package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ViewProjectCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewProjectCommand object
 */
public class ViewProjectCommandParser implements Parser<ViewProjectCommand> {
    @Override
    public ViewProjectCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewProjectCommand.MESSAGE_USAGE));
        }

        return new ViewProjectCommand(trimmedArgs);
    }
}
