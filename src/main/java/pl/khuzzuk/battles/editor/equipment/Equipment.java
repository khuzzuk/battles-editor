package pl.khuzzuk.battles.editor.equipment;

import lombok.Data;

@Data
public class Equipment {
    private String name;
    private int movement;
    private int weaponSkills;
    private int ballisticSkills;
    private int strength;
    private int toughness;
    private int wounds;
    private int initiative;
    private int attacks;
    private int leadership;
}
