package pl.khuzzuk.battles.editor.nation;

import lombok.Data;

@Data
public class Nation {
    private String name;
    private String backgroundImagePath;
    private String emblemImagePath;

    @Override
    public String toString() {
        return name;
    }
}
