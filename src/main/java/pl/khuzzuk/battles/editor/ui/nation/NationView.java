package pl.khuzzuk.battles.editor.ui.nation;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import pl.khuzzuk.battles.editor.nation.Nation;
import pl.khuzzuk.battles.editor.nation.NationService;
import pl.khuzzuk.battles.editor.ui.DirectPane;
import pl.khuzzuk.battles.editor.ui.HeaderView;
import pl.khuzzuk.battles.editor.ui.WithEffects;

@RequiredArgsConstructor
@Component
class NationView extends DirectPane implements WithEffects, InitializingBean {
    private final NationService nationService;

    private HeaderView headerView = new HeaderView(260, 70, 1);
    private Rectangle backElement = new Rectangle(731, 1181);
    private Rectangle emblemBorderElement = new Rectangle(260, 260);
    private Rectangle emblemElement = new Rectangle(250, 250);

    @Override
    public void afterPropertiesSet() {
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
            Image background = new Image(nationService.getBackgroundUrl(nation));
            backgroundPattern = new ImagePattern(background, 0, 0, 1, 1, true);
        }
        backElement.setFill(backgroundPattern);
        emblemBorderElement.setFill(backgroundPattern);
        headerView.refresh("Battles", backgroundPattern);

        Paint emblemPattern = Color.BLACK;
        if (nation.getEmblemImagePath() != null) {
            emblemPattern = new ImagePattern(new Image(nationService.getEmblemUrl(nation)), 0, 0, 1, 1, true);
        }
        emblemElement.setFill(emblemPattern);
    }
}
