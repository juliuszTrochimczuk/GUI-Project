package Components.Game.Player;

import Components.Game.Entities.GameEntity;
import Components.Game.Entities.ObjectsType;
import Components.Game.GameWorld;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends GameEntity implements Runnable {
    private int[] moveDirection = new int[2];
    private ImageIcon[][] animation = new ImageIcon[4][3];
    private GameWorld world;

    private Action upAction;
    private Action downAction;
    private Action rightAction;
    private Action leftAction;

    public Player(GameWorld world) {
        super(ObjectsType.PLAYER, 1, 1);
        //setFocusable(true);
        this.world = world;

        String[] directions = {"up", "down", "right", "left"};
        for (int i = 0; i < directions.length; i++) {
            for (int j = 1; j <= 3; j++) {
                try {
                    File file = new File("Assets/pacman-"+directions[i]+"/"+j+".png");
                    BufferedImage icon = ImageIO.read(file);
                    animation[i][j-1] = new ImageIcon(icon);
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
    }

    synchronized
    public void move() {
        GameEntity nextEntity = world.getEntityAtPosition(pos[0] + moveDirection[0], pos[1] + moveDirection[1]);
        if (nextEntity.getObjectsType().isWalkable()) {
            world.switchEntityPlaces(this, nextEntity);
            setFocusable(true);
        }
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