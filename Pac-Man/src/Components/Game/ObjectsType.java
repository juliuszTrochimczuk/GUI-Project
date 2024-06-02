package Components.Game;

public enum ObjectsType {
    WALL("Assets/other/wall.png"),
    COIN("Assets/other/dot.png"),
    EMPTY_SPACE("Assets/other/floor.png"),
    PLAYER("Assets/pacman-right/1.png"),
    GHOST("Assets/ghosts/blinky.png");

    private final String iconPath;

    private ObjectsType(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getIconPath() {
        return iconPath;
    }
}
