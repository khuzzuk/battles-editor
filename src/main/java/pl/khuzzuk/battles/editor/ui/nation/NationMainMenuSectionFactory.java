package pl.khuzzuk.battles.editor.ui.nation;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.khuzzuk.battles.editor.nation.Nation;
import pl.khuzzuk.battles.editor.nation.NationService;
import pl.khuzzuk.battles.editor.repo.Repo;
import pl.khuzzuk.battles.editor.settings.SettingsRepo;
import pl.khuzzuk.battles.editor.ui.MainMenu;

@AllArgsConstructor
@Component
public class NationMainMenuSectionFactory {
    private ComboBox<Nation> nationSelector;
    private Repo repo;
    private SettingsRepo settingsRepo;
    private NationService nationService;
    private NationMenu nationMenu;

    public void createSection(MainMenu mainMenu) {
        Label nationLabel = new Label("Nations");
        mainMenu.place(nationLabel, 10, 50);

        nationSelector.getItems().setAll(repo.getNations());
        nationSelector.valueProperty().addListener((obs, prev, nation) -> onNationSelection(nation));
        mainMenu.place(nationSelector, 100, 50);

        Button createNationButton = new Button("+");
        createNationButton.setOnAction(event -> nationEditor());
        mainMenu.place(createNationButton, 70, 50);
    }

    private void nationEditor() {
        Nation nation =
            nationSelector.getValue() != null ? nationSelector.getValue() : new Nation();
        nationMenu.refresh(nation, settingsRepo.getCurrentWorkingDirectory());
    }

    private void onNationSelection(Nation nation) {
        nationService.assureNationDirectory(nation);
        repo.getCardsByNation(nation);
    }
}
