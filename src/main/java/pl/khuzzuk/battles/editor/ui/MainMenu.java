package pl.khuzzuk.battles.editor.ui;

import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import pl.khuzzuk.battles.editor.api.Card;
import pl.khuzzuk.battles.editor.repo.Repo;
import pl.khuzzuk.battles.editor.ui.card.CardMainMenuSectionFactory;
import pl.khuzzuk.battles.editor.ui.card.CardMenu;
import pl.khuzzuk.battles.editor.ui.nation.NationMainMenuSectionFactory;
import pl.khuzzuk.battles.editor.ui.nation.NationMenu;

import java.io.File;
import java.nio.file.Path;

public class MainMenu extends DirectPane {
    public MainMenu(UIContext ctx) {
        super(ctx);
        ctx.setMainMenu(this);
        ctx.setNationMenu(new NationMenu(ctx));
        ctx.setCardMenu(new CardMenu(ctx));
    }


    @Override
    public void refresh() {
        getChildren().clear();

        if (ctx.getSettingsRepo().loadSettings().getWorkingDirectory() == null) {
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
