package Components.MainMenu;

import Components.SaveDataController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HighScorePanel extends JPanel {

    private JButton returnToOpeningPanelButton;

    public HighScorePanel() {
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());

        if (!SaveDataController.getInstance().getData().isEmpty()) {
            String[] dataArr = new String[SaveDataController.getInstance().getData().size()];
            for (int i = 0; i < dataArr.length; i++)
                dataArr[i] = SaveDataController.getInstance().getData().get(i).getData();

            JList<String> list = new JList<>(dataArr);
            list.setForeground(Color.ORANGE);
            list.setBackground(Color.BLACK);
            JScrollPane scrollPane = new JScrollPane(list);

            add(scrollPane, BorderLayout.CENTER);
        }

        returnToOpeningPanelButton = new JButton("Return to Main Menu");
        add(returnToOpeningPanelButton, BorderLayout.SOUTH);
    }

    public void addActionListenerToReturnToOpeningPanelButton(ActionListener actionListener) {
        returnToOpeningPanelButton.addActionListener(actionListener);
    }
}