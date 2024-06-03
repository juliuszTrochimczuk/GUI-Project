package Components.Game.Entities;

public enum ObjectsType {
    WALL("Assets/other/wall.png", false),
    COIN("Assets/other/dot.png", true),
    EMPTY_SPACE("Assets/other/floor.png", true),
    PLAYER("Assets/pacman-right/1.png", false),
    GHOST("Assets/ghosts/blinky.png", false);

    private final String iconPath;
    private final boolean walkable;

    private ObjectsType(String iconPath, boolean walkable) {

        this.iconPath = iconPath;
        this.walkable = walkable;
    }

    public String getIconPath() {
        return iconPath;
    }

    public boolean isWalkable() { return walkable; }
}
