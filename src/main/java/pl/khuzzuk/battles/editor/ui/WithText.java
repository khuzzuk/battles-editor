package pl.khuzzuk.battles.editor.ui;

import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public interface WithText {
    Font FONT_14 = Font.loadFont(ClassLoader.getSystemResourceAsStream("battles.ttf"), 14);
    Font FONT_20 = Font.loadFont(ClassLoader.getSystemResourceAsStream("battles.ttf"), 20);
    Font FONT_30 = Font.loadFont(ClassLoader.getSystemResourceAsStream("battles.ttf"), 30);
    Font FONT_40 = Font.loadFont(ClassLoader.getSystemResourceAsStream("battles.ttf"), 40);
    Font FONT_60 = Font.loadFont(ClassLoader.getSystemResourceAsStream("battles.ttf"), 60);

    default void placeText(Pane pane, String text) {
        Label label = new Label(text);

        // I'm not proud of this
        label.setFont(FONT_60);
        if (isTextOutsideOfBounds(text, FONT_60, pane)) {
            label.setFont(FONT_40);
            if (isTextOutsideOfBounds(text, FONT_40, pane)) {
                label.setFont(FONT_20);
            }
        }

        pane.getChildren().setAll(label);
    }

    private static boolean isTextOutsideOfBounds(String text, Font font, Pane pane) {
        Text textMeasure = new Text(text);
        textMeasure.setFont(font);
        final Bounds bounds = textMeasure.getLayoutBounds();
        return pane.getPrefWidth() < bounds.getWidth() || pane.getPrefHeight() < bounds.getHeight();
    }
}
