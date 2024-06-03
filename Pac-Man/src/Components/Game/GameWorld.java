package Components.Game;

import Components.Game.Entities.GameEntity;
import Components.Game.Entities.ObjectsType;
import Components.Game.Player.Player;

import javax.swing.*;
import java.awt.*;

public class GameWorld extends JPanel {
    private GameEntity[][] entities;
    private JPanel[][] entityPlacement;
    private Player player;

    public GameWorld(int height, int width) {
        setBackground(Color.BLACK);
        setSize(new Dimension(width * 16, height * 16));
        setLayout(new GridLayout(width, height));

        entities = new GameEntity[height][width];
        entityPlacement = new JPanel[height][width];

        generateMap();
        player = new Player(this);
        entities[1][1] = player;
        entityPlacement[1][1].remove(0);
        entityPlacement[1][1].add(player);
    }

    public Player getPlayer() {
        return player;
    }

    public void updateWorld() {
        player.move();
    }

    synchronized
    public GameEntity getEntityAtPosition(int y, int x) {
        return entities[y][x];
    }

    public void swapEntityPlaces(GameEntity caller, GameEntity toSwitch) {
        GameEntity objectToRemember = caller;

        entities[toSwitch.getPosition()[0]][toSwitch.getPosition()[1]] = caller;
        entityPlacement[toSwitch.getPosition()[0]][toSwitch.getPosition()[1]].remove(toSwitch);
        entityPlacement[toSwitch.getPosition()[0]][toSwitch.getPosition()[1]].add(caller);

        entities[objectToRemember.getPosition()[0]][objectToRemember.getPosition()[1]] = toSwitch;
        entityPlacement[objectToRemember.getPosition()[0]][objectToRemember.getPosition()[1]].remove(objectToRemember);
        entityPlacement[objectToRemember.getPosition()[0]][objectToRemember.getPosition()[1]].add(toSwitch);

        int[] oldCallerPosition = {caller.getPosition()[0], caller.getPosition()[1]};
        caller.setPosition(toSwitch.getPosition());
        toSwitch.setPosition(oldCallerPosition);
    }

    public GameEntity changeEntity(GameEntity original, GameEntity newEntity) {
        entities[original.getPosition()[0]][original.getPosition()[1]] = newEntity;
        entityPlacement[original.getPosition()[0]][original.getPosition()[1]].remove(original);
        entityPlacement[original.getPosition()[0]][original.getPosition()[1]].add(newEntity);
        revalidate();
        repaint();
        return newEntity;
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
                entities[i][j] = new GameEntity(ObjectsType.EMPTY_SPACE, i, j);
                add(entityPlacement[i][j]);
            }
        }

        //FILL HORIZONTAL EDGES
        for (int i = 0; i < entities[0].length; i++) {
            entities[0][i] = new GameEntity(ObjectsType.WALL, 0, i);
            entityPlacement[0][i].add(entities[0][i], BorderLayout.CENTER);
            entities[entities.length - 1][i] = new GameEntity(ObjectsType.WALL, entities.length - 1, i);
            entityPlacement[entities.length - 1][i].add(entities[entities.length - 1][i]);
        }

        //FILL VERTICAL EDGES
        for (int i = 1; i < entities.length - 1; i++) {
            entities[i][0] = new GameEntity(ObjectsType.WALL, i, 0);
            entityPlacement[i][0].add(entities[i][0], BorderLayout.CENTER);
            entities[i][entities[i].length - 1] = new GameEntity(ObjectsType.WALL, i, entities[i].length - 1);
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
                    entities[i][j] = new GameEntity(ObjectsType.EMPTY_SPACE, i, j);
                    entityPlacement[i][j].add(entities[i][j], BorderLayout.CENTER);
                } else {
                    entities[i][j] = new GameEntity(typesOfEntities[(int) (Math.random() * typesOfEntities.length)], i, j);
                    entityPlacement[i][j].add(entities[i][j], BorderLayout.CENTER);
                }
            }
        }
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