package pl.khuzzuk.battles.editor.card;

import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.scene.image.Image;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.khuzzuk.battles.editor.repo.Repo;
import pl.khuzzuk.battles.editor.settings.SettingsRepo;
import pl.khuzzuk.battles.editor.util.UrlUtils;

@AllArgsConstructor
@Service
public class CardService {
  private SettingsRepo settingsRepo;
  private Repo repo;

  public Path getCardPath(Card card) {
    return Paths.get(card.getNationName(), card.getName());
  }

  public String getImageUrl(Card card) {
    return UrlUtils.resolveUrl(settingsRepo.getCurrentWorkingDirectory(), card.getImagePath());
  }

  public void setImageToCard(Card card, Path path) {
    card.setImagePath(settingsRepo.getCurrentWorkingDirectory().relativize(path).toString());
    Image image = new Image(getImageUrl(card));
    card.setW((int) image.getWidth());
    card.setH((int) image.getHeight());
  }

  private static void define() {
    int movement;
    int weaponSkills;
    int ballisticSkills;
    int strength;
    int toughness;
    int wounds;
    int initiative;
    int attacks;
    int leadership;
  }
}
