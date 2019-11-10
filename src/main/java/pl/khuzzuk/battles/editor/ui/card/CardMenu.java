package pl.khuzzuk.battles.editor.ui.card;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import pl.khuzzuk.battles.editor.card.Card;
import pl.khuzzuk.battles.editor.card.CardService;
import pl.khuzzuk.battles.editor.repo.Repo;
import pl.khuzzuk.battles.editor.settings.SettingsRepo;
import pl.khuzzuk.battles.editor.ui.DirectPane;

@RequiredArgsConstructor
@Component
public class CardMenu extends DirectPane implements InitializingBean {

  private final CardView cardView;
  private final Repo repo;
  private final SettingsRepo settingsRepo;
  private final CardService cardService;

  private Button back = new Button("Back");
  private Button save = new Button("Save");
  private TextField nameField = new TextField();
  private TextField experienceField = new TextField();
  private Button imageSelector = new Button();
  private TextField xField = new TextField();
  private TextField yField = new TextField();
  private TextField wField = new TextField();
  private TextField hField = new TextField();

  private Card card;

  @Override
  public void afterPropertiesSet() {
    place(back, 10, 10);
    place(new Label("Name"), 10, 50);
    place(nameField, 50, 50);
    place(new Label("Experience"), 10, 90);
    place(experienceField, 50, 90);
    place(imageSelector, 10, 130);
    place(new Label("x"), 15, 170);
    place(xField, 40, 170);
    place(new Label("y"), 15, 210);
    place(yField, 40, 210);
    place(new Label("w"), 15, 250);
    place(wField, 40, 250);
    place(new Label("h"), 15, 290);
    place(hField, 40, 290);
    place(save, 10, 600);
    place(cardView, 300, 10);

    back.setOnAction(action -> toMainMenu());
    save.setOnAction(event -> saveCard());
    imageSelector.setOnAction(event -> chooseImage());

    final EventHandler<ActionEvent> imageHandler = event -> {
      moveImage();
      refreshCardView();
    };
    xField.setOnAction(imageHandler);
    yField.setOnAction(imageHandler);
    wField.setOnAction(this::changeW);
    hField.setOnAction(this::changeH);
  }

  void refresh(Card card) {
    super.refresh();
    this.card = card;

    nameField.setText(card.getName());
    experienceField.setText(card.getExperience() + "");
    imageSelector
        .setText(card.getImagePath() != null ? card.getImagePath() : "Choose image path");
    xField.setText(card.getX() + "");
    yField.setText(card.getY() + "");
    wField.setText(card.getW() + "");
    hField.setText(card.getH() + "");

    refreshCardView();
  }

  private void chooseImage() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setInitialDirectory(settingsRepo.getCurrentWorkingDirectory().toFile());
    File file = fileChooser.showOpenDialog(getScene().getWindow());

    if (file != null) {
      cardService.setImageToCard(card, file.toPath());
      refresh(card);
    }
  }

  private void refreshCardView() {
    cardView.refresh(card, repo.getNationByName(card.getNationName()));
  }

  private void changeW(Object any) {
    int w = Integer.parseInt(wField.getText());
    double ratio = ((double) w / (double) card.getW());
    int h = (int) (Integer.parseInt(hField.getText()) * ratio);
    hField.setText(h + "");
    moveImage();
    refreshCardView();
  }

  private void changeH(Object any) {
    int h = Integer.parseInt(hField.getText());
    double ratio = ((double) h / (double) card.getH());
    int w = (int) (Integer.parseInt(wField.getText()) * ratio);
    wField.setText(w + "");
    moveImage();
    refreshCardView();
  }

  private void moveImage() {
    card.setX(Integer.parseInt(xField.getText()));
    card.setY(Integer.parseInt(yField.getText()));
    card.setW(Integer.parseInt(wField.getText()));
    card.setH(Integer.parseInt(hField.getText()));
  }

  private void saveCard() {
    card.setName(nameField.getText());
    card.setExperience(Integer.parseInt(experienceField.getText()));

    repo.saveCard(card, settingsRepo.getCurrentWorkingDirectory());
    moveImage();
    toMainMenu();
  }
}
