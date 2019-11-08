package pl.khuzzuk.battles.editor.nation;

import lombok.AllArgsConstructor;
import pl.khuzzuk.battles.editor.api.Nation;
import pl.khuzzuk.battles.editor.ui.UIContext;

import java.nio.file.Path;

@AllArgsConstructor
public class NationService {
    UIContext ctx;

    public String getBackgroundUrl(Nation nation) {
        return getUrl(ctx.getSettingsRepo().getCurrentWorkingDirectory(), nation.getBackgroundImagePath());
    }

    public String getEmblemUrl(Nation nation) {
        return getUrl(ctx.getSettingsRepo().getCurrentWorkingDirectory(), nation.getEmblemImagePath());
    }

    private static String getUrl(Path dir, String file) {
        return dir.resolve(file).toFile().toURI().toString();
    }
}
