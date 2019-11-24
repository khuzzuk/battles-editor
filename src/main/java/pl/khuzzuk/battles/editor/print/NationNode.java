package pl.khuzzuk.battles.editor.print;

import static pl.khuzzuk.battles.editor.card.CardConstants.CARD_HEIGHT;
import static pl.khuzzuk.battles.editor.card.CardConstants.CARD_WIDTH;
import static pl.khuzzuk.battles.editor.card.CardConstants.PRINT_SCALE;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import pl.khuzzuk.battles.editor.nation.Nation;
import pl.khuzzuk.battles.editor.ui.DirectPane;
import pl.khuzzuk.battles.editor.ui.HeaderView;
import pl.khuzzuk.battles.editor.ui.ImageService;
import pl.khuzzuk.battles.editor.ui.WithEffects;

@RequiredArgsConstructor
@Component
class NationNode extends DirectPane implements WithEffects, InitializingBean {

  private static final double MARGIN_MULT = 1.04197;
  private static final double MARGIN_WIDTH = (CARD_WIDTH * MARGIN_MULT) - CARD_WIDTH;
  private static final double MARGIN_HEIGHT = (CARD_HEIGHT * MARGIN_MULT) - CARD_HEIGHT;
  private static final double CENTRAL_SECTION_WIDTH = 260;
  private static final double CENTRAL_INNER_WIDTH = 250;
  private static final double HEADER_X = 235;
  private static final double HEADER_Y = 200;
  private static final double EMBLEM_BORDER_X = 235;
  private static final double EMBLEM_BORDER_Y = 290;
  private static final double EMBLEM_X = 240;
  private static final double EMBLEM_Y = 300;
  private final ImageService imageService;

  private Rectangle leftTop = new Rectangle(CARD_WIDTH * MARGIN_MULT, CARD_HEIGHT * MARGIN_MULT);
  private Rectangle rightTop = new Rectangle(CARD_WIDTH * MARGIN_MULT, CARD_HEIGHT * MARGIN_MULT);
  private Rectangle leftCenter = new Rectangle(CARD_WIDTH * MARGIN_MULT, CARD_HEIGHT);
  private Rectangle rightCenter = new Rectangle(CARD_WIDTH * MARGIN_MULT, CARD_HEIGHT);
  private Rectangle leftBottom = new Rectangle(CARD_WIDTH * MARGIN_MULT, CARD_HEIGHT * MARGIN_MULT);
  private Rectangle rightBottom = new Rectangle(CARD_WIDTH * MARGIN_MULT,
      CARD_HEIGHT * MARGIN_MULT);

  private HeaderView leftTopHeader = new HeaderView(260, 70, PRINT_SCALE);
  private HeaderView rightTopHeader = new HeaderView(260, 70, PRINT_SCALE);
  private HeaderView leftCenterHeader = new HeaderView(260, 70, PRINT_SCALE);
  private HeaderView rightCenterHeader = new HeaderView(260, 70, PRINT_SCALE);
  private HeaderView leftBottomHeader = new HeaderView(260, 70, PRINT_SCALE);
  private HeaderView rightBottomHeader = new HeaderView(260, 70, PRINT_SCALE);

  private Rectangle leftTopEmblemBorder = new Rectangle(260, 260);
  private Rectangle rightTopEmblemBorder = new Rectangle(260, 260);
  private Rectangle leftCenterEmblemBorder = new Rectangle(260, 260);
  private Rectangle rightCenterEmblemBorder = new Rectangle(260, 260);
  private Rectangle leftBottomEmblemBorder = new Rectangle(260, 260);
  private Rectangle rightBottomEmblemBorder = new Rectangle(260, 260);

  private Rectangle leftTopEmblem = new Rectangle(250, 250);
  private Rectangle rightTopEmblem = new Rectangle(250, 250);
  private Rectangle leftCenterEmblem = new Rectangle(250, 250);
  private Rectangle rightCenterEmblem = new Rectangle(250, 250);
  private Rectangle leftBottomEmblem = new Rectangle(250, 250);
  private Rectangle rightBottomEmblem = new Rectangle(250, 250);

  @Override
  public void afterPropertiesSet() throws Exception {
    new Scene(new Group(this));

    rightTop.setScaleX(-1);
    leftCenter.setScaleY(-1);
    rightCenter.setScaleX(-1);
    rightCenter.setScaleY(-1);
    rightBottom.setScaleX(-1);

    place(leftTop, 0, 0);
    place(rightTop, CARD_WIDTH * MARGIN_MULT, 0);
    place(leftCenter, 0, CARD_HEIGHT * MARGIN_MULT);
    place(rightCenter, CARD_WIDTH * MARGIN_MULT, CARD_HEIGHT * MARGIN_MULT);
    place(leftBottom, 0, CARD_HEIGHT * MARGIN_MULT + CARD_HEIGHT);
    place(rightBottom, CARD_WIDTH * MARGIN_MULT, CARD_HEIGHT * MARGIN_MULT + CARD_HEIGHT);

    place(leftTopHeader, HEADER_X + MARGIN_WIDTH, HEADER_Y + MARGIN_HEIGHT);
    place(rightTopHeader, HEADER_X + CARD_WIDTH + MARGIN_WIDTH, HEADER_Y + MARGIN_HEIGHT);
    place(leftCenterHeader, HEADER_X + MARGIN_WIDTH, HEADER_Y + MARGIN_HEIGHT + CARD_HEIGHT);
    place(rightCenterHeader, HEADER_X + CARD_WIDTH + MARGIN_WIDTH, HEADER_Y + MARGIN_HEIGHT + CARD_HEIGHT);
    place(leftBottomHeader, HEADER_X + MARGIN_WIDTH, HEADER_Y + MARGIN_HEIGHT + CARD_HEIGHT * 2);
    place(rightBottomHeader, HEADER_X + CARD_WIDTH + MARGIN_WIDTH, HEADER_Y + MARGIN_HEIGHT + CARD_HEIGHT * 2);

    place(leftTopEmblemBorder, EMBLEM_BORDER_X + MARGIN_WIDTH, EMBLEM_BORDER_Y + MARGIN_HEIGHT);
    place(rightTopEmblemBorder, EMBLEM_BORDER_X + MARGIN_WIDTH + CARD_WIDTH, EMBLEM_BORDER_Y + MARGIN_HEIGHT);
    place(leftCenterEmblemBorder, EMBLEM_BORDER_X + MARGIN_WIDTH, EMBLEM_BORDER_Y + MARGIN_HEIGHT + CARD_HEIGHT);
    place(rightCenterEmblemBorder, EMBLEM_BORDER_X + MARGIN_WIDTH + CARD_WIDTH, EMBLEM_BORDER_Y + MARGIN_HEIGHT + CARD_HEIGHT);
    place(leftBottomEmblemBorder, EMBLEM_BORDER_X + MARGIN_WIDTH, EMBLEM_BORDER_Y + MARGIN_HEIGHT + CARD_HEIGHT * 2);
    place(rightBottomEmblemBorder, EMBLEM_BORDER_X + MARGIN_WIDTH + CARD_WIDTH, EMBLEM_BORDER_Y + MARGIN_HEIGHT + CARD_HEIGHT * 2);

    place(leftTopEmblem, EMBLEM_X + MARGIN_WIDTH, EMBLEM_Y + MARGIN_HEIGHT);
    place(rightTopEmblem, EMBLEM_X + MARGIN_WIDTH + CARD_WIDTH, EMBLEM_Y + MARGIN_HEIGHT);
    place(leftCenterEmblem, EMBLEM_X + MARGIN_WIDTH, EMBLEM_Y + MARGIN_HEIGHT + CARD_HEIGHT);
    place(rightCenterEmblem, EMBLEM_X + MARGIN_WIDTH + CARD_WIDTH, EMBLEM_Y + MARGIN_HEIGHT + CARD_HEIGHT);
    place(leftBottomEmblem, EMBLEM_X + MARGIN_WIDTH, EMBLEM_Y + MARGIN_HEIGHT + CARD_HEIGHT * 2);
    place(rightBottomEmblem, EMBLEM_X + MARGIN_WIDTH + CARD_WIDTH, EMBLEM_Y + MARGIN_HEIGHT + CARD_HEIGHT * 2);

    addDropShadow(leftTopEmblemBorder);
    addDropShadow(rightTopEmblemBorder);
    addDropShadow(leftCenterEmblemBorder);
    addDropShadow(rightCenterEmblemBorder);
    addDropShadow(leftBottomEmblemBorder);
    addDropShadow(rightBottomEmblemBorder);

    addInnerShadow(leftTopEmblem);
    addInnerShadow(rightTopEmblem);
    addInnerShadow(leftCenterEmblem);
    addInnerShadow(rightCenterEmblem);
    addInnerShadow(leftBottomEmblem);
    addInnerShadow(rightBottomEmblem);

    leftTopEmblemBorder.setArcWidth(25);
    rightTopEmblemBorder.setArcWidth(25);
    leftCenterEmblemBorder.setArcWidth(25);
    rightCenterEmblemBorder.setArcWidth(25);
    leftBottomEmblemBorder.setArcWidth(25);
    rightBottomEmblemBorder.setArcWidth(25);
    leftTopEmblemBorder.setArcHeight(25);
    rightTopEmblemBorder.setArcHeight(25);
    leftCenterEmblemBorder.setArcHeight(25);
    rightCenterEmblemBorder.setArcHeight(25);
    leftBottomEmblemBorder.setArcHeight(25);
    rightBottomEmblemBorder.setArcHeight(25);

  }

  void refresh(Nation nation) {
    ImagePattern background = imageService.getPaint(nation.getBackgroundImagePath());
    ImagePattern emblem = imageService.getPaint(nation.getEmblemImagePath());

    leftTop.setFill(background);
    rightTop.setFill(background);
    leftCenter.setFill(background);
    rightCenter.setFill(background);
    leftBottom.setFill(background);
    rightBottom.setFill(background);

    leftTopHeader.refresh(nation.getName(), background);
    rightTopHeader.refresh(nation.getName(), background);
    leftCenterHeader.refresh(nation.getName(), background);
    rightCenterHeader.refresh(nation.getName(), background);
    leftBottomHeader.refresh(nation.getName(), background);
    rightBottomHeader.refresh(nation.getName(), background);

    leftTopEmblemBorder.setFill(background);
    rightTopEmblemBorder.setFill(background);
    leftCenterEmblemBorder.setFill(background);
    rightCenterEmblemBorder.setFill(background);
    leftBottomEmblemBorder.setFill(background);
    rightBottomEmblemBorder.setFill(background);

    leftTopEmblem.setFill(emblem);
    rightTopEmblem.setFill(emblem);
    leftCenterEmblem.setFill(emblem);
    rightCenterEmblem.setFill(emblem);
    leftBottomEmblem.setFill(emblem);
    rightBottomEmblem.setFill(emblem);
  }

}
