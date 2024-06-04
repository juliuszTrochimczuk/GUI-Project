package Components.Game.UI;

import Components.Game.GameThread;
import Components.Game.GameWorld;
import Components.Game.MovingEntities.Player;
import Components.Game.Timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UIGameFrame extends JFrame {
    private UIGameCounterPanel counterPanel;

    public UIGameFrame(GameThread gameThread, GameWorld gameWorld) {
        setTitle("Pacman - Game");
        setSize(new Dimension(gameWorld.getWidth(), gameWorld.getHeight() + 50));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosing(e);
                int achivedScore = gameWorld.getPlayer().getScore();
                new UISaveScoreFrame(achivedScore);
                dispose();
                gameThread.interrupt();
            }
        });

        counterPanel = new UIGameCounterPanel();
        add(counterPanel, BorderLayout.NORTH);
        add(gameWorld, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void updateUI(Timer timer, Player player) {
        counterPanel.updateTimeText(timer.getSeconds());
        counterPanel.updateScoreText(player.getScore());
        counterPanel.updateHealthText(player.getHealth());
    }

    public void checkIfTheGameEnds(GameWorld world) {
        if (!areThereAnyDotsToCollect(world) || !playerIsAlive(world.getPlayer()))
            dispose();
    }

    private boolean areThereAnyDotsToCollect(GameWorld world) {
        return world.getDotsCount() != 0;
    }

    private boolean playerIsAlive(Player player) {
        return player.getHealth() != 0;
    }
}