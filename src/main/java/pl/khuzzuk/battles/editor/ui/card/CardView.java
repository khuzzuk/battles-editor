package pl.khuzzuk.battles.editor.ui.card;

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
    contentFrame = new CardContentFrame((int) scale, (int) (64 * scale), cardService, imageService,
        equipmentService);
    backElement = new Rectangle(730 * scale, 960 * scale);
    headerView = new HeaderView((int) (670 * scale), (int) (75 * scale));

    place(backElement, 0, 0);
    place(headerView, 30 * scale, 30 * scale);
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
