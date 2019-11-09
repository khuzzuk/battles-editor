package pl.khuzzuk.battles.editor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.khuzzuk.battles.editor.ui.MainMenu;

@SpringBootApplication
public class Editor extends Application {
    private ConfigurableApplicationContext context;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Battles Editor");
        stage.setMaximized(true);

        VBox root = context.getBean("root", VBox.class);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void init() throws Exception {
        context = SpringApplication.run(Editor.class);
    }

    @Override
    public void stop() throws Exception {
        context.stop();
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

    public static void main(String[] args) {
        launch(args);
    }
}
