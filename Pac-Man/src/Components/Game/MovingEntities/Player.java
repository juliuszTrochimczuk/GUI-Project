package Components.Game.MovingEntities;

import Components.Game.Entities.GameEntity;
import Components.Game.Entities.ObjectsType;
import Components.Game.GameWorld;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends GameEntity implements Runnable {
    private GameWorld world;

    private int[] moveDirection = new int[2];
    private ImageIcon[][] animation = new ImageIcon[4][3];
    private BufferedImage[][] animationBufferedImages = new BufferedImage[4][3];
    private Action upAction;
    private Action downAction;
    private Action rightAction;
    private Action leftAction;

    private int score;

    private int health = 3;

    public Player(GameWorld world) {
        super(ObjectsType.PLAYER, 1, 1);
        this.world = world;
        score = 0;

        String[] directions = {"up", "down", "right", "left"};
        for (int i = 0; i < directions.length; i++) {
            for (int j = 1; j <= 3; j++) {
                try {
                    File file = new File("Assets/pacman-"+directions[i]+"/"+j+".png");
                    animationBufferedImages[i][j-1] = ImageIO.read(file);
                    animation[i][j-1] = new ImageIcon(animationBufferedImages[i][j-1]);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    System.exit(-1);
                }
            }
        }

        upAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveDirection[0] = -1;
                moveDirection[1] = 0;
            }
        };
        downAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveDirection[0] = 1;
                moveDirection[1] = 0;
            }
        };
        rightAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveDirection[0] = 0;
                moveDirection[1] = 1;
            }
        };
        leftAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveDirection[0] = 0;
                moveDirection[1] = -1;
            }
        };

        getInputMap().put(KeyStroke.getKeyStroke("UP"), "upAction");
        getActionMap().put("upAction", upAction);
        getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "downAction");
        getActionMap().put("downAction", downAction);
        getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "rightAction");
        getActionMap().put("rightAction", rightAction);
        getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "leftAction");
        getActionMap().put("leftAction", leftAction);

        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                for (int i = 0; i < animationBufferedImages.length; i++) {
                    for (int j = 0; j < animationBufferedImages[i].length; j++) {
                        Image img = animationBufferedImages[i][j].getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
                        animation[i][j] = new ImageIcon(img);
                    }
                }
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

    synchronized
    public void move() {
        GameEntity nextEntity = world.getEntityAtPosition(pos[0] + moveDirection[0], pos[1] + moveDirection[1]);
        if (nextEntity.getObjectsType().isWalkable()) {
            if (nextEntity.getObjectsType() == ObjectsType.COIN) {
                score += 1;
                world.removeDot(nextEntity);
                nextEntity = world.changeEntity(nextEntity, new GameEntity(ObjectsType.EMPTY_SPACE, nextEntity.getPosition()[0], nextEntity.getPosition()[1]));
            }
            world.swapEntityPlaces(this, nextEntity);
        }
        else if (nextEntity.getObjectsType() == ObjectsType.GHOST)
            health -= 1;
        setFocusable(true);
        requestFocusInWindow();
    }

    public int getScore() {
        return score;
    }

    public int getHealth() {
        return health;
    }

    @Override
    public void run() {
        int activeAnimationIndex = 0;
        boolean indexGrow = true;
        int directionIndex = 0;
        while (true) {
            if (getMoveDirection()[0] == -1)
                directionIndex = 0;
            else if (getMoveDirection()[0] == 1)
                directionIndex = 1;
            else if (getMoveDirection()[1] == 1)
                directionIndex = 2;
            else if (getMoveDirection()[1] == -1)
                directionIndex = 3;
            setIcon(animation[directionIndex][activeAnimationIndex]);
            if (indexGrow)
                activeAnimationIndex++;
            else
                activeAnimationIndex--;
            if (activeAnimationIndex == 3) {
                activeAnimationIndex = 2;
                indexGrow = false;
            }
            else if (activeAnimationIndex == -1) {
                activeAnimationIndex = 0;
                indexGrow = true;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) { }
        }
    }

    synchronized
    public int[] getMoveDirection() {
        return moveDirection;
    }
}