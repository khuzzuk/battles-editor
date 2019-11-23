package pl.khuzzuk.battles.editor.ui.equipment;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.khuzzuk.battles.editor.equipment.Equipment;
import pl.khuzzuk.battles.editor.repo.Repo;
import pl.khuzzuk.battles.editor.ui.MainMenu;

@AllArgsConstructor
@Component
public class EquipmentMainMenuSectionFactory {

  private ComboBox<Equipment> equipmentSelector;
  private Repo repo;
  private EquipmentMenu equipmentMenu;

  public void createSection(MainMenu mainMenu) {
    Label equipmentLabel = new Label("Equipment");
    mainMenu.place(equipmentLabel, 10, 90);

    equipmentSelector.getItems().setAll(repo.getEquipment());
    mainMenu.place(equipmentSelector, 120, 90);

    Button createEquipmentButton = new Button("+");
    createEquipmentButton.setOnAction(event -> equipmentMenu.refresh(new Equipment()));
    mainMenu.place(createEquipmentButton, 70, 90);

    Button editEquipmentButton = new Button("E");
    editEquipmentButton.setDisable(true);
    editEquipmentButton.setOnAction(event -> equipmentMenu.refresh(equipmentSelector.getValue()));
    equipmentSelector.valueProperty()
        .addListener((c, o, n) -> editEquipmentButton.setDisable(n == null));
    mainMenu.place(editEquipmentButton, 90, 90);
  }
}
