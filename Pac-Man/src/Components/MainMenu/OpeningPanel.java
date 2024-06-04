package Components.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class OpeningPanel extends JPanel {
    private JButton newGameButton;
    private JButton highScoresButton;
    private JButton exitButton;

    OpeningPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(Color.BLACK);

        newGameButton = new JButton("New Game");
        highScoresButton = new JButton("High Scores");
        exitButton = new JButton("Exit");

        add(newGameButton);
        add(highScoresButton);
        add(exitButton);
    }

    public void addActionListenerToNewGameButton(ActionListener actionListener) {
        newGameButton.addActionListener(actionListener);
    }

    public void addActionListenerToHighScoresButton(ActionListener actionListener) {
        highScoresButton.addActionListener(actionListener);
    }

    public void addActionListenerToExitButton(ActionListener actionListener) {
        exitButton.addActionListener(actionListener);
    }
}