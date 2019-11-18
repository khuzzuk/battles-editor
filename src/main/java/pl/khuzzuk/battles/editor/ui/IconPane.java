package pl.khuzzuk.battles.editor.ui;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import lombok.Setter;

public class IconPane extends DirectPane implements HexPlane {

    private final int hexR;
    private double frameScale;
    @Setter
    private Paint background;

    public IconPane(int hexR) {
        this.hexR = hexR;
        frameScale = hexR / 8d;
    }

    public void clear() {
        getChildren().clear();
    }

    public void addIcon(int row, int col, String content, Color blend) {
        Icon icon = new Icon(hexR, background);
        icon.draw(content);
        icon.addBlend(blend);

        double x = getCol(col, hexR);
        double topAnchor = (hexR * 3d / 2) * row + frameScale * 3 / 2;
        double leftAnchor = x + hexR / 6d;
        place(icon, leftAnchor, topAnchor);
    }
}
