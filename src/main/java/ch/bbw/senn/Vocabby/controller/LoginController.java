package ch.bbw.senn.Vocabby.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

import ch.bbw.senn.Vocabby.Set;
import ch.bbw.senn.Vocabby.Term;
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
		
		//TODO: Remove This
		List<Set> sets = new ArrayList<>();
		List<Term> terms = new ArrayList<>();
		
		terms.add(new Term(UUID.randomUUID(), "Baum", "arbre"));
		terms.add(new Term(UUID.randomUUID(),"Mensch", "personne"));
		terms.add(new Term(UUID.randomUUID(),"Vogel", "l'oiseau"));
		terms.add(new Term(UUID.randomUUID(),"Augen", "les yeux"));
		terms.add(new Term(UUID.randomUUID(),"Fuss", "le pied"));
		
		sets.add(new Set(UUID.randomUUID(),"Voci Teil 1", "Französisch", LocalDate.now(), terms));
		sets.add(new Set(UUID.randomUUID(),"Voci Teil 2", "Random", LocalDate.now(), terms));
		sets.add(new Set(UUID.randomUUID(),"Voci Teil 3", "Englisch", LocalDate.now(), terms));

		loader.loadMainDialog(new User(username, password, sets));
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
			loader.loadMainDialog(new User(username, password, null));
		}
		
		else {
			tError.setText("Username or Password not sufficient");
		}
	}

}
