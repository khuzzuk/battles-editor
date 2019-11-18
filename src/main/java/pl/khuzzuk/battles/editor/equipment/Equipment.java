package pl.khuzzuk.battles.editor.equipment;

import lombok.Data;

@Data
public class Equipment {
    private String name;
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
    private int x;
    private int y;
    private int w;
    private int h;

    @Override
    public String toString() {
        return name;
    }
}
