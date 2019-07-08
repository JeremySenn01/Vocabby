package ch.bbw.senn.Vocabby.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import ch.bbw.senn.Vocabby.FileManager;
import ch.bbw.senn.Vocabby.Proxy;
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
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MainController implements Initializable {

	@FXML
	private Pane pRoot;
	@FXML
	private Button btNewSet;
	@FXML
	private ListView<Set> lvSets;
	@FXML
	private Text tUsername;
	@FXML
	private Text tError;

	private static ObservableList<Set> sets;
	private ViewLoader loader;
	private Proxy proxy;
	private User user;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loader = new ViewLoader();
		proxy = new Proxy();
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
		this.user = user;
		tUsername.setText(user.getUsername() + "'s Sets");
		lvSets.setItems((ObservableList<Set>) sets);
	}

	@FXML
	private void handleNewSet() {
		loader.loadNewSetDialog(this.user);
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

			// Delete the Set from Database
			boolean deletedSet = proxy.deleteSet(selected);

			if (deletedSet) {
				sets.remove(selected);
			} else {
				tError.setText("Couldn't delete the Set :(");
			}

		}
	}

	@FXML
	public void handleWriteSet() throws IOException {
		Set selected = lvSets.getSelectionModel().getSelectedItem();
		if (selected != null) {
			Alert message = new Alert(AlertType.INFORMATION);
			message.setTitle("Vocabby");
			if (!FileManager.writeSetToFile(selected)) {
				message.setHeaderText("File Couldn't be created");
				message.setContentText("The Set '" + selected.getName() + "' couldn't be written into a file");
			} else {
				message.setHeaderText("File created");
				message.setContentText("The Set '" + selected.getName() + "' was successfully written into a file\n" + "Directory: '/downloads'");

			}
			message.show();
		}
	}
	
	//TODO: Finish implementing this method / feature
	@FXML
	public void handleImportSet() throws IOException {
		 FileChooser fileChooser = new FileChooser();
		 fileChooser.setTitle("Open Resource File");
		 fileChooser.getExtensionFilters().addAll(
		         new ExtensionFilter("Text Files", "*.txt"));
		 File selectedFile = fileChooser.showOpenDialog(tError.getScene().getWindow());
		 if (selectedFile != null) {
		   	FileManager.importSetFromTxtFile(selectedFile, user.getId());
		 }
	}
}
