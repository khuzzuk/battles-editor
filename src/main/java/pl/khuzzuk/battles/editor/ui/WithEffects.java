package pl.khuzzuk.battles.editor.ui;

import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Shape;

public interface WithEffects {
    default void setBackground(Image background, Shape element) {
        element.setFill(new ImagePattern(background, 0, 0, 1, 1, true));
        element.setStrokeWidth(0);
    }

    default void addDropShadow(Shape shape) {
        shape.setEffect(new DropShadow(10, 0, 0, Color.BLACK));
    }

    default void addInnerShadow(Shape shape) {
        double width = shape.getBoundsInLocal().getWidth();
        InnerShadow innerShadow = new InnerShadow(10, 0, 0, Color.BLACK);
        Light.Point light = new Light.Point(width / 2, width / 2, width, Color.WHITE);
        Lighting lighting = new Lighting(light);
        innerShadow.setInput(lighting);
        shape.setEffect(innerShadow);
    }
}
