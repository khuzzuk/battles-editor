package pl.khuzzuk.battles.editor.card;

import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.AllArgsConstructor;
import pl.khuzzuk.battles.editor.api.Card;

@AllArgsConstructor
public class CardService {
  public Path getCardPath(Card card) {
    return Paths.get(card.getNationName(), card.getName());
  }
}
