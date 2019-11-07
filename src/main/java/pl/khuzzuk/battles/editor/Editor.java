package pl.khuzzuk.battles.editor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.khuzzuk.battles.editor.repo.Repo;
import pl.khuzzuk.battles.editor.settings.SettingsRepo;
import pl.khuzzuk.battles.editor.ui.ContentPane;
import pl.khuzzuk.battles.editor.ui.MainMenu;
import pl.khuzzuk.battles.editor.ui.UIContext;

import java.nio.file.Path;

public class Editor extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Battles Editor");
        stage.setMaximized(true);

        ContentPane mainContainer = new ContentPane();
        UIContext ctx = new UIContext();
        ctx.setSettingsRepo(settingsRepo());
        ctx.setRepo(repo(ctx));

        MainMenu mainMenu = mainMenu(ctx);
        mainContainer.getChildren().addAll(mainMenu);

        MenuBar menuBar = menuBar(mainMenu, mainContainer);

        VBox root = new VBox();
        root.getChildren().addAll(menuBar, mainContainer);

        stage.setScene(new Scene(root));
        stage.show();
    }

    private static MenuBar menuBar(MainMenu mainMenu, Pane parent) {
        MenuBar menuBar = new MenuBar();

        MenuItem backMenu = new MenuItem("back");
        backMenu.setOnAction(event -> {
            parent.getChildren().clear();
            parent.getChildren().add(mainMenu);
        });

        Menu main = new Menu("Main");
        main.getItems().add(backMenu);

        menuBar.getMenus().add(main);
        return menuBar;
    }

    private static MainMenu mainMenu(UIContext uiContext) {
        MainMenu mainMenu = new MainMenu(uiContext);
        mainMenu.refresh();
        return mainMenu;
    }

    private static SettingsRepo settingsRepo() {
        SettingsRepo repo = new SettingsRepo();
        repo.loadSettings();
        return repo;
    }

    private static Repo repo(UIContext uiContext) {
        if (uiContext.getSettingsRepo().loadSettings().getWorkingDirectory() != null) {
            return Repo.repoFor(Path.of(uiContext.getSettingsRepo().loadSettings().getWorkingDirectory()));
        } else {
            return new Repo();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
