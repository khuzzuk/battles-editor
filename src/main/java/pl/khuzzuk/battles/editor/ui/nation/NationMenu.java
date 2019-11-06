package pl.khuzzuk.battles.editor.ui.nation;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.AllArgsConstructor;
import pl.khuzzuk.battles.editor.api.Nation;
import pl.khuzzuk.battles.editor.ui.ContentPane;
import pl.khuzzuk.battles.editor.ui.DirectPane;

@AllArgsConstructor
public class NationMenu extends DirectPane {
    private ContentPane pane;

    public void refresh(DirectPane toReturn, Nation nation) {
        pane.getChildren().clear();
        pane.getChildren().add(this);
        getChildren().clear();

        Label nameLabel = new Label("Name");
        place(nameLabel, 10, 10);

        TextField nameField = new TextField(nation.getName());
        place(nameField, 100, 10);
    }
}
