package pl.khuzzuk.battles.editor.ui;

import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public interface WithEffects {
    default void addDropShadow(Shape shape) {
        shape.setEffect(new DropShadow(10, 0, 0, Color.BLACK));
    }

    default void addInnerShadow(Shape shape) {
        InnerShadow innerShadow = new InnerShadow(10, 0, 0, Color.BLACK);
        Light.Point light = new Light.Point(125, 125, 250, Color.WHITE);
        Lighting lighting = new Lighting(light);
        innerShadow.setInput(lighting);
        shape.setEffect(innerShadow);
    }
}
