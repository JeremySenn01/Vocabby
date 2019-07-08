package ch.bbw.senn.Vocabby.controller;

import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.UUID;

import ch.bbw.senn.Vocabby.Proxy;
import ch.bbw.senn.Vocabby.Set;
import ch.bbw.senn.Vocabby.Term;
import ch.bbw.senn.Vocabby.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class NewController implements Initializable {

	@FXML
	private Button btCancel;
	@FXML
	private Button btAddTerm;
	@FXML
	private Button btRemoveTerm;
	@FXML
	private Button btCreateSet;
	@FXML
	private TextField tfTitle;
	@FXML
	private TextField tfTheme;
	@FXML
	private TextField tfOriginal;
	@FXML
	private TextField tfTranslated;
	@FXML
	private ListView<Term> lvTerms;
	@FXML
	private Text tError;

	private ObservableList<Term> terms;
	private Proxy proxy;
	private User user;
	private UUID setId;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		terms = FXCollections.observableArrayList();
		lvTerms.setItems(terms);
		proxy = new Proxy();
		Instant start = Instant.now();
		setId = UUID.randomUUID();
		Instant stop = Instant.now();
		System.out.println("duration : " + Duration.between(start, stop));
	}

	public void initController(User user) {
		this.user = user;
	}

	@FXML
	private void handleCreateSet() {
		Set newSet = new Set(this.setId, this.user.getId(), tfTitle.getText(), tfTheme.getText(), LocalDate.now(), this.terms);
		boolean created = proxy.createSet(newSet, this.user);

		if (created) {
			MainController.addNewSet(newSet);
			btCreateSet.getScene().getWindow().hide();
		} else {
			tError.setText("Couldn't save the Set :(");
		}
	}

	@FXML
	private void handleAddTerm() {
		String original = tfOriginal.getText();
		String translated = tfTranslated.getText();
		Term newTerm = new Term(UUID.randomUUID(), this.setId, original, translated);

		this.terms.add(newTerm);
		tfOriginal.setText("");
		tfOriginal.setText("");
	}

	@FXML
	private void handleRemoveTerm() {
		Term selected = lvTerms.getSelectionModel().getSelectedItem();
		this.terms.remove(selected);
	}

	@FXML
	private void handleCancel() {
		btCancel.getScene().getWindow().hide();
	}

}
