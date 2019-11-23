package pl.khuzzuk.battles.editor.print;

import java.util.ArrayDeque;
import java.util.Queue;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.PrinterJob;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.khuzzuk.battles.editor.card.Card;
import pl.khuzzuk.battles.editor.card.CardConstants;
import pl.khuzzuk.battles.editor.nation.Nation;
import pl.khuzzuk.battles.editor.repo.Repo;
import pl.khuzzuk.battles.editor.ui.DirectPane;
import pl.khuzzuk.battles.editor.ui.ImageService;

@Slf4j
@RequiredArgsConstructor
@Service
public class PdfPrinter {

  private static final double A4_WIDTH_IN_PIX = 595.44;
  private static final double A4_HEIGHT_IN_PIX = 841.68;
  private static final double BACK_MARGIN = 42.53;
  private static final double FRONT_MARGIN = 56.71;

  private final Repo repo;
  private final ImagePrinter imagePrinter;
  private final ImageService imageService;
  private final Rectangle frontRect = new Rectangle(A4_WIDTH_IN_PIX * CardConstants.PRINT_SCALE,
      A4_HEIGHT_IN_PIX * CardConstants.PRINT_SCALE);
//  private final Rectangle backRect = new Rectangle(A4_WIDTH_IN_PIX * CardConstants.PRINT_SCALE,
//      A4_HEIGHT_IN_PIX * CardConstants.PRINT_SCALE);
  private final Rectangle backRect = new Rectangle(595.44, 841.68);
  private Queue<Card> cards = new ArrayDeque<>();
  private DirectPane front = new DirectPane();
  private DirectPane back = new DirectPane();

  @PostConstruct
  private void refresh() {
    front.getChildren().setAll(frontRect);
    //back.getChildren().setAll(backRect);
  }

  public void add(Card card) {
    cards.add(card);
  }

  public void clear() {
    cards.clear();
  }

  public void print() {
    if (cards.isEmpty()) {
      return;
    }

    log.info(String.format("Creating a file: %s", cards));
    refresh();
    Nation nation = repo.getNationByName(cards.peek().getNationName());

    PrinterJob job = PrinterJob.createPrinterJob();
    job.showPrintDialog(null);
    addBack(nation);
    job.getJobSettings().setPageLayout(
        job.getPrinter().createPageLayout(Paper.A4, PageOrientation.PORTRAIT, 0, 0, 0, 0));
    job.printPage(this.back);
    job.endJob();
  }

  private void addBack(Nation nation) {
    Rectangle background = new Rectangle(
        (A4_WIDTH_IN_PIX - BACK_MARGIN * 2) / CardConstants.PRINT_SCALE,
        (A4_HEIGHT_IN_PIX - BACK_MARGIN * 2) / CardConstants.PRINT_SCALE);
    background = new Rectangle(595.44 - 20, 841.68 - 20);
    background = new Rectangle(100, 100);
    Rectangle background2 = new Rectangle(100, 100);
    Rectangle background3 = new Rectangle(100, 100);
    Rectangle background4 = new Rectangle(100, 100);
    Image backgroundImage = imageService.getImage(nation.getBackgroundImagePath());
    background.setFill(new ImagePattern(backgroundImage));
    background2.setFill(new ImagePattern(backgroundImage));
    background3.setFill(new ImagePattern(backgroundImage));
    background4.setFill(new ImagePattern(backgroundImage));
    background2.setScaleX(-1);
    background2.setStrokeWidth(0);
    background3.setScaleY(-1);
    background4.setScaleX(-1);
    background4.setScaleY(-1);
    back.place(background, 10, 10);
    back.place(background2, 110, 10);
    back.place(background3, 10, 110);
    back.place(background4, 110, 110);
  }
}
