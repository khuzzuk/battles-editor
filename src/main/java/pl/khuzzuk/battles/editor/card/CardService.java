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

  public int getMovement(Card card) {
    return 4;
  }

  public int getWeaponSkills(Card card) {
    return 3 + card.getExperience();
  }

  public int getBallisticSkills(Card card) {
    return 3 + card.getExperience();
  }

  public int getStrength(Card card) {
    return 3;
  }

  public int getToughness(Card card) {
    return 3;
  }

  public int getWounds(Card card) {
    return 1;
  }

  public int getInitiative(Card card) {
    return 3;
  }

  public int getAttacks(Card card) {
    return 1;
  }

  public int getLeadership(Card card) {
    return 7 + card.getExperience();
  }
}
