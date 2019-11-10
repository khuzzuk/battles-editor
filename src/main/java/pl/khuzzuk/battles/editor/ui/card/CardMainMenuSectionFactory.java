package pl.khuzzuk.battles.editor.ui.card;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.khuzzuk.battles.editor.card.Card;
import pl.khuzzuk.battles.editor.nation.Nation;
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
    Button addCardButton = new Button("+");

    mainMenu.place(cardLabel, 10, 10);
    mainMenu.place(cardSelector, 100, 10);
    mainMenu.place(addCardButton, 70, 10);

    nationSelector.valueProperty().addListener(
        (obs, prev, next) -> onNationSelection(next, addCardButton, cardSelector));
    addCardButton.setDisable(true);
    addCardButton.setOnAction(event -> cardEditor());
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
