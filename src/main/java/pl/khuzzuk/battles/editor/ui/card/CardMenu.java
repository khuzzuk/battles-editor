package pl.khuzzuk.battles.editor.ui.card;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import pl.khuzzuk.battles.editor.api.Card;
import pl.khuzzuk.battles.editor.repo.Repo;
import pl.khuzzuk.battles.editor.settings.SettingsRepo;
import pl.khuzzuk.battles.editor.ui.DirectPane;

@RequiredArgsConstructor
@Component
public class CardMenu extends DirectPane implements InitializingBean {
    private final CardView cardView;
    private final Repo repo;
    private final SettingsRepo settingsRepo;

    private Button back = new Button("Back");
    private Button save = new Button("Save");
    private TextField nameField = new TextField();

    private Card card;

    @Override
    public void afterPropertiesSet() throws Exception {
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

        cardView.refresh(card, repo.getNationByName(card.getNationName()));
    }

    private void saveCard() {
        card.setName(nameField.getText());

        repo.saveCard(card, settingsRepo.getCurrentWorkingDirectory());
        toMainMenu();
    }
}
