package Components.MainMenu;

import Components.Game.GameThread;
import Components.SaveDataController;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainMenuFrame extends JFrame {
    private OpeningPanel openingPanel = new OpeningPanel();
    private ChooseGameSizePanel gameSizePanel = new ChooseGameSizePanel();
    private HighScorePanel highScorePanel = new HighScorePanel();

    public MainMenuFrame() {
        setTitle("Pacman - Main Menu");
        setSize(new Dimension(300, 300));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                SaveDataController.getInstance().saveDataInFile();
            }
        });

        add(new TitlePanel(), BorderLayout.NORTH);

        openingPanel.addActionListenerToNewGameButton(e -> {
            remove(openingPanel);
            add(gameSizePanel, BorderLayout.CENTER);
            revalidate();
            repaint();
        });
        openingPanel.addActionListenerToHighScoresButton(e -> {
            remove(openingPanel);
            add(highScorePanel, BorderLayout.CENTER);
            revalidate();
            repaint();
        });
        openingPanel.addActionListenerToExitButton(e -> new ExitDialog(this));

        highScorePanel.addActionListenerToReturnToOpeningPanelButton(e -> {
            remove(highScorePanel);
            add(openingPanel, BorderLayout.CENTER);
            revalidate();
            repaint();
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