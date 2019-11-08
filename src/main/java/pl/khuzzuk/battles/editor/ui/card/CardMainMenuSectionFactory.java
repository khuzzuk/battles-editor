package pl.khuzzuk.battles.editor.ui.card;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import pl.khuzzuk.battles.editor.api.Card;
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
        addCardButton.setOnAction(event -> ctx.getMainMenu().createCard());
        ctx.getMainMenu().place(addCardButton, 70, 10);
    }
}
