package pl.khuzzuk.battles.editor.card;

import lombok.AllArgsConstructor;
import pl.khuzzuk.battles.editor.api.Card;

import java.nio.file.Path;
import java.nio.file.Paths;

@AllArgsConstructor
public class CardService {
  public Path getCardPath(Card card) {
    return Paths.get(card.getNationName(), card.getName());
  }
}
