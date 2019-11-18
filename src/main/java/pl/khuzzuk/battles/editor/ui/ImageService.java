package pl.khuzzuk.battles.editor.ui;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.khuzzuk.battles.editor.settings.SettingsRepo;

@AllArgsConstructor
@Service
public class ImageService {
  private SettingsRepo settingsRepo;

  public Image getImage(String path) {
    return new Image(settingsRepo.getFileFromCurrentDirectory(path).toUri().toString());
  }

  public ImagePattern getPaint(String path) {
    return new ImagePattern(getImage(path), 0, 0, 1, 1, true);
  }
}
