package seedu.address.logic.parser;

import seedu.address.logic.commands.ViewProjectCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewProjectCommand object.
 * Expected format: view PROJECT_NAME
 */
public class ViewProjectCommandParser implements Parser<ViewProjectCommand> {

    @Override
    public ViewProjectCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(
                    "Invalid command format!\n%1$s", ViewProjectCommand.MESSAGE_USAGE));
        }

        return new ViewProjectCommand(trimmedArgs);
    }
}

