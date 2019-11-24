package pl.khuzzuk.battles.editor.ui.card;

import static pl.khuzzuk.battles.editor.card.CardConstants.CARD_HEIGHT;
import static pl.khuzzuk.battles.editor.card.CardConstants.CARD_WIDTH;
import static pl.khuzzuk.battles.editor.card.CardConstants.HEADER_HEIGHT;
import static pl.khuzzuk.battles.editor.card.CardConstants.HEADER_MARGIN;
import static pl.khuzzuk.battles.editor.card.CardConstants.HEADER_WIDTH;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.khuzzuk.battles.editor.card.Card;
import pl.khuzzuk.battles.editor.card.CardService;
import pl.khuzzuk.battles.editor.equipment.EquipmentService;
import pl.khuzzuk.battles.editor.nation.Nation;
import pl.khuzzuk.battles.editor.nation.NationService;
import pl.khuzzuk.battles.editor.ui.DirectPane;
import pl.khuzzuk.battles.editor.ui.HeaderView;
import pl.khuzzuk.battles.editor.ui.ImageService;
import pl.khuzzuk.battles.editor.ui.WithEffects;
import pl.khuzzuk.battles.editor.ui.WithText;

@RequiredArgsConstructor
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CardView extends DirectPane implements WithEffects, WithText {
  private final NationService nationService;
  private final CardService cardService;
  private final EquipmentService equipmentService;
  private final ImageService imageService;

  private Rectangle backElement;
  private HeaderView headerView;
  private CardContentFrame contentFrame;

  public void rescale(double scale) {
    setMaxWidth(CARD_WIDTH * scale);
    setMaxHeight(CARD_HEIGHT * scale);
    contentFrame = new CardContentFrame((int) scale, (int) (64 * scale), cardService, imageService,
        equipmentService);
    backElement = new Rectangle(CARD_WIDTH * scale, CARD_HEIGHT * scale);
    headerView = new HeaderView(HEADER_WIDTH, HEADER_HEIGHT, scale);

    place(backElement, 0, 0);
    place(headerView, HEADER_MARGIN * scale, HEADER_MARGIN * scale);
    place(contentFrame, 24 * scale, 120 * scale);
  }

  public void refresh(Card card, Nation nation) {
    Paint backgroundPaint = Color.BLACK;
    if (nation.getBackgroundImagePath() != null) {
      Image backgroundImage = new Image(nationService.getBackgroundUrl(nation));
      backgroundPaint = new ImagePattern(backgroundImage, 0, 0, 1, 1, true);
    }
    backElement.setFill(backgroundPaint);
    headerView.refresh(card.getName(), backgroundPaint);
    if (card.getImagePath() != null) {
      Image contentImage = new Image(cardService.getImageUrl(card));
      contentFrame.refresh(backgroundPaint, contentImage, card);
    } else {
      contentFrame.refresh(backgroundPaint);
    }
  }
}
