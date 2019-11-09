package pl.khuzzuk.battles.editor.ui;

import javafx.scene.layout.VBox;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ContentPane extends VBox implements InitializingBean {
  private MainMenu mainMenu;
  private UIBridge uiBridge;

  @Override
  public void afterPropertiesSet() {
    getChildren().add(mainMenu);
    uiBridge.setContentPane(this);
  }
}
