package pl.khuzzuk.battles.editor.settings;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class SettingsRepo implements InitializingBean {

  private static final Path SETTINGS_PATH = Paths.get("settings.json");
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  private Settings settings;

  @Override
  public void afterPropertiesSet() throws IOException {
    settings = readSettingsFromFile();
  }

  private void storeSettings() {
    try (OutputStream output = Files.newOutputStream(SETTINGS_PATH)) {
      OBJECT_MAPPER.writeValue(output, settings);
    } catch (IOException e) {
      System.err.println("Error during settings save");
      e.printStackTrace();
    }
  }

  private Settings readSettingsFromFile() {
    try (BufferedReader reader = Files.newBufferedReader(SETTINGS_PATH)) {
      return OBJECT_MAPPER.readValue(reader, Settings.class);
    } catch (IOException e) {
      return new Settings();
    }
  }

  public boolean hasCurrentWorkingDirectory() {
    return settings.getWorkingDirectory() != null;
  }

  public Path getCurrentWorkingDirectory() {
    return settings.getWorkingDirectory() != null ? Path.of(settings.getWorkingDirectory()) : null;
  }

  public void setCurrentWorkingDirectory(Path directory) {
    Path absDir = directory.toAbsolutePath();
    settings.setWorkingDirectory(absDir.toString());
    storeSettings();
  }

  public Path getFileFromCurrentDirectory(String path) {
    return hasCurrentWorkingDirectory() ? getCurrentWorkingDirectory().resolve(Paths.get(path))
        : Paths.get(path);
  }
}
