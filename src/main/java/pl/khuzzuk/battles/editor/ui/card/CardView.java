package pl.khuzzuk.battles.editor.ui.card;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
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
public class CardView extends DirectPane implements WithEffects, WithText, InitializingBean {
  private final NationService nationService;
  private final CardService cardService;
  private final EquipmentService equipmentService;
  private final ImageService imageService;

  private Rectangle backElement = new Rectangle(731, 1181);
  private HeaderView headerView = new HeaderView(671, 75);
  private CardContentFrame contentFrame;

  @Override
  public void afterPropertiesSet() {
    contentFrame = new CardContentFrame(64, cardService, imageService, equipmentService);

    place(backElement, 0, 0);
    place(headerView, 30, 30);
    place(contentFrame, 24, 120);
  }

  void refresh(Card card, Nation nation) {
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
