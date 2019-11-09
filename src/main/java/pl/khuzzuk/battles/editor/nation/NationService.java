package pl.khuzzuk.battles.editor.nation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.khuzzuk.battles.editor.api.Nation;
import pl.khuzzuk.battles.editor.settings.SettingsRepo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@AllArgsConstructor
@Service
public class NationService {
    private SettingsRepo settingsRepo;

    public String getBackgroundUrl(Nation nation) {
        return getUrl(settingsRepo.getCurrentWorkingDirectory(), nation.getBackgroundImagePath());
    }

    public String getEmblemUrl(Nation nation) {
        return getUrl(settingsRepo.getCurrentWorkingDirectory(), nation.getEmblemImagePath());
    }

    public void assureNationDirectory(Nation nation) {
        Path nationDir = Paths.get(nation.getName());
        Path destination = settingsRepo.getCurrentWorkingDirectory().resolve(nationDir);
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
