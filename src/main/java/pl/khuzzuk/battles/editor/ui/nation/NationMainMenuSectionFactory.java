package pl.khuzzuk.battles.editor.ui.nation;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import pl.khuzzuk.battles.editor.api.Nation;
import pl.khuzzuk.battles.editor.repo.Repo;
import pl.khuzzuk.battles.editor.ui.MainMenu;

public class NationMainMenuSectionFactory {
    public static void createSection(MainMenu mainMenu, Repo repo) {
        Label nationLabel = new Label("Nations");
        mainMenu.place(nationLabel, 10, 50);

        ComboBox<Nation> nationSelector = new ComboBox<>();
        nationSelector.getItems().clear();
        nationSelector.getItems().addAll(repo.getNations());
        mainMenu.place(nationSelector, 100, 50);

        Button createNationButton = new Button("+");
        createNationButton.setOnAction(event -> mainMenu.createNation());
        mainMenu.place(createNationButton, 70, 50);
    }
}
