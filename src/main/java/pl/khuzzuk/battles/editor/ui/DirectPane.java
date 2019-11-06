package pl.khuzzuk.battles.editor.ui;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class DirectPane extends AnchorPane {
    public void place(Node node, double x, double y) {
        AnchorPane.setLeftAnchor(node, x);
        AnchorPane.setTopAnchor(node, y);
        getChildren().add(node);
    }
}
