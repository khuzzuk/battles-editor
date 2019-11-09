package pl.khuzzuk.battles.editor.settings;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.Getter;
import org.springframework.beans.factory.InitializingBean;

public class SettingsRepo implements InitializingBean {
    private static final Path SETTINGS_PATH = Paths.get("settings.json");
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Getter
    private Settings settings;

    @Override
    public void afterPropertiesSet() {
        settings = readSettingsFromFile();
    }

    public void storeSettings() {
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

    public Path getCurrentWorkingDirectory() {
        return Path.of(settings.getWorkingDirectory());
    }
}
