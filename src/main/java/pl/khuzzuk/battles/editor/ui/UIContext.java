package pl.khuzzuk.battles.editor.ui;

import lombok.Data;
import pl.khuzzuk.battles.editor.repo.Repo;
import pl.khuzzuk.battles.editor.settings.SettingsRepo;
import pl.khuzzuk.battles.editor.ui.nation.NationMenu;

@Data
public class UIContext {
    private Repo repo;
    private SettingsRepo settingsRepo;
    private ContentPane contentPane;
    private MainMenu mainMenu;
    private NationMenu nationMenu;
}
