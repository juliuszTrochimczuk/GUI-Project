package Components.Game.UI;

import Components.MainMenu.MainMenuFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import java.io.IOException;

public class UISaveScoreFrame extends JFrame {
    public UISaveScoreFrame(int playerAchivedScore) {
        setTitle("Pacman - Save score");
        setBackground(Color.BLACK);
        setSize(new Dimension(400, 250));
        setVisible(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                new MainMenuFrame();
                dispose();
            }
        });

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        {
            titlePanel.setBackground(Color.BLACK);
            JLabel text = new JLabel("Save your score: " + playerAchivedScore + " under nick...");
            text.setForeground(Color.YELLOW);
            titlePanel.add(text);
        }

        JPanel mainPanel = new JPanel(new GridBagLayout());
        {
            mainPanel.setBackground(Color.BLACK);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JTextField nickField = new JTextField("");
            JButton acceptButton = new JButton("Save your result");
            acceptButton.addActionListener((e) -> {
                String textToSave = nickField.getText() + " " + playerAchivedScore;
                try {
                    FileWriter fw = new FileWriter("./save_file.txt", true);
                    fw.write(textToSave + "\n");
                    fw.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                    System.exit(-2);
                }
                new MainMenuFrame();
                dispose();
            });

            mainPanel.add(nickField, gbc);
            mainPanel.add(acceptButton, gbc);
        }

        add(titlePanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }
}