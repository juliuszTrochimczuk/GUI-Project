package Components.MainMenu;

import javax.swing.*;
import java.awt.*;

public class TitlePanel extends JPanel {
    TitlePanel() {
        JLabel title = new JLabel("PAC-MAN");
        setAlignmentX(JComponent.CENTER_ALIGNMENT);
        add(title);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.YELLOW);
        setBackground(Color.BLACK);
    }
}