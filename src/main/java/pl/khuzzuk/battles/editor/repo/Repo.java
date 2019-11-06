package pl.khuzzuk.battles.editor.repo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import pl.khuzzuk.battles.editor.api.Card;
import pl.khuzzuk.battles.editor.api.Equipment;
import pl.khuzzuk.battles.editor.api.Nation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@Getter
public class Repo {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private Set<Card> cards = new HashSet<>();
    private Set<Nation> nations = new HashSet<>();
    private Set<Equipment> equipment = new HashSet<>();

    public static Repo repoFor(Path directory) {
        Repo repo = new Repo();
        repo.loadNations(directory);
        return repo;
    }

    private void loadNations(Path directory) {
        try (Stream<Path> files = Files.walk(directory)){
            PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:*.json");
            files.filter(path -> Files.isRegularFile(path))
                    .filter(pathMatcher::matches)
            .forEach(path -> loadNation(path, directory));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadNation(Path file, Path directory) {
        try (BufferedReader reader = Files.newBufferedReader(directory.resolve(file))) {
            Nation nation = OBJECT_MAPPER.readValue(reader, Nation.class);
            nations.add(nation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveNation(Nation nation, Path directory) {
        nations.add(nation);
        Path nationPath = directory.resolve(Path.of(nation.getName() + ".json"));
        try (OutputStream output = Files.newOutputStream(nationPath)){
            OBJECT_MAPPER.writeValue(output, nation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
