package Components.Game.UI;

import javax.swing.*;
import java.awt.*;

public class UIGameCounterPanel extends JPanel {
    public UIGameCounterPanel() {
        setBackground(Color.BLACK);
        setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel healthText = new JLabel("Health: ");
        JLabel scoreText = new JLabel("Score: ");
        JLabel timeText = new JLabel("Time: ");

        add(healthText);
        add(scoreText);
        add(timeText);
    }
}
