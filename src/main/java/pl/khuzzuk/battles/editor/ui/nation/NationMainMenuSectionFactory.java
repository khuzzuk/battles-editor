package pl.khuzzuk.battles.editor.ui.nation;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import lombok.experimental.UtilityClass;
import pl.khuzzuk.battles.editor.api.Nation;
import pl.khuzzuk.battles.editor.ui.UIContext;

@UtilityClass
public class NationMainMenuSectionFactory {
    public static void createSection(UIContext ctx) {
        Label nationLabel = new Label("Nations");
        ctx.getMainMenu().place(nationLabel, 10, 50);

        ComboBox<Nation> nationSelector = new ComboBox<>();
        ctx.setNationSelector(nationSelector);
        nationSelector.getItems().clear();
        nationSelector.getItems().addAll(ctx.getRepo().getNations());
        nationSelector.valueProperty().addListener((obs, prev, nation) -> onNationSelection(nation, ctx));
        ctx.getMainMenu().place(nationSelector, 100, 50);

        Button createNationButton = new Button("+");
        createNationButton.setOnAction(event -> nationEditor(ctx, nationSelector));
        ctx.getMainMenu().place(createNationButton, 70, 50);
    }

    private static void nationEditor(UIContext ctx, ComboBox<Nation> nationSelector) {
        Nation nation = nationSelector.getValue();
        if (nation == null) {
            nation = new Nation();
        }
        ctx.getNationMenu().refresh(nation, ctx.getSettingsRepo().getCurrentWorkingDirectory());
    }

    private static void onNationSelection(Nation nation, UIContext ctx) {
        ctx.getNationService().assureNationDirectory(nation);
        ctx.getRepo().getCardsByNation(nation);
    }
}
