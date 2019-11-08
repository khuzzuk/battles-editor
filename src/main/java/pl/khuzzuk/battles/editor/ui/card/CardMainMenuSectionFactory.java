package pl.khuzzuk.battles.editor.ui.card;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import pl.khuzzuk.battles.editor.api.Card;
import pl.khuzzuk.battles.editor.api.Nation;
import pl.khuzzuk.battles.editor.ui.UIContext;

public class CardMainMenuSectionFactory {

  public static void createSection(UIContext ctx) {
    Label cardLabel = new Label("Cards");
    ctx.getMainMenu().place(cardLabel, 10, 10);

    ComboBox<Card> cardSelector = new ComboBox<>();
    cardSelector.getItems().clear();
    cardSelector.getItems().addAll(ctx.getRepo().getCards());
    ctx.getMainMenu().place(cardSelector, 100, 10);

    Button addCardButton = new Button("+");
    addCardButton.setDisable(true);
    ctx.getNationSelector().valueProperty().addListener(
        (obs, prev, next) -> onNationSelection(next, ctx, addCardButton, cardSelector));
    addCardButton.setOnAction(event -> cardEditor(ctx, cardSelector));
    ctx.getMainMenu().place(addCardButton, 70, 10);
  }

  private static void cardEditor(UIContext ctx, ComboBox<Card> cardSelector) {
    Card card = cardSelector.getValue();
    if (card == null) {
      card = new Card();
      card.setNationName(ctx.getNationSelector().getValue().getName());
    }
    ctx.getCardMenu().refresh(card);
  }

  private static void onNationSelection(Nation nation, UIContext ctx, Button addCardButton,
      ComboBox<Card> cardSelector) {
    if (nation != null) {
      addCardButton.setDisable(false);
      ctx.getNationService().assureNationDirectory(nation);
      cardSelector.getItems().setAll(ctx.getRepo().getCardsByNation(nation));
    } else {
      addCardButton.setDisable(true);
    }
  }
}
