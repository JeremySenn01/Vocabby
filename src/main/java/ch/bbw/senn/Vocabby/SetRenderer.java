package ch.bbw.senn.Vocabby;

import java.time.format.DateTimeFormatter;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class SetRenderer implements Callback<ListView<Set>, ListCell<Set>> {

	@Override
	public ListCell<Set> call(ListView<Set> arg0) {
		return new MyListCell();
	}

	class MyListCell extends ListCell<Set> {
		@Override
		public void updateItem(Set set, boolean empty) {
			super.updateItem(set, empty);

			// Format Car Cell
			if (!empty) {
				Pane pane = new Pane();
				Label title = new Label(set.getName());
				Label theme = new Label(set.getTheme());
				Label date = new Label("created on " + set.getCreationDate().format(DateTimeFormatter.ISO_DATE));
				Label terms = new Label(set.getTerms().size() + " Terms");

				// Relocate Labels
				title.relocate(20, 0);
				theme.relocate(350, 0);
				terms.relocate(20, 30);
				date.relocate(350, 30);

				// Style Labels
				date.setStyle("-fx-font-size: 8pt ;");
				terms.setStyle("-fx-font-size: 8pt ;");


				//Add Children to Pane
				pane.getChildren().add(title);
				pane.getChildren().add(theme);
				pane.getChildren().add(date);
				pane.getChildren().add(terms);

				setGraphic(pane);

			} else {
				setGraphic(null);
			}
		}
	}

}
