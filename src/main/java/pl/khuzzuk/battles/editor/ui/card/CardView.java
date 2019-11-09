package pl.khuzzuk.battles.editor.ui.card;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import pl.khuzzuk.battles.editor.api.Card;
import pl.khuzzuk.battles.editor.api.Nation;
import pl.khuzzuk.battles.editor.nation.NationService;
import pl.khuzzuk.battles.editor.ui.DirectPane;
import pl.khuzzuk.battles.editor.ui.WithEffects;
import pl.khuzzuk.battles.editor.ui.WithText;

@RequiredArgsConstructor
@Component
public class CardView extends DirectPane implements WithEffects, WithText, InitializingBean {
    private final NationService nationService;

    Rectangle backElement = new Rectangle(731, 1181);

    @Override
    public void afterPropertiesSet() throws Exception {
        place(backElement, 0, 0);
    }

    public void refresh(Card card, Nation nation) {
        Paint backgroundPain = Color.BLACK;
        if (nation.getBackgroundImagePath() != null) {
            Image backgroundImage = new Image(nationService.getBackgroundUrl(nation));
            backgroundPain = new ImagePattern(backgroundImage, 0, 0, 1, 1, true);
        }
        backElement.setFill(backgroundPain);
    }
}
