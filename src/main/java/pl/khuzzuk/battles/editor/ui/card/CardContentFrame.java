package pl.khuzzuk.battles.editor.ui.card;

import static javafx.scene.paint.Color.color;

import java.util.ArrayList;
import java.util.List;
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
import org.apache.commons.lang3.tuple.Pair;
import pl.khuzzuk.battles.editor.card.Card;
import pl.khuzzuk.battles.editor.card.CardService;
import pl.khuzzuk.battles.editor.equipment.Equipment;
import pl.khuzzuk.battles.editor.equipment.EquipmentService;
import pl.khuzzuk.battles.editor.ui.DirectPane;
import pl.khuzzuk.battles.editor.ui.HexPane;
import pl.khuzzuk.battles.editor.ui.Hexagonal;
import pl.khuzzuk.battles.editor.ui.IconPane;
import pl.khuzzuk.battles.editor.ui.ImageService;
import pl.khuzzuk.battles.editor.ui.WithEffects;

class CardContentFrame extends DirectPane implements Hexagonal, HexPane, WithEffects {

  private static final List<Pair<Integer, Integer>> ICON_PLACEMENTS = List
      .of(Pair.of(2, 0), Pair.of(3, 1), Pair.of(4, 0), Pair.of(5, 1), Pair.of(6, 0),
          Pair.of(7, 1), Pair.of(7, 3), Pair.of(3, 9), Pair.of(4, 10),
          Pair.of(5, 9), Pair.of(6, 10), Pair.of(7, 9), Pair.of(7, 5), Pair.of(7, 7));
  private static final Color SKILLS_BLEND = color(0.36, 0.36, 0.36, 0.25);
  private static final Color MOVEMENT_BLEND = color(0.36, 0.61, 0.39, 0.25);
  private static final Color REACH_BLEND = color(0.75, 0.55, 0.20, 0.25);
  private static final Color PHYSIQUE_BLEND = color(0.37, 0.15, 0.02, 0.25);
  private static final Color ARMOR_BLEND = color(0, 0, 0, 0.35);

  private final int hexR;
  private final int scale;
  private final CardService cardService;
  private final ImageService imageService;
  private final EquipmentService equipmentService;
  private double frameScale;

  private Path outer = new Path();
  private Path inner = new Path();
  private Path image = new Path();
  private ObservableList<PathElement> oE = outer.getElements();
  private ObservableList<PathElement> iE = inner.getElements();
  private ObservableList<PathElement> imE = image.getElements();

  private AnchorPane imageContainer = new AnchorPane();
  private ImageView imageView = new ImageView();
  private IconPane iconPane;

  CardContentFrame(int scale, int hexR, CardService cardService, ImageService imageService,
      EquipmentService equipmentService) {
    this.scale = scale;
    this.hexR = hexR;
    this.cardService = cardService;
    this.imageService = imageService;
    this.equipmentService = equipmentService;
    frameScale = hexR / 8d;
    iconPane = new IconPane(hexR, imageService);

    MoveTo startingPoint = getStartingPoint(getCol(4, hexR), getRow(1, hexR), hexR, 5);
    MoveTo innerStartingPoint = new MoveTo(translateX(startingPoint.getX(), 5, frameScale),
        translateY(startingPoint.getY(), 5, frameScale));

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
    place(imageContainer, -frameScale * 7 - 2, -frameScale * 5 - 1);
    place(iconPane, 0, 0);

    addDropShadow(outer);
    addInnerShadow(inner);
    inner.setStrokeWidth(0);
    outer.setStrokeWidth(0);
    image.setFill(Color.BLACK);
    imageContainer.setClip(image);
  }

  void refresh(Paint background) {
    outer.setFill(background);
    inner.setFill(background);
  }

  void refresh(Paint background, Image image, Card card) {
    outer.setFill(background);
    inner.setFill(background);
    imageContainer.getChildren().clear();
    imageView.setImage(image);
    imageView.setFitWidth(card.getW() * scale);
    imageView.setFitHeight(card.getH() * scale);
    AnchorPane.setLeftAnchor(imageView, (double) card.getX() * scale);
    AnchorPane.setTopAnchor(imageView, (double) card.getY() * scale);
    imageContainer.getChildren().add(imageView);

    iconPane.setBackground(background);
    iconPane.clear();
    iconPane.addIcon(0, 2, "" + cardService.getMovement(card), MOVEMENT_BLEND);
    iconPane.addIcon(0, 4, "" + cardService.getWeaponSkills(card), SKILLS_BLEND);
    iconPane.addIcon(0, 6, "" + cardService.getBallisticSkills(card), SKILLS_BLEND);
    iconPane.addIcon(0, 8, "" + cardService.getInitiative(card), SKILLS_BLEND);
    iconPane.addIcon(1, 1, "" + cardService.getStrength(card), PHYSIQUE_BLEND);
    iconPane.addIcon(1, 3, "" + cardService.getToughness(card), PHYSIQUE_BLEND);
    iconPane.addIcon(1, 9, "" + cardService.getLeadership(card), SKILLS_BLEND);
    if (cardService.getWounds(card) > 1) {
      iconPane.addIcon(1, 5, "" + cardService.getWounds(card), PHYSIQUE_BLEND);
    }
    if (cardService.getArmor(card) > 0) {
      iconPane.addIcon(1, 7, "" + cardService.getArmor(card), ARMOR_BLEND);
    }
    if (cardService.getAttacks(card) > 1) {
      iconPane.addIcon(2, 10, "" + cardService.getAttacks(card), SKILLS_BLEND);
    }
    if (cardService.getReach(card) > 1) {
      iconPane.addIcon(2, 0, "" + cardService.getReach(card), REACH_BLEND);
    }
    drawEquipmentIcons(card);
  }

  private void drawWith(int row, int col, int[] points) {
    LineTo[] drawings = drawLines(getCol(col, hexR), getRow(row, hexR), hexR, points);
    LineTo[] innerDrawings = new LineTo[drawings.length];
    int size = drawings.length - 1;
    for (int i = 0; i < size; i++) {
      innerDrawings[i] = new LineTo(
          translateX(drawings[i].getX(), points[i], frameScale),
          translateY(drawings[i].getY(), points[i], frameScale));
    }
    innerDrawings[size] = new LineTo(
        translateXBordered(drawings[size].getX(), points[size], frameScale),
        translateYBordered(drawings[size].getY(), points[size], frameScale));
    oE.addAll(drawings);
    iE.addAll(innerDrawings);
    imE.addAll(innerDrawings);
  }

  private void drawEquipmentIcons(Card card) {
    List<Equipment> equipmentList = new ArrayList<>(equipmentService.findEquipment(card));
    int occupied = cardService.getReach(card) > 1 ? 1 : 0;
    for (int i = 0; i < equipmentList.size() && i < ICON_PLACEMENTS.size() - occupied; i++) {
      Pair<Integer, Integer> position = ICON_PLACEMENTS.get(i + occupied);
      iconPane.addIcon(position.getLeft(), position.getRight(), equipmentList.get(i), scale);
    }
  }
}
