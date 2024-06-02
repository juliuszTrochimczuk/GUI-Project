package Components.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class GameEntity extends JLabel {
    private BufferedImage icon;
    private ObjectsType objectsType;

    public GameEntity(ObjectsType destineObjectType) {
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
    }

    public ObjectsType getObjectsType() {
        return objectsType;
    }
}
