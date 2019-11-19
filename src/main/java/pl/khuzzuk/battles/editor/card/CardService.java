package pl.khuzzuk.battles.editor.card;

import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.scene.image.Image;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.khuzzuk.battles.editor.equipment.EquipmentService;
import pl.khuzzuk.battles.editor.repo.Repo;
import pl.khuzzuk.battles.editor.settings.SettingsRepo;
import pl.khuzzuk.battles.editor.util.UrlUtils;

@AllArgsConstructor
@Service
public class CardService {
  private SettingsRepo settingsRepo;
  private Repo repo;
  private EquipmentService equipmentService;

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
    return 4 + equipmentService.getMovementMod(equipmentService.findEquipment(card));
  }

  public int getWeaponSkills(Card card) {
    return 2 + card.getExperience() + +equipmentService
        .getWeaponSkillsMod(equipmentService.findEquipment(card));
  }

  public int getBallisticSkills(Card card) {
    return 2 + card.getExperience() +equipmentService
        .getBallisticSkillsMod(equipmentService.findEquipment(card));
  }

  public int getStrength(Card card) {
    return 3 + equipmentService.getStrengthMod(equipmentService.findEquipment(card));
  }

  public int getToughness(Card card) {
    return 3 + equipmentService.getToughnessMod(equipmentService.findEquipment(card));
  }

  public int getWounds(Card card) {
    return 1 + equipmentService.getWoundsMod(equipmentService.findEquipment(card));
  }

  public int getInitiative(Card card) {
    return 3 + equipmentService.getInitiativeMod(equipmentService.findEquipment(card));
  }

  public int getAttacks(Card card) {
    return 1 + equipmentService.getAttacksMod(equipmentService.findEquipment(card));
  }

  public int getLeadership(Card card) {
    return 7 + card.getExperience() + equipmentService.getLeadershipMod(equipmentService.findEquipment(card));
  }

  public int getArmor(Card card) {
    return equipmentService.getArmorMod(equipmentService.findEquipment(card));
  }
}
