package pl.khuzzuk.battles.editor.ui;

import javafx.scene.control.ComboBox;
import lombok.Data;
import pl.khuzzuk.battles.editor.api.Nation;
import pl.khuzzuk.battles.editor.card.CardService;
import pl.khuzzuk.battles.editor.nation.NationService;
import pl.khuzzuk.battles.editor.repo.Repo;
import pl.khuzzuk.battles.editor.settings.SettingsRepo;
import pl.khuzzuk.battles.editor.ui.card.CardMenu;
import pl.khuzzuk.battles.editor.ui.nation.NationMenu;

@Data
public class UIContext {
    private Repo repo;
    private SettingsRepo settingsRepo;
    private ContentPane contentPane;
    private MainMenu mainMenu;
    private NationMenu nationMenu;
    private ComboBox<Nation> nationSelector;
    private CardMenu cardMenu;
    private NationService nationService;
    private CardService cardService;
}
