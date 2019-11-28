package pl.khuzzuk.battles.editor.equipment;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "name")
public class Equipment {
    private String name;
    private EquipmentType type;
    private String iconFile;
    private int movement;
    private int weaponSkills;
    private int ballisticSkills;
    private int strength;
    private int toughness;
    private int wounds;
    private int initiative;
    private int attacks;
    private int leadership;
    private int armor;
    private int reach;
    private int x;
    private int y;
    private int w;
    private int h;

    @Override
    public String toString() {
        return name;
    }
}
