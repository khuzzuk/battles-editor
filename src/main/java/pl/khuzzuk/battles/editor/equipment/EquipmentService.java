package pl.khuzzuk.battles.editor.equipment;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.khuzzuk.battles.editor.card.Card;
import pl.khuzzuk.battles.editor.repo.Repo;

@AllArgsConstructor
@Service
public class EquipmentService {

  private Repo repo;

  public Set<Equipment> findEquipment(Card card) {
    return card.getEquipment().stream()
        .map(repo::findEquipmentByName)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .collect(Collectors.toSet());
  }

  public int getMovementMod(Set<Equipment> equipment) {
    return equipment.stream().mapToInt(Equipment::getMovement).sum();
  }

  public int getWeaponSkillsMod(Set<Equipment> equipment) {
    return equipment.stream().mapToInt(Equipment::getWeaponSkills).sum();
  }

  public int getBallisticSkillsMod(Set<Equipment> equipment) {
    return equipment.stream().mapToInt(Equipment::getBallisticSkills).sum();
  }

  public int getStrengthMod(Set<Equipment> equipment) {
    return equipment.stream().mapToInt(Equipment::getStrength).sum();
  }

  public int getToughnessMod(Set<Equipment> equipment) {
    return equipment.stream().mapToInt(Equipment::getToughness).sum();
  }

  public int getWoundsMod(Set<Equipment> equipment) {
    return equipment.stream().mapToInt(Equipment::getWounds).sum();
  }

  public int getInitiativeMod(Set<Equipment> equipment) {
    return equipment.stream().mapToInt(Equipment::getInitiative).sum();
  }

  public int getAttacksMod(Set<Equipment> equipment) {
    return equipment.stream().mapToInt(Equipment::getAttacks).sum();
  }

  public int getLeadershipMod(Set<Equipment> equipment) {
    return equipment.stream().mapToInt(Equipment::getLeadership).sum();
  }

  public int getArmorMod(Set<Equipment> equipment) {
    return equipment.stream().mapToInt(Equipment::getArmor).sum();
  }
}
