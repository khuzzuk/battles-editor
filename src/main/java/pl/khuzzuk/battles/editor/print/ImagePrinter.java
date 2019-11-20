package pl.khuzzuk.battles.editor.print;

import java.io.File;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import pl.khuzzuk.battles.editor.card.Card;
import pl.khuzzuk.battles.editor.repo.Repo;
import pl.khuzzuk.battles.editor.ui.card.CardView;

@RequiredArgsConstructor
@Component
public class ImagePrinter implements InitializingBean {
  private final CardView cardView;
  private final Repo repo;

  @Override
  public void afterPropertiesSet() {
    cardView.rescale(2);
    new Scene(new Group(cardView));
  }

  public void saveToPng(Card card) {
    try {
      cardView.refresh(card, repo.getNationByName(card.getNationName()));
      WritableImage image = cardView.snapshot(new SnapshotParameters(), null);
      ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", new File(card.getName() + ".png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
