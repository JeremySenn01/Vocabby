package ch.bbw.senn.Vocabby.controller;

import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class MainController implements Initializable {

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

	}

	public void initController(User user) {
		if (user.getSets() != null) {
			sets = (ObservableList<Set>) user.getSets();
		} else {
			sets = FXCollections.observableArrayList();
		}
		tUsername.setText(user.getUsername() + "'s Sets");
		lvSets.setItems((ObservableList<Set>) sets);
	}

	@FXML
	private void handleNewSet() {
		loader.loadNewSetDialog();
	}

	public static void addNewSet(Set newSet) {
		System.out.println("newSet :" + newSet);
		System.out.println("sets :" + sets);

		sets.add(newSet);
	}
}
