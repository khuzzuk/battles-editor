package pl.khuzzuk.battles.editor.ui.equipment;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import pl.khuzzuk.battles.editor.equipment.Equipment;
import pl.khuzzuk.battles.editor.nation.Nation;
import pl.khuzzuk.battles.editor.repo.Repo;
import pl.khuzzuk.battles.editor.settings.SettingsRepo;
import pl.khuzzuk.battles.editor.ui.DirectPane;
import pl.khuzzuk.battles.editor.ui.IconPane;
import pl.khuzzuk.battles.editor.ui.ImageService;

@RequiredArgsConstructor
@Component
public class EquipmentMenu extends DirectPane implements InitializingBean {
  private final ImageService imageService;
  private final SettingsRepo settingsRepo;
  private final Repo repo;
  private final ComboBox<Nation> nationSelector;

  private TextField nameField = new TextField();
  private Button iconFile = new Button();
  private TextField movementField = new TextField();
  private TextField wsField = new TextField();
  private TextField bsField = new TextField();
  private TextField strengthField = new TextField();
  private TextField toughnessField = new TextField();
  private TextField woundsField = new TextField();
  private TextField initiativeField = new TextField();
  private TextField attacksField = new TextField();
  private TextField leadershipField = new TextField();
  private TextField armorField = new TextField();

  private TextField xField = new TextField();
  private TextField yField = new TextField();
  private TextField wField = new TextField();
  private TextField hField = new TextField();

  private Button saveButton = new Button("Save");

  private IconPane iconPane;

  private Equipment equipment;
  private String iconFilePath;

  @Override
  public void afterPropertiesSet() {
    iconPane = new IconPane(64, imageService);

    place(new Label("Name"), 10, 10);
    place(nameField, 100, 10);
    place(iconFile, 10, 50);
    place(new Label("Movement"), 10, 90);
    place(movementField, 100, 90);
    place(new Label("WS"), 10, 130);
    place(wsField, 100, 130);
    place(new Label("BS"), 10, 170);
    place(bsField, 100, 170);
    place(new Label("Strength"), 10, 210);
    place(strengthField, 100, 210);
    place(new Label("Toughness"), 10, 250);
    place(toughnessField, 100, 250);
    place(new Label("Wounds"), 10, 290);
    place(woundsField, 100, 290);
    place(new Label("Init"), 10, 330);
    place(initiativeField, 100, 330);
    place(new Label("Attacks"), 10, 370);
    place(attacksField, 100, 370);
    place(new Label("Leadership"), 10, 410);
    place(leadershipField, 100, 410);
    place(new Label("Armor"), 10, 450);
    place(armorField, 100, 450);

    place(new Label("x"), 15, 490);
    place(xField, 40, 490);
    place(new Label("y"), 15, 530);
    place(yField, 40, 530);
    place(new Label("w"), 15, 570);
    place(wField, 40, 570);
    place(new Label("h"), 15, 610);
    place(hField, 40, 610);

    place(saveButton, 20, 650);

    place(iconPane, 400, 10);

    saveButton.setOnAction(event -> save());
    iconFile.setOnAction(event -> selectIconFile());

    final EventHandler<ActionEvent> imageHandler = event -> {
      equipment.setX(Integer.parseInt(xField.getText()));
      equipment.setY(Integer.parseInt(yField.getText()));
      refresh(equipment);
    };
    xField.setOnAction(imageHandler);
    yField.setOnAction(imageHandler);
    hField.setOnAction(this::changeH);
    wField.setOnAction(this::changeW);
  }

  public void refresh(Equipment equipment) {
    this.equipment = equipment;
    refresh();

    nameField.setText(equipment.getName());
    iconFile.setText(
        StringUtils.isBlank(equipment.getIconFile()) ? "Set icon" : equipment.getIconFile());
    movementField.setText("" + equipment.getMovement());
    wsField.setText("" + equipment.getWeaponSkills());
    bsField.setText("" + equipment.getBallisticSkills());
    strengthField.setText("" + equipment.getStrength());
    toughnessField.setText("" + equipment.getToughness());
    woundsField.setText("" + equipment.getWounds());
    initiativeField.setText("" + equipment.getInitiative());
    attacksField.setText("" + equipment.getAttacks());
    leadershipField.setText("" + equipment.getLeadership());
    armorField.setText("" + equipment.getArmor());
    xField.setText("" + equipment.getX());
    yField.setText("" + equipment.getY());
    wField.setText("" + equipment.getW());
    hField.setText("" + equipment.getH());

    iconPane.clear();
    iconFilePath = equipment.getIconFile();
    if (nationSelector.getValue() != null
        && nationSelector.getValue().getBackgroundImagePath() != null) {
      iconPane
          .setBackground(imageService.getPaint(nationSelector.getValue().getBackgroundImagePath()));
    }
    if (iconFilePath != null) {
      iconPane.addIcon(1, 1, equipment);
    }
  }

  private void save() {
    equipment.setName(nameField.getText());
    equipment.setIconFile(iconFilePath);
    equipment.setMovement(Integer.parseInt(movementField.getText()));
    equipment.setWeaponSkills(Integer.parseInt(wsField.getText()));
    equipment.setBallisticSkills(Integer.parseInt(bsField.getText()));
    equipment.setStrength(Integer.parseInt(strengthField.getText()));
    equipment.setToughness(Integer.parseInt(toughnessField.getText()));
    equipment.setWounds(Integer.parseInt(woundsField.getText()));
    equipment.setInitiative(Integer.parseInt(initiativeField.getText()));
    equipment.setAttacks(Integer.parseInt(attacksField.getText()));
    equipment.setLeadership(Integer.parseInt(leadershipField.getText()));
    equipment.setArmor(Integer.parseInt(armorField.getText()));
    equipment.setX(Integer.parseInt(xField.getText()));
    equipment.setY(Integer.parseInt(yField.getText()));
    equipment.setW(Integer.parseInt(wField.getText()));
    equipment.setH(Integer.parseInt(hField.getText()));

    repo.saveEquipment(equipment);
    toMainMenu();
  }

  private void selectIconFile() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setInitialDirectory(settingsRepo.getCurrentWorkingDirectory().toFile());
    File iconFile = fileChooser.showOpenDialog(getScene().getWindow());
    if (iconFile != null) {
      iconFilePath = settingsRepo.getCurrentWorkingDirectory().relativize(iconFile.toPath())
          .toString();
      Image image = imageService.getImage(iconFilePath);
      equipment.setIconFile(iconFilePath);
      equipment.setW((int) image.getWidth());
      equipment.setH((int) image.getHeight());
      refresh(this.equipment);
    }
  }

  private void changeW(Object any) {
    int w = Integer.parseInt(wField.getText());
    double ratio = ((double) w / (double) equipment.getW());
    int h = (int) (Integer.parseInt(hField.getText()) * ratio);
    hField.setText(h + "");
    equipment.setW(w);
    equipment.setH(h);
    refresh(equipment);
  }

  private void changeH(Object any) {
    int h = Integer.parseInt(hField.getText());
    double ratio = ((double) h / (double) equipment.getH());
    int w = (int) (Integer.parseInt(wField.getText()) * ratio);
    wField.setText(w + "");
    equipment.setW(w);
    equipment.setH(h);
    refresh(equipment);
  }
}
