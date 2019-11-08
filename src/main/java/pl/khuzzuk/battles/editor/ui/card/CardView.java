package pl.khuzzuk.battles.editor.ui.card;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import pl.khuzzuk.battles.editor.api.Card;
import pl.khuzzuk.battles.editor.api.Nation;
import pl.khuzzuk.battles.editor.ui.DirectPane;
import pl.khuzzuk.battles.editor.ui.UIContext;
import pl.khuzzuk.battles.editor.ui.WithEffects;
import pl.khuzzuk.battles.editor.ui.WithText;

public class CardView extends DirectPane implements WithEffects, WithText {
    Rectangle backElement = new Rectangle(731, 1181);

    public CardView(UIContext ctx) {
        super(ctx);
        place(backElement, 0, 0);
    }

    public void refresh(Card card, Nation nation) {
        Paint backgroundPain = Color.BLACK;
        if (nation.getBackgroundImagePath() != null) {
            Image backgroundImage = new Image(ctx.getNationService().getBackgroundUrl(nation));
            backgroundPain = new ImagePattern(backgroundImage, 0, 0, 1, 1, true);
        }
        backElement.setFill(backgroundPain);
    }
}
