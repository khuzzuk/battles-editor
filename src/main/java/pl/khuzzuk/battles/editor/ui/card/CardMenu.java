package pl.khuzzuk.battles.editor.ui.card;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pl.khuzzuk.battles.editor.api.Card;
import pl.khuzzuk.battles.editor.ui.DirectPane;
import pl.khuzzuk.battles.editor.ui.UIContext;

public class CardMenu extends DirectPane {
    private Button back = new Button("Back");
    private Button save = new Button("Save");
    private TextField nameField = new TextField();
    private CardView cardView;

    private Card card;

    public CardMenu(UIContext uiContext) {
        super(uiContext);
        cardView = new CardView(uiContext);

        place(back, 10, 10);
        place(new Label("Name"), 10, 50);
        place(nameField, 50, 50);
        place(save, 10, 600);
        place(cardView, 300, 10);

        back.setOnAction(action -> toMainMenu());
        save.setOnAction(event -> saveCard());
    }

    public void refresh(Card card) {
        super.refresh();
        this.card = card;

        nameField.setText(card.getName());

        cardView.refresh(card, ctx.getRepo().getNationByName(card.getNationName()));
    }

    private void saveCard() {
        card.setName(nameField.getText());

        ctx.getRepo().saveCard(card, ctx.getSettingsRepo().getCurrentWorkingDirectory());
        toMainMenu();
    }
}
