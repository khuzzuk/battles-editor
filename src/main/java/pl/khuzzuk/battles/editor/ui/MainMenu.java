package pl.khuzzuk.battles.editor.ui;

import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import lombok.AllArgsConstructor;
import pl.khuzzuk.battles.editor.api.Card;
import pl.khuzzuk.battles.editor.api.Nation;
import pl.khuzzuk.battles.editor.repo.Repo;
import pl.khuzzuk.battles.editor.settings.SettingsRepo;
import pl.khuzzuk.battles.editor.ui.card.CardMainMenuSectionFactory;
import pl.khuzzuk.battles.editor.ui.card.CardMenu;
import pl.khuzzuk.battles.editor.ui.nation.NationMainMenuSectionFactory;
import pl.khuzzuk.battles.editor.ui.nation.NationMenu;

import java.io.File;
import java.nio.file.Path;

@AllArgsConstructor
public class MainMenu extends DirectPane {
    private final ContentPane pane;
    private final SettingsRepo settingsRepo;
    private Repo repo;

    public void refresh() {
        getChildren().clear();

        if (settingsRepo.loadSettings().getWorkingDirectory() == null) {
            Button selectDirectory = new Button("Choose directory");
            selectDirectory.setOnAction(event -> selectDirectory());
            getChildren().add(selectDirectory);
        } else {
            CardMainMenuSectionFactory.createSection(this, repo);
            NationMainMenuSectionFactory.createSection(this, repo);
        }
    }

    private void selectDirectory() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(getScene().getWindow());
        settingsRepo.loadSettings().setWorkingDirectory(file.toString());
        settingsRepo.storeSettings();
        repo = Repo.repoFor(Path.of(settingsRepo.loadSettings().getWorkingDirectory()));
        refresh();
    }

    public void createCard() {
        CardMenu cardMenu = new CardMenu(pane);
        cardMenu.refresh(this, new Card());
    }

    public void createNation() {
        NationMenu nationMenu = new NationMenu(pane);
        nationMenu.refresh(this, new Nation());
    }
}
