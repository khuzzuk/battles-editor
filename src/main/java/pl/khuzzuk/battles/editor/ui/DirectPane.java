package pl.khuzzuk.battles.editor.ui;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DirectPane extends AnchorPane {
    protected final UIContext ctx;

    public void place(Node node, double x, double y) {
        AnchorPane.setLeftAnchor(node, x);
        AnchorPane.setTopAnchor(node, y);
        getChildren().add(node);
    }

    protected void toMainMenu() {
        ctx.getMainMenu().refresh();
        ctx.getContentPane().getChildren().setAll(ctx.getMainMenu());
    }

    public void refresh() {
        ctx.getContentPane().getChildren().setAll(this);
    }
}
