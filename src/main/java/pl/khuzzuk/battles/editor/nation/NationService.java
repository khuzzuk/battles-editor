package pl.khuzzuk.battles.editor.nation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.khuzzuk.battles.editor.settings.SettingsRepo;
import pl.khuzzuk.battles.editor.util.UrlUtils;

@AllArgsConstructor
@Service
public class NationService {

  private SettingsRepo settingsRepo;

  public String getBackgroundUrl(Nation nation) {
    return UrlUtils
        .resolveUrl(settingsRepo.getCurrentWorkingDirectory(), nation.getBackgroundImagePath());
  }

  public String getEmblemUrl(Nation nation) {
    return UrlUtils
        .resolveUrl(settingsRepo.getCurrentWorkingDirectory(), nation.getEmblemImagePath());
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
}
