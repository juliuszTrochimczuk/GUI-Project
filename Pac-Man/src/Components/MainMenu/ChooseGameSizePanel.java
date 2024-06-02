package Components.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ChooseGameSizePanel extends JPanel {
    private JButton returnToOpeningPanelButton;

    private JButton verySmallBoardButton;
    private JButton smallBoardButton;
    private JButton mediumBoardButton;
    private JButton bigBoardButton;
    private JButton veryBigBoardButton;

    ChooseGameSizePanel() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        returnToOpeningPanelButton = new JButton("Return to Main Menu");
        add(returnToOpeningPanelButton, BorderLayout.SOUTH);

        JPanel buttonPanels = new JPanel(new GridBagLayout());
        {
            buttonPanels.setBackground(Color.BLACK);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            verySmallBoardButton = new JButton("Very Small Board");
            smallBoardButton = new JButton("Small Board");
            mediumBoardButton = new JButton("Medium Board");
            bigBoardButton = new JButton("Big Board");
            veryBigBoardButton = new JButton("Very Big Board");

            buttonPanels.add(verySmallBoardButton, gbc);
            buttonPanels.add(smallBoardButton, gbc);
            buttonPanels.add(mediumBoardButton, gbc);
            buttonPanels.add(bigBoardButton, gbc);
            buttonPanels.add(veryBigBoardButton, gbc);
        }
        add(buttonPanels, BorderLayout.CENTER);
    }

    public void addActionListenerToReturnToOpeningPanelButton(ActionListener actionListener) {
        returnToOpeningPanelButton.addActionListener(actionListener);
    }

    public void addActionListenerToVerySmallBoardButton(ActionListener actionListener) {
        verySmallBoardButton.addActionListener(actionListener);
    }

    public void addActionListenerToSmallBoardButton(ActionListener actionListener) {
        smallBoardButton.addActionListener(actionListener);
    }

    public void addActionListenerToMediumBoardButton(ActionListener actionListener) {
        mediumBoardButton.addActionListener(actionListener);
    }

    public void addActionListenerToBigBoardButton(ActionListener actionListener) {
        bigBoardButton.addActionListener(actionListener);
    }

    public void addActionListenerToVeryBigBoardButton(ActionListener actionListener) {
        veryBigBoardButton.addActionListener(actionListener);
    }
}
