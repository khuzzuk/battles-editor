package pl.khuzzuk.battles.editor.ui;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.khuzzuk.battles.editor.card.Card;
import pl.khuzzuk.battles.editor.print.PdfPrinter;

@RequiredArgsConstructor
@Component
public class PrintMenuFactory {
  private final PdfPrinter pdfPrinter;
  private final ComboBox<Card> cardSelector;

  public void addTo(MainMenu mainMenu) {
    Button printButton = new Button();
    mainMenu.place(printButton, 10, 130);
    printButton.setDisable(true);

    cardSelector.valueProperty().addListener((obs, o, n) -> printButton.setDisable(n == null));

    printButton.setOnAction(event -> {
      pdfPrinter.add(cardSelector.getValue());
      pdfPrinter.print();
    });
  }
}
