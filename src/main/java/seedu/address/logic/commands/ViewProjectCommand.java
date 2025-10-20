package seedu.address.logic.commands;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Project;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

/**
 * Displays the details of the project.
 */
public class ViewProjectCommand extends Command{

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": View the details of a project.\n"
            + "Parameters: "
            + "NAME\n"
            + "Example: " + COMMAND_WORD + " "
            + "IndiDex v1.3";

    public static final String MESSAGE_PROJECT_NOT_FOUND = "Project '%s' not found!";
    public static final String MESSAGE_VIEW_SUCCESS = "Viewing project: %s";

    private final String projectName;

    public ViewProjectCommand(String projectName) {
        requireNonNull(projectName);
        this.projectName = projectName;
    }

    @Override
    public CommandResult execute (Model model) throws CommandException {
        requireNonNull(model);

        Project project = model.findProjectByName(projectName);
        if (project == null) {
            throw new CommandException(String.format(MESSAGE_PROJECT_NOT_FOUND, projectName));
        }

        return new CommandResult(String.format(MESSAGE_VIEW_SUCCESS, projectName), project);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }
}
