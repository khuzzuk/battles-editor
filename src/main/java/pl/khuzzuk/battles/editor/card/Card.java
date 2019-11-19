package pl.khuzzuk.battles.editor.card;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
public class Card {

  private String name;
  private int experience;
  private Set<String> equipment = new HashSet<>();
  private String nationName;
  private String imagePath;
  private int x;
  private int y;
  private int w;
  private int h;

  public void setEquipment(Set<String> equipment) {
    this.equipment = equipment != null ? equipment : new HashSet<>();
  }

  @Override
  public String toString() {
    return name;
  }
}
