package pl.khuzzuk.battles.editor.ui;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public interface WithText {
    Font FONT_14 = Font.loadFont(ClassLoader.getSystemResourceAsStream("battles.ttf"), 14);
    Font FONT_20 = Font.loadFont(ClassLoader.getSystemResourceAsStream("battles.ttf"), 20);
    Font FONT_30 = Font.loadFont(ClassLoader.getSystemResourceAsStream("battles.ttf"), 30);
    Font FONT_40 = Font.loadFont(ClassLoader.getSystemResourceAsStream("battles.ttf"), 40);
    Font FONT_60 = Font.loadFont(ClassLoader.getSystemResourceAsStream("battles.ttf"), 60);

    default void placeText(Pane pane, String text) {
        Label label = new Label(text);
        pane.getChildren().setAll(label);

        if (pane.getPrefHeight() > 70) {
            label.setFont(FONT_60);
        } else if (pane.getPrefHeight() > 50) {
            label.setFont(FONT_40);
        } else {
            label.setFont(FONT_30);
        }
    }
}
