package ch.bbw.senn.Vocabby.controller;

import java.net.URL;
import java.util.ResourceBundle;

import ch.bbw.senn.Vocabby.User;
import ch.bbw.senn.Vocabby.ViewLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginController implements Initializable {

	@FXML
	private TextField tfUsername;
	@FXML
	private TextField tfPassword;
	@FXML
	private Button btLogin;
	@FXML
	private Button btRegister;
	@FXML
	private Text tError;

	private ViewLoader loader;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.loader = new ViewLoader();
	}

	@FXML
	private void handleLogin() {

		String username = tfUsername.getText();
		String password = tfPassword.getText();

		// Search Database -----------

		// ---------------------------
		
		// Log in
		btLogin.getScene().getWindow().hide();
		this.loader.loadMainDialog(new User(username, password, null));
	}

	@FXML
	private void handleRegister() {

		String username = tfUsername.getText();
		String password = tfPassword.getText();

		if (username.length() >= 2 && username.length() <= 30 && password.length() >= 2) {

			// Insert into Database -----------

			// --------------------------------

			// Log in
			btLogin.getScene().getWindow().hide();
			this.loader.loadMainDialog(new User(username, password, null));
		}
		
		else {
			tError.setText("Username or Password not sufficient");
		}
	}

}
