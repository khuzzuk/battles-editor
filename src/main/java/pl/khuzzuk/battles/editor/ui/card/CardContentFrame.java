package pl.khuzzuk.battles.editor.ui.card;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.scene.shape.Rectangle;
import pl.khuzzuk.battles.editor.ui.DirectPane;
import pl.khuzzuk.battles.editor.ui.Hexagonal;
import pl.khuzzuk.battles.editor.ui.WithEffects;

public class CardContentFrame extends DirectPane implements Hexagonal, WithEffects {

  private final int hexR;
  private double frameScale;

  private Path outer = new Path();
  private Path inner = new Path();
  private Path image = new Path();
  private ObservableList<PathElement> oE = outer.getElements();
  private ObservableList<PathElement> iE = inner.getElements();
  private ObservableList<PathElement> imE = image.getElements();

  private AnchorPane imageContainer = new AnchorPane();
  private ImageView imageView = new ImageView();

  CardContentFrame(int hexR) {
    this.hexR = hexR;
    frameScale = hexR / 8d;

    MoveTo startingPoint = getStartingPoint(getCol(4, hexR), getRow(1, hexR), hexR, 5);
    MoveTo innerStartingPoint = new MoveTo(translateX(startingPoint.getX(), 5),
        translateY(startingPoint.getY(), 5));

    oE.add(startingPoint);
    iE.add(innerStartingPoint);
    imE.add(innerStartingPoint);

    drawWith(1, 4, new int[]{4, 3, 2});
    drawWith(1, 6, new int[]{3, 2});
    drawWith(1, 8, new int[]{3, 2});
    drawWith(1, 10, new int[]{3, 2, 1});
    drawWith(2, 11, new int[]{2, 1});
    drawWith(3, 12, new int[]{2, 1, 0});
    drawWith(4, 11, new int[]{1});
    drawWith(5, 12, new int[]{2, 1, 0});
    drawWith(6, 11, new int[]{1});
    drawWith(7, 12, new int[]{2, 1, 0});
    drawWith(8, 11, new int[]{1, 0, 5});
    drawWith(8, 9, new int[]{0, 5});
    drawWith(8, 7, new int[]{0, 5});
    drawWith(8, 5, new int[]{0, 5});
    drawWith(8, 3, new int[]{0, 5, 4});
    drawWith(7, 2, new int[]{5, 4, 3});
    drawWith(6, 3, new int[]{4});
    drawWith(5, 2, new int[]{5, 4, 3});
    drawWith(4, 3, new int[]{4});
    drawWith(3, 2, new int[]{5, 4, 3});
    drawWith(2, 3, new int[]{4, 3});

    oE.add(new ClosePath());
    iE.add(new ClosePath());
    imE.add(new ClosePath());

    place(outer, 0, 0);
    place(inner, frameScale, frameScale);
    //place(image, frameScale, frameScale);
    image.setFill(Color.BLACK);
    imageContainer.setClip(image);
    place(imageContainer, -frameScale * 7 - 1, -frameScale * 5);

    addDropShadow(outer);
    addInnerShadow(inner);
    //addInnerShadow(image);
    inner.setStrokeWidth(0);
    outer.setStrokeWidth(0);
    //image.setStrokeWidth(0);
  }

  void refresh(Paint background) {
    outer.setFill(background);
    inner.setFill(background);
  }
  void refresh(Paint background, Image image, double x, double y, double w, double h) {
    outer.setFill(background);
    inner.setFill(background);
    imageContainer.getChildren().clear();
    imageView.setImage(image);
    imageView.setFitWidth(w);
    imageView.setFitHeight(h);
    AnchorPane.setLeftAnchor(imageView, x);
    AnchorPane.setTopAnchor(imageView, y);
    imageContainer.getChildren().add(imageView);
  }

  private void drawWith(int row, int col, int[] points) {
    LineTo[] drawings = drawLines(getCol(col, hexR), getRow(row, hexR), hexR, points);
    LineTo[] innerDrawings = new LineTo[drawings.length];
    int size = drawings.length - 1;
    for (int i = 0; i < size; i++) {
      innerDrawings[i] = new LineTo(
          translateX(drawings[i].getX(), points[i]),
          translateY(drawings[i].getY(), points[i]));
    }
    innerDrawings[size] = new LineTo(
        translateXBordered(drawings[size].getX(), points[size]),
        translateYBordered(drawings[size].getY(), points[size]));
    oE.addAll(drawings);
    iE.addAll(innerDrawings);
    imE.addAll(innerDrawings);
  }

  private double translateX(double x, int position) {
    switch (position) {
      case 0:
        return x;
      case 1:
      case 2:
        return x - frameScale;
      case 3:
        return x;
      case 4:
      case 5:
        return x + frameScale;
      default:
        return x;
    }
  }

  private double translateY(double y, int position) {
    switch (position) {
      case 0:
        return y - frameScale;
      case 1:
        return y - frameScale / 2;
      case 2:
        return y + frameScale / 2;
      case 3:
        return y + frameScale;
      case 4:
        return y + frameScale / 2;
      case 5:
        return y - frameScale / 2;
      default:
        return y;
    }
  }

  private double translateXBordered(double x, int position) {
    switch (position) {
      case 0:
      case 1:
        return x - frameScale;
      case 2:
        return x;
      case 3:
      case 4:
        return x + frameScale;
      default:
        return x;
    }
  }

  private double translateYBordered(double y, int position) {
    switch (position) {
      case 0:
        return y - frameScale / 2;
      case 1:
        return y + frameScale / 2;
      case 2:
        return y + frameScale;
      case 3:
        return y + frameScale / 2;
      case 4:
        return y - frameScale / 2;
      case 5:
        return y - frameScale;
      default:
        return y;
    }
  }

  private int getRow(int num, int r) {
    return (int) (10 + (r * 1.5) * num);
  }

  private int getCol(int num, int r) {
    return num * (r - (r / 10 + 1));
  }
}
