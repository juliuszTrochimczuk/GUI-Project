package Components.Game.MovingEntities;

import Components.Game.Entities.GameEntity;
import Components.Game.Entities.ObjectsType;
import Components.Game.GameWorld;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Ghost extends GameEntity {

    private GameWorld world;

    public Ghost(GameWorld world, String ghostName) {
        super(ObjectsType.GHOST, 1, 1);
        this.world = world;
        try {
            File file = new File("Assets/ghosts/" + ghostName + ".png");
            icon = ImageIO.read(file);
            setIcon(new ImageIcon(icon));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    public void move() {
        int[] moveDirection = chooseMoveDirection();
        GameEntity nextEntity = world.getEntityAtPosition(pos[0] + moveDirection[0], pos[1] + moveDirection[1]);
        if (nextEntity.getObjectsType().isWalkable()) {
            world.swapEntityPlaces(this, nextEntity);
        }
    }

    private int[] chooseMoveDirection() {
        int[] moveDirection = new int[2];
        int drawnedDirection = (int)(Math.random() * 4);
        switch (drawnedDirection) {
            case 0:
                moveDirection[0] = -1;
                moveDirection[1] = 0;
                break;
            case 1:
                moveDirection[0] = 1;
                moveDirection[1] = 0;
                break;
            case 2:
                moveDirection[0] = 0;
                moveDirection[1] = -1;
                break;
            case 3:
                moveDirection[0] = 0;
                moveDirection[1] = 1;
                break;
        }
        return moveDirection;
    }
}
