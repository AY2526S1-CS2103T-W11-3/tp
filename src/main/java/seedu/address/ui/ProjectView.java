package seedu.address.ui;

import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.project.Project;

/**
 * Panel containing project details.
 */
public class ProjectView extends UiPart<Region> {
    private static final String FXML = "ProjectView.fxml";

    @FXML
    private Label name;

    @FXML
    private Label deadline;

    @FXML
    private Label priority;

    @FXML
    private Label members;

    /**
     * Constructs a {@code ProjectView}
     */
    public ProjectView() {
        super(FXML);
        showDefaultMessage();
    }

    /**
     * Placeholder message when no project selected yet.
     */
    public void showDefaultMessage() {
        deadline.setText("Enter 'view PROJECT_NAME' to view details");

        deadline.getStyleClass().add("project-text");
    }

    /**
     * Displays the details of the selected project.
     */
    public void showProject(Project project) {
        name.setText(project.getName());
        deadline.setText("Deadline: " + project.getDeadline().toString());
        priority.setText("Priority: " + project.getPriority().toString());

        String memberNames = project.getMembers().stream()
                .map(person -> person.getName().fullName) // adjust if your Person class has a different way to get name
                .collect(Collectors.joining(", "));
        members.setText("Members: " + memberNames);

        name.getStyleClass().add("project-text");
        deadline.getStyleClass().add("project-text");
        priority.getStyleClass().add("project-text");
        members.getStyleClass().add("project-text");
    }
}
