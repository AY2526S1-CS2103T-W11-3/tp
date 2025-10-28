package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.project.Project;

/**
 * Panel containing the list of persons.
 * When a person is selected, triggers a callback to display their projects.
 */
public class PersonListPanel extends UiPart<Region> {

    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    private final ObservableList<Project> projectList;

    @FXML
    private ListView<Person> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given list and selection callback.
     *
     * @param personList The list of persons to display. Must not be null.
     * @param projectList The list of projects to display as tags in each contact. Must not be null
     */
    public PersonListPanel(ObservableList<Person> personList, ObservableList<Project> projectList) {
        super(FXML);
        requireNonNull(personList, "Person list cannot be null");
        requireNonNull(projectList, "Person list cannot be null");

        this.projectList = projectList;

        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                List<Project> joinedProjects = projectList.stream()
                        .filter(project -> project.hasMember(person)).toList();
                setGraphic(new PersonCard(person, joinedProjects, getIndex() + 1).getRoot());
            }
        }
    }
}
