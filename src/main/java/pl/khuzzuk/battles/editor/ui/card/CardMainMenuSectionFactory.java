package pl.khuzzuk.battles.editor.ui.card;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import pl.khuzzuk.battles.editor.api.Card;
import pl.khuzzuk.battles.editor.repo.Repo;
import pl.khuzzuk.battles.editor.ui.MainMenu;

public class CardMainMenuSectionFactory {
    public static void createSection(MainMenu mainMenu, Repo repo) {
        Label cardLabel = new Label("Cards");
        mainMenu.place(cardLabel, 10, 10);
        ComboBox<Card> cardSelector = new ComboBox<>();
        cardSelector.getItems().clear();
        cardSelector.getItems().addAll(repo.getCards());
        mainMenu.place(cardSelector, 100, 10);
        Button addCardButton = new Button("+");
        addCardButton.setOnAction(event -> mainMenu.createCard());
        mainMenu.place(addCardButton, 70, 10);

    }
}
