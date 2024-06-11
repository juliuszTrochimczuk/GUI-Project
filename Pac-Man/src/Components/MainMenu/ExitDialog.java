package Components.MainMenu;

import javax.swing.*;
import java.awt.*;

public class ExitDialog extends JDialog {
    public ExitDialog(JFrame owner) {
        super(owner, "Exit Dialog");
        setSize(420, 200);

        JPanel textPanel = new JPanel(new FlowLayout());
        {
            textPanel.setBackground(Color.BLACK);
            JLabel l = new JLabel("You really wanna close the game?");
            l.setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
            l.setForeground(Color.YELLOW);
            textPanel.add(l);
        }
        JPanel buttonPanels = new JPanel(new FlowLayout());
        {
            buttonPanels.setBackground(Color.BLACK);
            JButton accept = new JButton("Accept");
            JButton back = new JButton("Back");
            accept.addActionListener(e1 -> owner.dispose());
            back.addActionListener(e1 -> setVisible(false));
            buttonPanels.add(accept);
            buttonPanels.add(back);
        }

        add(textPanel, BorderLayout.NORTH);
        add(buttonPanels, BorderLayout.CENTER);

        setVisible(true);
        setLocationRelativeTo(null);
    }
}
