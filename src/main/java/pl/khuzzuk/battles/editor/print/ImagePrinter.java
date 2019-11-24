package pl.khuzzuk.battles.editor.print;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import pl.khuzzuk.battles.editor.card.Card;
import pl.khuzzuk.battles.editor.card.CardConstants;
import pl.khuzzuk.battles.editor.nation.Nation;
import pl.khuzzuk.battles.editor.repo.Repo;
import pl.khuzzuk.battles.editor.settings.SettingsRepo;
import pl.khuzzuk.battles.editor.ui.ImageService;
import pl.khuzzuk.battles.editor.ui.card.CardView;

@Slf4j
@RequiredArgsConstructor
@Component
public class ImagePrinter implements InitializingBean {
  private final CardView cardView;
  private final Repo repo;
  private final SettingsRepo settingsRepo;
  private final ImageService imageService;
  private final NationNode nationNode;

  @Override
  public void afterPropertiesSet() {
    cardView.rescale(CardConstants.PRINT_SCALE);
    new Scene(new Group(cardView));
  }

  public void saveToPng(Card card) {
    log.info(String.format("Start printing: %s", card));
    try {
      cardView.refresh(card, repo.getNationByName(card.getNationName()));

      log.info("Image built");
      final SnapshotParameters params = new SnapshotParameters();
      params.setViewport(new Rectangle2D(0, 0, CardConstants.CARD_WIDTH, CardConstants.CARD_HEIGHT));
      WritableImage image = cardView.snapshot(params, null);

      final Path pathToSave = settingsRepo
          .getFileFromCurrentDirectory(card.getNationName(), card.getName());
      log.info(String.format("Writing image: %s", pathToSave));
      ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png",
          new File(pathToSave
              + ".png"));

      log.info(String.format("Done printing: %s", card));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void saveToPng(Nation nation) {
    log.info(String.format("Start printing nation back: %s", nation));
    nationNode.refresh(nation);

    WritableImage image = nationNode.snapshot(new SnapshotParameters(), null);
    Path nationPngPath = settingsRepo.getFileFromCurrentDirectory(nation.getName());

    try {
      ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", new File(nationPngPath + ".png"));
      log.info(String.format("Done printing nation: %s", nation));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
