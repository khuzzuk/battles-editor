package pl.khuzzuk.battles.editor.ui;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class HeaderView extends DirectPane implements WithEffects, WithText {

  private Rectangle outer;
  private Rectangle inner;
  private VBox textArea = new VBox(10);

  public HeaderView(int x, int y) {
    outer = new Rectangle(x, y);
    inner = new Rectangle(x - 10, y - 10);
    place(outer, 0, 0);
    place(inner, 5, 5);
    place(textArea, 5, -10);
    addDropShadow(outer);
    addInnerShadow(inner);
    textArea.setAlignment(Pos.CENTER);
    textArea.setPrefWidth(x - 10);
    textArea.setPrefHeight(y - 10);
  }

  public void refresh(String text, Paint paint) {
    outer.setFill(paint);
    inner.setFill(paint);
    placeText(textArea, text);
  }
}
