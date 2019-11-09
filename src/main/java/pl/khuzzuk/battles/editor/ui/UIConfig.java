package pl.khuzzuk.battles.editor.ui;

import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UIConfig {

  @Bean
  @Qualifier("root")
  VBox root(MenuBar menuBar, ContentPane contentPane) {
    return new VBox(menuBar, contentPane);
  }

  @Bean
  MenuBar menuBar() {
    MenuBar menuBar = new MenuBar();
    return menuBar;
  }
}
