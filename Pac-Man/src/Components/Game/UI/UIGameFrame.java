package Components.Game.UI;

import Components.Game.GameThread;
import Components.Game.GameWorld;
import Components.Game.Player.Player;
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

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
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
}