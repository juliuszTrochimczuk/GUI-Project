package Components.Game;

import javax.swing.*;
import java.awt.*;

public class GameWorld extends JPanel {
    public GameEntity[][] entities;
    private JPanel[][] entityPlacement;

    public GameWorld(int height, int width) {
        setBackground(Color.BLACK);
        entities = new GameEntity[height][width];
        entityPlacement = new JPanel[height][width];
        setSize(new Dimension(width * 16, height * 16));
        setLayout(new GridLayout(width, height));

        generateMap();
    }

    private void generateMap() {
        if (entities[0].length != entities[entities.length - 1].length) {
            throw new RuntimeException("Map have irregular shape");
        }

        //PREPARE SPACE
        for (int i = 0; i < entities.length; i++) {
            for (int j = 0; j < entities[i].length; j++) {
                JPanel panel = new JPanel(new BorderLayout());
                panel.setSize(new Dimension(16, 16));
                panel.setBackground(Color.BLACK);
                entityPlacement[i][j] = panel;
                entities[i][j] = new GameEntity(ObjectsType.EMPTY_SPACE);
                add(entityPlacement[i][j]);
            }
        }

        //FILL HORIZONTAL EDGES
        for (int i = 0; i < entities[0].length; i++) {
            entities[0][i] = new GameEntity(ObjectsType.WALL);
            entityPlacement[0][i].add(entities[0][i], BorderLayout.CENTER);
            entities[entities.length - 1][i] = new GameEntity(ObjectsType.WALL);
            entityPlacement[entities.length - 1][i].add(entities[entities.length - 1][i]);
        }

        //FILL VERTICAL EDGES
        for (int i = 1; i < entities.length - 1; i++) {
            entities[i][0] = new GameEntity(ObjectsType.WALL);
            entityPlacement[i][0].add(entities[i][0], BorderLayout.CENTER);
            entities[i][entities[i].length - 1] = new GameEntity(ObjectsType.WALL);
            entityPlacement[i][entities[i].length - 1].add(entities[i][entities[i].length - 1], BorderLayout.CENTER);
        }

        ObjectsType[] typesOfEntities = {
                ObjectsType.WALL,
                ObjectsType.COIN,
                ObjectsType.EMPTY_SPACE,
        };

        //FILL INNER SPACE
        for (int i = 1; i < entities.length - 1; i++) {
            for (int j = 1; j < entities[i].length - 1; j++) {
               if (entities[i - 1][j].getObjectsType() == ObjectsType.WALL ||
                    entities[i + 1][j].getObjectsType() == ObjectsType.WALL ||
                    entities[i][j - 1].getObjectsType() == ObjectsType.WALL ||
                    entities[i][j + 1].getObjectsType() == ObjectsType.WALL) {
                    entities[i][j] = new GameEntity(ObjectsType.EMPTY_SPACE);
                    entityPlacement[i][j].add(entities[i][j], BorderLayout.CENTER);
                } else {
                    entities[i][j] = new GameEntity(typesOfEntities[(int) (Math.random() * typesOfEntities.length)]);
                    entityPlacement[i][j].add(entities[i][j], BorderLayout.CENTER);
                }
            }
        }

        revalidate();
        repaint();
    }

    private void drawMap() {
        for (int i = 0; i < entities.length; i++) {
            for (int j = 0; j < entities[i].length; j++) {
                if (entities[i][j].getObjectsType() == ObjectsType.WALL) {
                    System.out.printf("X");
                } else if (entities[i][j].getObjectsType() == ObjectsType.COIN) {
                    System.out.printf("o");
                } else if (entities[i][j].getObjectsType() == ObjectsType.EMPTY_SPACE) {
                    System.out.printf("_");
                }
            }
            System.out.println("");
        }
    }
}