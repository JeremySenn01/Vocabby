package ch.bbw.senn.Vocabby.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;

import ch.bbw.senn.Vocabby.Proxy;
import ch.bbw.senn.Vocabby.User;
import ch.bbw.senn.Vocabby.ViewLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginController implements Initializable {

	@FXML
	private TextField tfUsername;
	@FXML
	private PasswordField tfPassword;
	@FXML
	private Button btLogin;
	@FXML
	private Button btRegister;
	@FXML
	private Text tError;

	private ViewLoader loader;
	private Proxy proxy;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.loader = new ViewLoader();
		this.proxy = new Proxy();
	}

	@FXML
	private void handleLogin() {

		String username = tfUsername.getText();
		String password = tfPassword.getText();

		// Search Database -----------
		Optional<User> foundUser = proxy.getUser(username, password);
		if (foundUser.isPresent()) {
			User user = foundUser.get();
			// Log in
			btLogin.getScene().getWindow().hide();
			loader.loadMainDialog(user);
		} else {
			tError.setText("Username or password incorrect");
		}

//		// TODO: Remove This
//		List<Set> sets = new ArrayList<>();
//		List<Term> terms = new ArrayList<>();
//
//		terms.add(new Term(UUID.randomUUID(), "Baum", "arbre"));
//		terms.add(new Term(UUID.randomUUID(), "Mensch", "personne"));
//		terms.add(new Term(UUID.randomUUID(), "Vogel", "l'oiseau"));
//		terms.add(new Term(UUID.randomUUID(), "Augen", "les yeux"));
//		terms.add(new Term(UUID.randomUUID(), "Fuss", "le pied"));
//
//		sets.add(new Set(UUID.randomUUID(), "Voci Teil 1", "Französisch", LocalDate.now(), terms));
//		sets.add(new Set(UUID.randomUUID(), "Voci Teil 2", "Random", LocalDate.now(), terms));
//		sets.add(new Set(UUID.randomUUID(), "Voci Teil 3", "Englisch", LocalDate.now(), terms));

	}

	@FXML
	private void handleRegister() {

		String username = tfUsername.getText();
		String password = tfPassword.getText();

		if (username.length() >= 2 && username.length() <= 30 && password.length() >= 2) {
			User newUser = new User(username, password, null, UUID.randomUUID());
			// Insert into Database
			boolean createdUser = proxy.createUser(newUser);
			if (createdUser) {
				// Log in
				btLogin.getScene().getWindow().hide();
				loader.loadMainDialog(newUser);
			} else {
				tError.setText("Couldn't create Profile");
			}
		}

		else {
			tError.setText("Username or Password not sufficient");
		}
	}

}
