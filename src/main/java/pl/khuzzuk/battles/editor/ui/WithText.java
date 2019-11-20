package pl.khuzzuk.battles.editor.ui;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public interface WithText {
    Font FONT_30 = Font.loadFont(ClassLoader.getSystemResourceAsStream("battles.ttf"), 30);
    Font FONT_40 = Font.loadFont(ClassLoader.getSystemResourceAsStream("battles.ttf"), 40);
    Font FONT_60 = Font.loadFont(ClassLoader.getSystemResourceAsStream("battles.ttf"), 60);
    Font FONT_80 = Font.loadFont(ClassLoader.getSystemResourceAsStream("battles.ttf"), 80);
    Font FONT_120 = Font.loadFont(ClassLoader.getSystemResourceAsStream("battles.ttf"), 120);

    default void placeText(Pane pane, String text) {
        Label label = new Label(text);
        pane.getChildren().setAll(label);

        if (text.equals("Herculiani")) {
            System.out.println(pane.getPrefHeight());
        }
        if (pane.getPrefHeight() > 140) {
            label.setFont(FONT_120);
        } else if (pane.getPrefHeight() > 100) {
            label.setFont(FONT_80);
        } else if (pane.getPrefHeight() > 70) {
            label.setFont(FONT_60);
        } else if (pane.getPrefHeight() > 50) {
            label.setFont(FONT_40);
        } else {
            label.setFont(FONT_30);
        }
    }
}
