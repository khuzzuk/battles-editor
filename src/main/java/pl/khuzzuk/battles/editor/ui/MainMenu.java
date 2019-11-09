package pl.khuzzuk.battles.editor.ui;

import java.io.File;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import pl.khuzzuk.battles.editor.repo.Repo;
import pl.khuzzuk.battles.editor.settings.SettingsRepo;
import pl.khuzzuk.battles.editor.ui.card.CardMainMenuSectionFactory;
import pl.khuzzuk.battles.editor.ui.nation.NationMainMenuSectionFactory;

@AllArgsConstructor
@Component
public class MainMenu extends DirectPane implements InitializingBean {
    private UIBridge uiBridge;
    private Repo repo;
    private SettingsRepo settingsRepo;
    private NationMainMenuSectionFactory nationMainMenuSectionFactory;
    private CardMainMenuSectionFactory cardMainMenuSectionFactory;

    @Override
    public void afterPropertiesSet() {
        uiBridge.setMainMenu(this);
        refresh();
    }

    @Override
    public void refresh() {
        getChildren().clear();
        if (settingsRepo.hasCurrentWorkingDirectory()) {
            nationMainMenuSectionFactory.createSection(this);
            cardMainMenuSectionFactory.createSection(this);
        } else {
            Button selectDirectory = new Button("Choose directory");
            selectDirectory.setOnAction(event -> selectDirectory());
            getChildren().add(selectDirectory);
        }
    }

    private void selectDirectory() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(getScene().getWindow());
        settingsRepo.setCurrentWorkingDirectory(file.toPath());
        repo.updateDirectory(settingsRepo.getCurrentWorkingDirectory());
        refresh();
    }
}
