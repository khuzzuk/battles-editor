package pl.khuzzuk.battles.editor.repo;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;
import pl.khuzzuk.battles.editor.card.Card;
import pl.khuzzuk.battles.editor.equipment.Equipment;
import pl.khuzzuk.battles.editor.nation.Nation;
import pl.khuzzuk.battles.editor.settings.SettingsRepo;

@AllArgsConstructor
@Repository
public class Repo implements InitializingBean {

    private static final String EQUIPMENT_FILE = "Equipment/equipment.json";
    private final ObjectMapper objectMapper;

    private final SettingsRepo settingsRepo;

    @Getter
    private Set<Card> cards = new HashSet<>();
    @Getter
    private Set<Nation> nations = new HashSet<>();
    @Getter
    private Set<Equipment> equipment = new HashSet<>();

    @Override
    public void afterPropertiesSet() {
        if (settingsRepo.hasCurrentWorkingDirectory()) {
            updateDirectory(settingsRepo.getCurrentWorkingDirectory());
        }
    }

    public void updateDirectory(Path directory) {
        cards.clear();
        nations.clear();
        equipment.clear();

        loadNations(directory);
        loadCards(directory);
        loadEquipment(directory);
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
            Nation nation = objectMapper.readValue(reader, Nation.class);
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
            Card card = objectMapper.readValue(reader, Card.class);
            cards.add(card);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveNation(Nation nation, Path directory) {
        nations.add(nation);
        Path nationPath = directory.resolve(Path.of(nation.getName() + ".json"));
        try (OutputStream output = Files.newOutputStream(nationPath)){
            objectMapper.writeValue(output, nation);
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
            objectMapper.writeValue(output, card);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadEquipment(Path directory) {
        try {
            Path equipmentPath = directory.resolve(Paths.get(EQUIPMENT_FILE));
            if (Files.exists(equipmentPath)) {
                try (BufferedReader reader = Files.newBufferedReader(equipmentPath)) {
                    Equipment[] equipmentToAdd = objectMapper.readValue(reader, Equipment[].class);
                    equipment.addAll(Arrays.asList(equipmentToAdd));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveEquipment(Equipment equipment) {
        this.equipment.add(equipment);
        Path equipmentPath = settingsRepo.getFileFromCurrentDirectory(EQUIPMENT_FILE);

        try (OutputStream output = Files.newOutputStream(equipmentPath)) {
            objectMapper.writeValue(output, this.equipment);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Optional<Equipment> findEquipmentByName(String name) {
        return equipment.stream().filter(eq -> eq.getName().equals(name)).findAny();
    }
}
