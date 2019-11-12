package pl.khuzzuk.battles.editor.ui;

public interface HexPlane {
  default double translateX(double x, int position, double frameScale) {
    switch (position) {
      case 0:
        return x;
      case 1:
      case 2:
        return x - frameScale;
      case 3:
        return x;
      case 4:
      case 5:
        return x + frameScale;
      default:
        return x;
    }
  }

  default double translateY(double y, int position, double frameScale) {
    switch (position) {
      case 0:
        return y - frameScale;
      case 1:
        return y - frameScale / 2;
      case 2:
        return y + frameScale / 2;
      case 3:
        return y + frameScale;
      case 4:
        return y + frameScale / 2;
      case 5:
        return y - frameScale / 2;
      default:
        return y;
    }
  }

  default double translateXBordered(double x, int position, double frameScale) {
    switch (position) {
      case 0:
      case 1:
        return x - frameScale;
      case 2:
        return x;
      case 3:
      case 4:
        return x + frameScale;
      default:
        return x;
    }
  }

  default double translateYBordered(double y, int position, double frameScale) {
    switch (position) {
      case 0:
        return y - frameScale / 2;
      case 1:
        return y + frameScale / 2;
      case 2:
        return y + frameScale;
      case 3:
        return y + frameScale / 2;
      case 4:
        return y - frameScale / 2;
      case 5:
        return y - frameScale;
      default:
        return y;
    }
  }

  default int getRow(int num, int r) {
    return (int) (10 + (r * 1.5) * num);
  }

  default int getCol(int num, int r) {
    return num * (r - (r / 10 + 1));
  }
}
