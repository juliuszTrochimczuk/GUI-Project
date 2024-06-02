package Components.MainMenu;

import Components.Game.GameThread;
import Components.Game.UI.UIGameFrame;

import javax.swing.*;
import java.awt.*;

public class MainMenuFrame extends JFrame {
    OpeningPanel openingPanel = new OpeningPanel();
    ChooseGameSizePanel gameSizePanel = new ChooseGameSizePanel();

    public MainMenuFrame() {
        setTitle("Pac-man - Main Menu");
        setSize(new Dimension(300, 300));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new TitlePanel(), BorderLayout.NORTH);

        openingPanel.addActionListenerToExitButton(e -> dispose());
        openingPanel.addActionListenerToNewGameButton(e -> {
            remove(openingPanel);
            add(gameSizePanel, BorderLayout.CENTER);
            repaint();
            revalidate();
        });

        gameSizePanel.addActionListenerToReturnToOpeningPanelButton(e -> {
            remove(gameSizePanel);
            add(openingPanel, BorderLayout.CENTER);
            repaint();
            revalidate();
        });
        gameSizePanel.addActionListenerToVerySmallBoardButton(e -> {
            new GameThread(16, 16).start();
            dispose();
        });
        gameSizePanel.addActionListenerToSmallBoardButton(e -> {
            new GameThread(24, 24).start();
            dispose();
        });
        gameSizePanel.addActionListenerToMediumBoardButton(e -> {
            new GameThread(32, 32).start();
            dispose();
        });
        gameSizePanel.addActionListenerToBigBoardButton(e -> {
            new GameThread(40, 40).start();
            dispose();
        });
        gameSizePanel.addActionListenerToVeryBigBoardButton(e -> {
            new GameThread(48, 48).start();
            dispose();
        });

        add(openingPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
