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

    public void addIcon(int row, int col, String content) {
        Icon icon = new Icon(hexR, background);
        icon.draw(content);
        icon.addBlend(Color.color(216 / 255, 158 / 255, 1, 0.25));

        double x = getCol(col, hexR);
        double topAnchor = (hexR * 3d / 2) * row + frameScale * 3 / 2;
        double leftAnchor = x + hexR / 6d;
        place(icon, leftAnchor, topAnchor);
    }
}
