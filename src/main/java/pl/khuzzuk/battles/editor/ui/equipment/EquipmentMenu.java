package pl.khuzzuk.battles.editor.ui.equipment;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import pl.khuzzuk.battles.editor.equipment.Equipment;
import pl.khuzzuk.battles.editor.equipment.EquipmentType;
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
  private final ComboBox<Equipment> equipmentSelector;

  private TextField nameField = new TextField();
  private ComboBox<EquipmentType> typeSelector = new ComboBox<>();
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
  private TextField reachField = new TextField();

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
    typeSelector.getItems().setAll(EquipmentType.values());

    VBox labels = new VBox();
    labels.getChildren().add(new Label("Name"));
    labels.getChildren().add(new Label("Type"));
    labels.getChildren().add(new Label("Icon"));
    labels.getChildren().add(new Label("Movement"));
    labels.getChildren().add(new Label("WS"));
    labels.getChildren().add(new Label("BS"));
    labels.getChildren().add(new Label("Strength"));
    labels.getChildren().add(new Label("Toughness"));
    labels.getChildren().add(new Label("Wounds"));
    labels.getChildren().add(new Label("Init"));
    labels.getChildren().add(new Label("Attacks"));
    labels.getChildren().add(new Label("Leadership"));
    labels.getChildren().add(new Label("Armor"));
    labels.getChildren().add(new Label("Reach"));
    labels.getChildren().add(new Label("x"));
    labels.getChildren().add(new Label("y"));
    labels.getChildren().add(new Label("w"));
    labels.getChildren().add(new Label("h"));
    labels.getChildren().forEach(label -> ((Label)label).setPrefHeight(25));

    VBox fields = new VBox();
    fields.getChildren().add(nameField);
    fields.getChildren().add(typeSelector);
    fields.getChildren().add(iconFile);
    fields.getChildren().add(movementField);
    fields.getChildren().add(wsField);
    fields.getChildren().add(bsField);
    fields.getChildren().add(strengthField);
    fields.getChildren().add(toughnessField);
    fields.getChildren().add(woundsField);
    fields.getChildren().add(initiativeField);
    fields.getChildren().add(attacksField);
    fields.getChildren().add(leadershipField);
    fields.getChildren().add(armorField);
    fields.getChildren().add(reachField);
    fields.getChildren().add(xField);
    fields.getChildren().add(yField);
    fields.getChildren().add(wField);
    fields.getChildren().add(hField);
    fields.getChildren().add(saveButton);

    place(labels, 10, 10);
    place(fields, 100, 10);
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
    typeSelector.setValue(equipment.getType());
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
    reachField.setText("" + equipment.getReach());
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
      iconPane.addIcon(1, 1, equipment, 1);
    }
  }

  private void updateEquipment() {
    equipment.setName(nameField.getText());
    equipment.setType(typeSelector.getValue());
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
    equipment.setReach(Integer.parseInt(reachField.getText()));
    equipment.setX(Integer.parseInt(xField.getText()));
    equipment.setY(Integer.parseInt(yField.getText()));
    equipment.setW(Integer.parseInt(wField.getText()));
    equipment.setH(Integer.parseInt(hField.getText()));
  }

  private void save() {
    updateEquipment();
    repo.saveEquipment(equipment);
    equipmentSelector.getItems().setAll(repo.getEquipment());
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
    updateEquipment();
    refresh(equipment);
  }

  private void changeH(Object any) {
    int h = Integer.parseInt(hField.getText());
    double ratio = ((double) h / (double) equipment.getH());
    int w = (int) (Integer.parseInt(wField.getText()) * ratio);
    wField.setText(w + "");
    updateEquipment();
    refresh(equipment);
  }
}
