package Components.Game.Entities;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameEntity extends JLabel {
    protected ObjectsType objectsType;

    protected int[] pos = new int[2];

    public GameEntity(ObjectsType destineObjectType, int yPosition, int xPosition) {
        setSize(new Dimension(16, 16));
        objectsType = destineObjectType;
        try {
            File file = new File(objectsType.getIconPath());
            BufferedImage icon = ImageIO.read(file);
            setIcon(new ImageIcon(icon));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }

        pos[0] = yPosition;
        pos[1] = xPosition;
    }

    public ObjectsType getObjectsType() {
        return objectsType;
    }

    public void setPosition(int y, int x) {
        pos[0] = y;
        pos[1] = x;
    }

    public void setPosition(int[] pos) {
        if (pos.length != 2) {
            throw new RuntimeException("Position has more/less then two coordinates");
        }
        this.pos = pos;
    }

    public int[] getPosition() {
        return pos;
    }
}