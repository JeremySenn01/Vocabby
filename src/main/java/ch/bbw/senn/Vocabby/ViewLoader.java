package ch.bbw.senn.Vocabby;

import java.io.IOException;

import ch.bbw.senn.Vocabby.controller.MainController;
import ch.bbw.senn.Vocabby.controller.NewController;
import ch.bbw.senn.Vocabby.controller.StudyController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ViewLoader {

	public void loadMainDialog(User user) {

		// Create FXMLLoader
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));

		// Create new Stage
		Stage stage = new Stage();

		// Try to load Scene
		try {
			stage.setScene(new Scene((Pane) loader.load()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// load controller
		MainController controller = loader.<MainController>getController();

		// Initialize Controller
		controller.initController(user);

		stage.show();

	}

	public void loadNewSetDialog(User user) {

		// Create FXMLLoader
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/New.fxml"));

		// Create new Stage
		Stage stage = new Stage();

		// Try to load Scene
		try {
			stage.setScene(new Scene((Pane) loader.load()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		NewController controller = loader.<NewController>getController();
		controller.initController(user);
		
		stage.show();
	}
	
	public void loadStudyDialog(Set set) {

		// Create FXMLLoader
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Study.fxml"));

		// Create new Stage
		Stage stage = new Stage();

		// Try to load Scene
		try {
			stage.setScene(new Scene((Pane) loader.load()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// load controller
		StudyController controller = loader.<StudyController>getController();

		// Initialize Controller
		controller.initController(set);

		stage.show();

	}

}
