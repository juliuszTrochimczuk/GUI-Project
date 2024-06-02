package Components.Game.UI;

import Components.Game.GameThread;
import Components.Game.GameWorld;
import Components.MainMenu.MainMenuFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UIGameFrame extends JFrame {
    public UIGameFrame(GameThread gameThread, GameWorld gameWorld) {
        setTitle("Pac-man - Game");
        setSize(new Dimension(gameWorld.getWidth(), gameWorld.getHeight() + 50));

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                MainMenuFrame nextFrame = new MainMenuFrame();
                //Save score
                dispose();
                gameThread.interrupt();
            }
        });

        add(new UIGameCounterPanel(), BorderLayout.NORTH);
        add(gameWorld, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
