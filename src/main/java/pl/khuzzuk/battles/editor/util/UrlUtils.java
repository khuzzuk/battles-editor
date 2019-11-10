package pl.khuzzuk.battles.editor.util;

import java.nio.file.Path;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UrlUtils {
  public static String resolveUrl(Path dir, String file) {
    return dir.resolve(file).toFile().toURI().toString();
  }
}
