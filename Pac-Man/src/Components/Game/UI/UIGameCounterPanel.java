package Components.Game.UI;

import javax.swing.*;
import java.awt.*;

public class UIGameCounterPanel extends JPanel {
    private JLabel healthText;
    private JLabel scoreText;
    private JLabel timeText;

    public UIGameCounterPanel() {
        setBackground(Color.BLACK);
        setLayout(new FlowLayout(FlowLayout.LEFT));

        healthText = new JLabel("Health: ");
        scoreText = new JLabel("Score: ");
        timeText = new JLabel("Time: ");

        add(healthText);
        add(scoreText);
        add(timeText);
    }

    public void updateTimeText(long timeThatPassed) {
        long minutes = timeThatPassed / 60;
        long seconds = timeThatPassed % 60;
        String finalText = "Time: ";
        if (minutes != 0)
            finalText += (minutes + ":");
        finalText += seconds;
        timeText.setText(finalText);
    }

    public void updateScoreText(int score) {
        scoreText.setText("Score: " + score);
    }
}