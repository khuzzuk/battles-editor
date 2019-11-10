package pl.khuzzuk.battles.editor.card;

import lombok.Data;

import java.util.List;
import pl.khuzzuk.battles.editor.equipment.Equipment;

@Data
public class Card {

  private String name;
  private int experience;
  private List<Equipment> equipment;
  private String nationName;
  private String imagePath;
  private int x;
  private int y;
  private int w;
  private int h;

  @Override
  public String toString() {
    return name;
  }
}
