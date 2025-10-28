package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Project;

/**
 * Displays detailed information for a specific project.
 */
public class ViewProjectCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays details for the specified project.\n"
            + "Parameters: PROJECT_NAME\n"
            + "Example: " + COMMAND_WORD + " Alpha";

    public static final String MESSAGE_PROJECT_NOT_FOUND = "No project found with name: %s";

    private final String projectName;

    /**
     * @param projectName of the project to be displayed.
     */
    public ViewProjectCommand(String projectName) {
        requireNonNull(projectName);
        this.projectName = projectName.trim();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Project project = model.getFilteredProjectList().stream()
                .filter(p -> p.getName().equalsIgnoreCase(projectName))
                .findFirst()
                .orElse(null);

        if (project == null) {
            throw new CommandException(String.format(MESSAGE_PROJECT_NOT_FOUND, projectName));
        }

        return new CommandResult(
                String.format("Viewing project: %s", project.getName()),
                false, false, project);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ViewProjectCommand
                && projectName.equalsIgnoreCase(((ViewProjectCommand) other).projectName));
    }
}
