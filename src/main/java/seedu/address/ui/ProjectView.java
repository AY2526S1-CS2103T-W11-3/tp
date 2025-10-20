package seedu.address.ui;

import javafx.scene.control.Alert;
import seedu.address.model.project.Project;

/**
 * Class that handles the display of project details.
 */
public class ProjectView {
    /**
     * Creates a pop-up with the given project's details.
     */
    public void showProject(Project project) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Project Details");
        alert.setHeaderText(project.getName());
        String displayText = "Priority: " + project.getPriority().toString()
                + "\nDeadline: " + project.getDeadline();
        alert.setContentText(displayText);
        alert.showAndWait();
    }
}
