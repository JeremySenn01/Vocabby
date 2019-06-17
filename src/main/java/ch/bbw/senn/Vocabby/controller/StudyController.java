package ch.bbw.senn.Vocabby.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import ch.bbw.senn.Vocabby.Set;
import ch.bbw.senn.Vocabby.Term;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;

public class StudyController implements Initializable {

	@FXML
	private Button btNext;
	@FXML
	private Button btPrevious;
	@FXML
	private Button btFlipCard;
	@FXML
	private Button btFlipAll;
	@FXML
	private Button btCancel;
	@FXML
	private Label lbTerm;
	@FXML
	private Text tTheme;
	@FXML
	private Text tTitle;
	@FXML
	private Text tDate;
	@FXML
	private Label lbProgress;
	@FXML
	private ProgressBar pbProgress;

	private List<Term> terms;
	private boolean showOriginalFirst;
	private boolean showCurrentOriginal;
	private Term currentTerm;
	private int progress;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		showOriginalFirst = true;
		showCurrentOriginal = true;
	}

	public void initController(Set set) {
		if (set.getTerms() != null) {

			this.terms = set.getTerms();
			currentTerm = this.terms.get(0);
			tDate.setText(set.getCreationDate().toString());
			tTitle.setText(set.getName());
			tTheme.setText(set.getTheme());

			this.setProgress();
		}
	}
	
	private void showCardText() {
		if (showCurrentOriginal) {
			lbTerm.setText(currentTerm.getOriginal());
		} else {
			lbTerm.setText(currentTerm.getTranslated());
		}
	}

	private void setProgress() {
		int currentIndex = terms.indexOf(this.currentTerm) + 1;
		this.progress = (100 / terms.size()) * currentIndex;
		if (currentIndex != terms.size()) {
			lbProgress.setText(this.progress + "%");
			pbProgress.setProgress((double) this.progress / 100);
		} else {
			lbProgress.setText("100%");
			pbProgress.setProgress(1.0);
		}
	}


	@FXML
	private void handlePrevious() {
		int currentIndex = terms.indexOf(this.currentTerm);
		if (currentIndex >= 1) {
			this.currentTerm = terms.get(currentIndex - 1);
		} else if (currentIndex == 0) {
			this.currentTerm = terms.get(terms.size() - 1);
		}
		
		if (showOriginalFirst) {
			showCurrentOriginal = true;
		}
		else {
			showCurrentOriginal = false;
		}
		this.showCardText();
		this.setProgress();
	}

	@FXML
	private void handleNext() {
		int currentIndex = terms.indexOf(this.currentTerm);
		if (currentIndex < terms.size() - 1) {
			this.currentTerm = terms.get(currentIndex + 1);
		} else if (currentIndex == terms.size() - 1) {
			this.currentTerm = terms.get(0);
		}
		if (showOriginalFirst) {
			showCurrentOriginal = true;
		}
		else {
			showCurrentOriginal = false;
		}
		this.showCardText();
		this.setProgress();
	}

	@FXML
	private void handleFlipCurrent() {
		this.showCurrentOriginal = !this.showCurrentOriginal;
		this.showCardText();
	}

	@FXML
	private void handleFlipAll() {
		this.showOriginalFirst = !this.showOriginalFirst;
		this.showCurrentOriginal = !this.showCurrentOriginal;
		this.showCardText();
	}

	@FXML
	private void handleCancel() {
		this.btCancel.getScene().getWindow().hide();
	}

}
