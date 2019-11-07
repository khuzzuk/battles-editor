package pl.khuzzuk.battles.editor.ui.nation;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pl.khuzzuk.battles.editor.api.Nation;
import pl.khuzzuk.battles.editor.ui.DirectPane;
import pl.khuzzuk.battles.editor.ui.UIContext;

import java.nio.file.Path;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class NationMenu extends DirectPane {
    private Label nameLabel = new Label("Name");
    private TextField nameField = new TextField();
    private Button backgroundFile = new Button();
    private Button emblemFile = new Button();
    private Button saveButton = new Button("Save");

    private Nation nation;
    private Path directory;
    private String backgroundPath;
    private String emblemPath;

    public NationMenu(UIContext uiContext) {
        super(uiContext);
    }

    public void init() {
        getChildren().clear();

        place(nameLabel, 10, 10);
        place(nameField, 100, 10);
        place(backgroundFile, 10, 50);
        place(emblemFile, 10, 100);
        place(saveButton, 10, 200);

        saveButton.setOnAction(event -> save());
    }

    public void refresh(Nation nation, Path directory) {
        super.refresh();
        this.nation = nation;
        this.directory = directory;

        nameField.setText(nation.getName());

        backgroundPath = nation.getBackgroundImagePath();
        backgroundFile.setText(isNotBlank(backgroundPath) ? backgroundPath : "Add background");

        emblemPath = nation.getEmblemImagePath();
        emblemFile.setText(isNotBlank(emblemPath) ? emblemPath : "Add emblem");
    }

    private void save() {
        nation.setName(nameField.getText());
        nation.setBackgroundImagePath(backgroundPath);
        nation.setEmblemImagePath(emblemPath);

        ctx.getRepo().saveNation(nation, directory);
        toMainMenu();
    }
}
