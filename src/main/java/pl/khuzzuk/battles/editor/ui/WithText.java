package pl.khuzzuk.battles.editor.ui;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public interface WithText {
    Font FONT_14 = Font.loadFont(ClassLoader.getSystemResourceAsStream("battles.ttf"), 14);
    Font FONT_20 = Font.loadFont(ClassLoader.getSystemResourceAsStream("battles.ttf"), 20);
    Font FONT_30 = Font.loadFont(ClassLoader.getSystemResourceAsStream("battles.ttf"), 30);
    Font FONT_40 = Font.loadFont(ClassLoader.getSystemResourceAsStream("battles.ttf"), 40);

    default void placeText(Pane pane, String text) {
        final Label label = new Label(text);

        label.setFont(FONT_40);
        if (label.getWidth() > pane.getPrefWidth()) {
            label.setFont(FONT_20);
        }

        pane.getChildren().setAll(label);
    }
}
