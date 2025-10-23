package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.LeaveProjectCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new LeaveProjectCommand object
 */
public class LeaveProjectCommandParser implements Parser<LeaveProjectCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the LeaveProjectCommand
     * and returns an LeaveProjectCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public LeaveProjectCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_MEMBER);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_MEMBER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, LeaveProjectCommand.MESSAGE_USAGE));
        }

        String projectName = argMultimap.getValue(PREFIX_NAME).get().trim();
        if (projectName.isEmpty()) {
            throw new ParseException("Missing project name.\n" + LeaveProjectCommand.MESSAGE_USAGE);
        }

        List<String> memberStrings = argMultimap.getAllValues(PREFIX_MEMBER);
        if (memberStrings.isEmpty()) {
            throw new ParseException("At least one member index must be provided.\n"
                    + LeaveProjectCommand.MESSAGE_USAGE);
        }

        List<Index> memberIndexes = new ArrayList<>();
        for (String s : memberStrings) {
            memberIndexes.add(ParserUtil.parseIndex(s));
        }

        return new LeaveProjectCommand(projectName, memberIndexes);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
