package pl.khuzzuk.battles.editor.ui;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class DirectPane extends AnchorPane {
    @Autowired
    private UIBridge uiBridge;

    public void place(Node node, double x, double y) {
        AnchorPane.setLeftAnchor(node, x);
        AnchorPane.setTopAnchor(node, y);
        getChildren().add(node);
    }

    protected void toMainMenu() {
        uiBridge.getContentPane().getChildren().setAll(uiBridge.getMainMenu());
    }

    public void refresh() {
        uiBridge.getContentPane().getChildren().setAll(this);
    }
}
