package pl.khuzzuk.battles.editor.ui;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public abstract class DirectPane extends AnchorPane {
    @Autowired
    @Lazy
    private MainMenu mainMenu;
    @Lazy
    @Autowired
    ContentPane contentPane;

    public void place(Node node, double x, double y) {
        AnchorPane.setLeftAnchor(node, x);
        AnchorPane.setTopAnchor(node, y);
        getChildren().add(node);
    }

    protected void toMainMenu() {
        mainMenu.refresh();
        contentPane.getChildren().setAll(mainMenu);
    }

    public void refresh() {
        contentPane.getChildren().setAll(this);
    }
}
