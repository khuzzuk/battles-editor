package pl.khuzzuk.battles.editor.ui.card;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.khuzzuk.battles.editor.api.Card;
import pl.khuzzuk.battles.editor.api.Nation;
import pl.khuzzuk.battles.editor.nation.NationService;
import pl.khuzzuk.battles.editor.repo.Repo;
import pl.khuzzuk.battles.editor.ui.MainMenu;

@AllArgsConstructor
@Component
public class CardMainMenuSectionFactory {
  private Repo repo;
  private NationService nationService;
  private ComboBox<Nation> nationSelector;
  private ComboBox<Card> cardSelector;
  private CardMenu cardMenu;

  public void createSection(MainMenu mainMenu) {
    Label cardLabel = new Label("Cards");
    mainMenu.place(cardLabel, 10, 10);

    ComboBox<Card> cardSelector = new ComboBox<>();
    cardSelector.getItems().clear();
    cardSelector.getItems().addAll(repo.getCards());
    mainMenu.place(cardSelector, 100, 10);

    Button addCardButton = new Button("+");
    addCardButton.setDisable(true);
    nationSelector.valueProperty().addListener(
        (obs, prev, next) -> onNationSelection(next, addCardButton, cardSelector));
    addCardButton.setOnAction(event -> cardEditor());
    mainMenu.place(addCardButton, 70, 10);
  }

  private void cardEditor() {
    Card card = cardSelector.getValue();
    if (card == null) {
      card = new Card();
      card.setNationName(nationSelector.getValue().getName());
    }
    cardMenu.refresh(card);
  }

  private void onNationSelection(Nation nation, Button addCardButton,
      ComboBox<Card> cardSelector) {
    if (nation != null) {
      addCardButton.setDisable(false);
      nationService.assureNationDirectory(nation);
      cardSelector.getItems().setAll(repo.getCardsByNation(nation));
    } else {
      addCardButton.setDisable(true);
    }
  }
}
