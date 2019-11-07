package pl.khuzzuk.battles.editor.ui.card;

import javafx.scene.control.Button;
import pl.khuzzuk.battles.editor.api.Card;
import pl.khuzzuk.battles.editor.ui.ContentPane;
import pl.khuzzuk.battles.editor.ui.DirectPane;
import pl.khuzzuk.battles.editor.ui.UIContext;

public class CardMenu extends DirectPane {
    private Button back = new Button("Back");

    private Card card;

    public CardMenu(UIContext uiContext) {
        super(uiContext);
        back.setOnAction(action -> toMainMenu());
        place(back, 10, 10);
    }

    public void refresh(Card card) {
        super.refresh();
        this.card = card;
        getChildren().clear();
    }
}
