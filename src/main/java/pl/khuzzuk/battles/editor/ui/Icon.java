package pl.khuzzuk.battles.editor.ui;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Path;
import lombok.RequiredArgsConstructor;
import pl.khuzzuk.battles.editor.equipment.Equipment;

@RequiredArgsConstructor
public class Icon extends DirectPane implements Hexagonal, WithEffects, WithText {
  private final int planeR;
  private final Paint background;
  private final ImageService imageService;
  private double iconR;
  private double frameScale;
  private AnchorPane imageContainer = new AnchorPane();
  private ImageView imageView = new ImageView();

  void draw(String content) {
    drawBase();
    addText(content);
  }

  void draw(Equipment equipment, int scale) {
    drawBase();
    addIcon(equipment, scale);
  }

  void addBlend(Paint paint) {
    Path blend = getHex(0, 0, iconR);
    blend.setFill(paint);
    place(blend, 0, 0);
  }

  private void drawBase() {
    getChildren().clear();

    iconR = planeR - (planeR / 8d * 1.5);
    frameScale = planeR / 8d;

    Path icon = getHex(0, 0, iconR);
    icon.setFill(background);
    addDropShadow(icon);
    place(icon, 0, 0);

    double innerSize = iconR - frameScale / 1.5;
    double innerBorderSize = frameScale / 1.75;
    Path innerIcon = getHex(0, 0, (int) innerSize);
    innerIcon.setFill(background);
    addInnerShadow(innerIcon);
    place(innerIcon, innerBorderSize, innerBorderSize * 1.33);
    place(imageContainer, 0, 0);
  }

  private void addText(String content) {
    HBox textBox = new HBox();
    textBox.setPrefWidth(planeR * 1.5);
    textBox.setPrefHeight(planeR * 1.5);
    textBox.setAlignment(Pos.CENTER);
    placeText(textBox, content);
    place(textBox, 0, -planeR * 0.4);
  }

  private void addIcon(Equipment equipment, double scale) {
    imageContainer.getChildren().clear();
    imageView.setImage(imageService.getImage(equipment.getIconFile()));
    imageView.setFitWidth(equipment.getW() * scale);
    imageView.setFitHeight(equipment.getH() * scale);
    AnchorPane.setLeftAnchor(imageView, (double) equipment.getX() * scale);
    AnchorPane.setTopAnchor(imageView, (double) equipment.getY() * scale);
    imageContainer.getChildren().add(imageView);
  }
}
