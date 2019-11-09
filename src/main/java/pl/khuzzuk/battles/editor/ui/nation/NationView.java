package pl.khuzzuk.battles.editor.ui.nation;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.khuzzuk.battles.editor.api.Nation;
import pl.khuzzuk.battles.editor.ui.DirectPane;
import pl.khuzzuk.battles.editor.ui.HeaderView;
import pl.khuzzuk.battles.editor.ui.WithEffects;

@Component
class NationView extends DirectPane implements WithEffects {
    private Rectangle backElement = new Rectangle(731, 1181);
    private Rectangle emblemBorderElement = new Rectangle(260, 260);
    private Rectangle emblemElement = new Rectangle(250, 250);
    @Autowired
    private HeaderView headerView;

    NationView() {
        headerView = new HeaderView(ctx);

        place(backElement, 0, 0);
        backElement.setStrokeWidth(0);

        place(headerView, 235, 200);

        place(emblemBorderElement, 235, 295);
        emblemBorderElement.setStrokeWidth(0);
        emblemBorderElement.setArcHeight(25);
        emblemBorderElement.setArcWidth(25);
        addDropShadow(emblemBorderElement);

        place(emblemElement, 240, 300);
        emblemElement.setStrokeWidth(0);
        emblemElement.setArcHeight(20);
        emblemElement.setArcWidth(20);
        addInnerShadow(emblemElement);
    }

    void refresh(Nation nation) {
        Paint backgroundPattern = Color.BLACK;
        if (nation.getBackgroundImagePath() != null) {
            Image background = new Image(ctx.getNationService().getBackgroundUrl(nation));
            backgroundPattern = new ImagePattern(background, 0, 0, 1, 1, true);
        }
        backElement.setFill(backgroundPattern);
        emblemBorderElement.setFill(backgroundPattern);
        headerView.refresh("Battles", backgroundPattern);

        Paint emblemPattern = Color.BLACK;
        if (nation.getEmblemImagePath() != null) {
            emblemPattern = new ImagePattern(new Image(ctx.getNationService().getEmblemUrl(nation)), 0, 0, 1, 1, true);
        }
        emblemElement.setFill(emblemPattern);

    }
}
