package pl.khuzzuk.battles.editor.ui.nation;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.io.File;
import java.nio.file.Path;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import pl.khuzzuk.battles.editor.nation.Nation;
import pl.khuzzuk.battles.editor.repo.Repo;
import pl.khuzzuk.battles.editor.settings.SettingsRepo;
import pl.khuzzuk.battles.editor.ui.DirectPane;

@RequiredArgsConstructor
@Component
public class NationMenu extends DirectPane implements InitializingBean {
    private final NationView nationView;
    private final SettingsRepo settingsRepo;
    private final Repo repo;

    private Label nameLabel = new Label("Name");
    private TextField nameField = new TextField();
    private Button backgroundFile = new Button();
    private Button emblemFile = new Button();
    private Button saveButton = new Button("Save");

    private Nation nation;
    private Path directory;
    private String backgroundPath;
    private String emblemPath;

    @Override
    public void afterPropertiesSet() {
        place(nameLabel, 10, 10);
        place(nameField, 100, 10);
        place(backgroundFile, 10, 50);
        place(emblemFile, 10, 100);
        place(saveButton, 10, 200);
        place(nationView, 300, 10);

        saveButton.setOnAction(event -> save());
    }

    void refresh(Nation nation, Path directory) {
        super.refresh();
        this.nation = nation;
        this.directory = directory;

        nameField.setText(nation.getName());

        backgroundPath = nation.getBackgroundImagePath();
        backgroundFile.setOnAction(event -> {
            Path file = chooseFile();
            backgroundPath = file.toString();
            backgroundFile.setText(file.toString());
            nation.setBackgroundImagePath(backgroundPath);
            nationView.refresh(nation);
        });
        backgroundFile.setText(isNotBlank(backgroundPath) ? backgroundPath : "Add background");

        emblemPath = nation.getEmblemImagePath();
        emblemFile.setOnAction(event -> {
            Path file = chooseFile();
            emblemPath = file.toString();
            emblemFile.setText(file.toString());
            nation.setEmblemImagePath(emblemPath);
            nationView.refresh(nation);
        });
        emblemFile.setText(isNotBlank(emblemPath) ? emblemPath : "Add emblem");
        nationView.refresh(nation);
    }

    private Path chooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(settingsRepo.getCurrentWorkingDirectory().toFile());
        File file = fileChooser.showOpenDialog(getScene().getWindow());
        return settingsRepo.getCurrentWorkingDirectory().relativize(file.toPath());
    }

    private void save() {
        nation.setName(nameField.getText());
        nation.setBackgroundImagePath(backgroundPath);
        nation.setEmblemImagePath(emblemPath);

        repo.saveNation(nation, directory);
        toMainMenu();
    }
}
