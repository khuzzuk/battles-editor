package pl.khuzzuk.battles.editor.nation;

import lombok.AllArgsConstructor;
import pl.khuzzuk.battles.editor.api.Nation;
import pl.khuzzuk.battles.editor.ui.UIContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@AllArgsConstructor
public class NationService {
    UIContext ctx;

    public String getBackgroundUrl(Nation nation) {
        return getUrl(ctx.getSettingsRepo().getCurrentWorkingDirectory(), nation.getBackgroundImagePath());
    }

    public String getEmblemUrl(Nation nation) {
        return getUrl(ctx.getSettingsRepo().getCurrentWorkingDirectory(), nation.getEmblemImagePath());
    }

    public void assureNationDirectory(Nation nation) {
        Path nationDir = Paths.get(nation.getName());
        Path destination = ctx.getSettingsRepo().getCurrentWorkingDirectory().resolve(nationDir);
        if (!Files.exists(destination)) {
            try {
                Files.createDirectory(destination);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String getUrl(Path dir, String file) {
        return dir.resolve(file).toFile().toURI().toString();
    }
}
