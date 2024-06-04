package Components.Game.Entities;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameEntity extends JLabel {
    protected ObjectsType objectsType;
    protected BufferedImage icon;

    protected int[] pos = new int[2];

    public GameEntity(ObjectsType destineObjectType, int yPosition, int xPosition) {
        setSize(new Dimension(16, 16));
        objectsType = destineObjectType;
        try {
            File file = new File(objectsType.getIconPath());
            icon = ImageIO.read(file);
            setIcon(new ImageIcon(icon));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }

        pos[0] = yPosition;
        pos[1] = xPosition;

        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                Image img = icon.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
                setIcon(new ImageIcon(img));
            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }
            @Override
            public void componentShown(ComponentEvent e) {

            }
            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });
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