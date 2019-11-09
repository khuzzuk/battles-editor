package pl.khuzzuk.battles.editor.ui;

import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.khuzzuk.battles.editor.api.Card;
import pl.khuzzuk.battles.editor.repo.Repo;
import pl.khuzzuk.battles.editor.settings.SettingsRepo;
import pl.khuzzuk.battles.editor.ui.card.CardMainMenuSectionFactory;
import pl.khuzzuk.battles.editor.ui.card.CardMenu;
import pl.khuzzuk.battles.editor.ui.nation.NationMainMenuSectionFactory;
import pl.khuzzuk.battles.editor.ui.nation.NationMenu;

import java.io.File;
import java.nio.file.Path;

@AllArgsConstructor
@Component
public class MainMenu extends DirectPane {
    private Repo repo;
    private SettingsRepo settingsRepo;

    @PostConstruct
    @Override
    public void refresh() {
        getChildren().clear();
        if (settingsRepo.loadSettings().getWorkingDirectory() == null) {
            Button selectDirectory = new Button("Choose directory");
            selectDirectory.setOnAction(event -> selectDirectory());
            getChildren().add(selectDirectory);
        } else {
            NationMainMenuSectionFactory.createSection(ctx);
            CardMainMenuSectionFactory.createSection(ctx);
        }
    }

    private void selectDirectory() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(getScene().getWindow());
        ctx.getSettingsRepo().loadSettings().setWorkingDirectory(file.toString());
        ctx.getSettingsRepo().storeSettings();
        ctx.setRepo(Repo.repoFor(Path.of(ctx.getSettingsRepo().loadSettings().getWorkingDirectory())));
        refresh();
    }

    public void createCard() {
        CardMenu cardMenu = new CardMenu(ctx);
        cardMenu.refresh(new Card());
    }
}
