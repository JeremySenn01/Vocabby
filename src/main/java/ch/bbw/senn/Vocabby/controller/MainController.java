package ch.bbw.senn.Vocabby.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import ch.bbw.senn.Vocabby.Set;
import ch.bbw.senn.Vocabby.SetRenderer;
import ch.bbw.senn.Vocabby.User;
import ch.bbw.senn.Vocabby.ViewLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class MainController implements Initializable {

	@FXML
	private Pane pRoot;
	@FXML
	private Button btNewSet;
	@FXML
	private ListView<Set> lvSets;
	@FXML
	private Text tUsername;

	private static ObservableList<Set> sets;
	private ViewLoader loader;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loader = new ViewLoader();

		lvSets.setCellFactory(new SetRenderer());

		lvSets.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent click) {

				if (click.getClickCount() == 2) {
					Set selected = lvSets.getSelectionModel().getSelectedItem();
					loader.loadStudyDialog(selected);
				}
			}
		});

		// Detect Key Pressed
		pRoot.setOnKeyPressed(new EventHandler<KeyEvent>() {
			// Handle Event
			public void handle(final KeyEvent keyEvent) {
				// Ctr + N
				if (keyEvent.getCode() == KeyCode.N && keyEvent.isControlDown()) {
					// Create new Note
					handleNewSet();
					// Prevent Key Event from doing anything else
					keyEvent.consume();
				}
				// Ctr + D
				if (keyEvent.getCode() == KeyCode.D && keyEvent.isControlDown()) {
					// Delete Note
					handleDeleteSet();
					// Prevent Key Event from doing anything else
					keyEvent.consume();
				}
			}
		});

	}

	public void initController(User user) {
		if (user.getSets() == null) {
			sets = FXCollections.observableArrayList();
		} else {
			sets = FXCollections.observableArrayList(user.getSets());
		}
		tUsername.setText(user.getUsername() + "'s Sets");
		lvSets.setItems((ObservableList<Set>) sets);
	}

	@FXML
	private void handleNewSet() {
		loader.loadNewSetDialog();
	}

	@FXML
	private void handleExit() {
		System.exit(0);
	}

	public static void addNewSet(Set newSet) {
		sets.add(newSet);
	}

	@FXML
	public void handleDeleteSet() {
		Set selected = lvSets.getSelectionModel().getSelectedItem();

		// Create confirmation Dialog
		Alert confirmation = new Alert(AlertType.CONFIRMATION);
		confirmation.setTitle("Confirm Action");
		confirmation.setHeaderText("Delete '" + selected.getName() + "'");
		confirmation.setContentText("Are you sure you want to do this? This decision is irreversible");

		Optional<ButtonType> result = confirmation.showAndWait();
		if (result.get() == ButtonType.OK) {
			sets.remove(selected);

			// Delete the Set from Database

			// Delete the Set from Database
		}
	}
}
