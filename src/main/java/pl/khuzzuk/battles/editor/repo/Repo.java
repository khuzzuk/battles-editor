package pl.khuzzuk.battles.editor.repo;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import lombok.Getter;
import pl.khuzzuk.battles.editor.api.Card;
import pl.khuzzuk.battles.editor.api.Equipment;
import pl.khuzzuk.battles.editor.api.Nation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
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
        repo.loadCards(directory);
        return repo;
    }

    public Nation getNationByName(String name) {
        return nations.stream().filter(nation -> name.equals(nation.getName())).findAny().orElseThrow();
    }

    private void loadNations(Path directory) {
        try (Stream<Path> files = Files.walk(directory, 1)){
            files.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".json"))
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

    private void loadCards(Path directory) {
        for (Nation nation : nations) {
            Path nationDir = directory.resolve(Paths.get(nation.getName()));

            try (Stream<Path> files = Files.walk(nationDir, 1)){
                files.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".json"))
                    .forEach(path -> loadCard(path, directory));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadCard(Path file, Path directory) {
        try (BufferedReader reader = Files.newBufferedReader(directory.resolve(file))) {
            Card card = OBJECT_MAPPER.readValue(reader, Card.class);
            cards.add(card);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveNation(Nation nation, Path directory) {
        nations.add(nation);
        Path nationPath = directory.resolve(Path.of(nation.getName() + ".json"));
        try (OutputStream output = Files.newOutputStream(nationPath)){
            OBJECT_MAPPER.writeValue(output, nation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Set<Card> getCardsByNation(Nation nation) {
        return cards.stream()
            .filter(card -> nation.getName().equals(card.getNationName()))
            .collect(Collectors.toSet());
    }

    public void saveCard(Card card, Path directory) {
        cards.add(card);
        Path cardPath = directory
            .resolve(Path.of(card.getNationName() + "\\" + card.getName() + ".json"));
        try (OutputStream output = Files.newOutputStream(cardPath)){
            OBJECT_MAPPER.writeValue(output, card);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
