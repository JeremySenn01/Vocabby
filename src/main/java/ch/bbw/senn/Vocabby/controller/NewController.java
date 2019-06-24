package ch.bbw.senn.Vocabby.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

import ch.bbw.senn.Vocabby.Set;
import ch.bbw.senn.Vocabby.Term;
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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		terms = FXCollections.observableArrayList();
		lvTerms.setItems(terms);
	}

	@FXML
	private void handleCreateSet() {
		Set newSet = new Set(UUID.randomUUID(), tfTitle.getText(), tfTheme.getText(), LocalDate.now(), this.terms);
		MainController.addNewSet(newSet);
		btCreateSet.getScene().getWindow().hide();

	}
	
	@FXML
	private void handleAddTerm() {
		String original = tfOriginal.getText();
		String translated = tfTranslated.getText();
		Term newTerm = new Term(UUID.randomUUID(), original, translated);
		
		this.terms.add(newTerm);
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
