package pl.khuzzuk.battles.editor.ui;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.khuzzuk.battles.editor.card.Card;
import pl.khuzzuk.battles.editor.nation.Nation;

@Configuration
public class UIConfig {

  @Bean
  @Qualifier("root")
  VBox root(ContentPane contentPane, MainMenu mainMenu) {
    VBox root = new VBox();

    MenuBar menuBar = new MenuBar();
    MenuItem backMenu = new MenuItem("back");
    backMenu.setOnAction(event -> {
      root.getChildren().clear();
      root.getChildren().add(mainMenu);
    });

    Menu main = new Menu("Main");
    main.getItems().add(backMenu);

    menuBar.getMenus().add(main);

    root.getChildren().setAll(menuBar, contentPane);

    return root;
  }

  @Bean
  ComboBox<Nation> nationSelector() {
    return new ComboBox<>();
  }

  @Bean
  ComboBox<Card> cardSelector() {
    return new ComboBox<>();
  }
}
