package pl.khuzzuk.battles.editor.ui;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class HeaderView extends DirectPane implements WithEffects, WithText {

  private Rectangle outer;
  private Rectangle inner;
  private VBox textArea = new VBox(10);

  public HeaderView(int x, int y, double scale) {
    final double width = x * scale;
    final double height = y * scale;
    final double margin = 10 * scale;

    outer = new Rectangle(width, height);
    inner = new Rectangle(width - margin, height - margin);
    place(outer, 0, 0);
    place(inner, 5 * scale, 5 * scale);
    place(textArea, 5 * scale, -10 * scale);
    addDropShadow(outer);
    addInnerShadow(inner);
    textArea.setAlignment(Pos.CENTER);
    textArea.setPrefWidth(width - margin);
    textArea.setPrefHeight(height - margin);
  }

  public void refresh(String text, Paint paint) {
    outer.setFill(paint);
    inner.setFill(paint);
    placeText(textArea, text);
  }
}
