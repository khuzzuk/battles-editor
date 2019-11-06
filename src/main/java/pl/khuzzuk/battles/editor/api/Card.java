package pl.khuzzuk.battles.editor.api;

import lombok.Data;

import java.util.List;

@Data
public class Card {
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
    private List<Equipment> equipment;
    private String nationName;
}
