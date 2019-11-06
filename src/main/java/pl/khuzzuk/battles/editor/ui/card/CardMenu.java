package pl.khuzzuk.battles.editor.ui.card;

import javafx.scene.control.Button;
import lombok.RequiredArgsConstructor;
import pl.khuzzuk.battles.editor.api.Card;
import pl.khuzzuk.battles.editor.ui.ContentPane;
import pl.khuzzuk.battles.editor.ui.DirectPane;

@RequiredArgsConstructor
public class CardMenu extends DirectPane {
    private final ContentPane pane;
    private DirectPane toReturn;
    private Card card;

    public void refresh(DirectPane toReturn, Card card) {
        this.toReturn = toReturn;
        this.card = card;

        pane.getChildren().clear();
        pane.getChildren().add(this);
        getChildren().clear();

        Button back = new Button("Back");
        back.setOnAction(action -> returnToMenu());
        place(back, 10, 10);
    }

    private void returnToMenu() {
        pane.getChildren().clear();
        pane.getChildren().add(toReturn);
    }
}
