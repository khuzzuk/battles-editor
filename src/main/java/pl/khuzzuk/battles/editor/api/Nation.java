package pl.khuzzuk.battles.editor.api;

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