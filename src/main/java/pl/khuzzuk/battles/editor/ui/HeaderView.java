package pl.khuzzuk.battles.editor.ui;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class HeaderView extends DirectPane implements WithEffects, WithText {
    private Rectangle outer = new Rectangle(260, 70);
    private Rectangle inner = new Rectangle(250, 60);
    private VBox textArea = new VBox(10);

    public HeaderView(UIContext ctx) {
        super(ctx);
        place(outer, 0, 0);
        place(inner, 5, 5);
        place(textArea, 5, -10);
        addDropShadow(outer);
        addInnerShadow(inner);
        textArea.setAlignment(Pos.TOP_CENTER);
        textArea.setPrefWidth(250);
        textArea.setPrefHeight(60);
    }

    public void refresh(String text, Paint paint) {
        outer.setFill(paint);
        inner.setFill(paint);
        placeText(textArea, text);
    }
}
