package pl.khuzzuk.battles.editor.ui.card;

import javafx.scene.shape.Rectangle;
import pl.khuzzuk.battles.editor.api.Card;
import pl.khuzzuk.battles.editor.ui.DirectPane;
import pl.khuzzuk.battles.editor.ui.UIContext;

public class CardView extends DirectPane {
    Rectangle backElement = new Rectangle(731, 1181);

    public CardView(UIContext uiContext) {
        super(uiContext);
    }

    public void refresh(Card card) {

    }
}
