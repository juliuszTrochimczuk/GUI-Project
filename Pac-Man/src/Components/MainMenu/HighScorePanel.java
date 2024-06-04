package Components.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

public class HighScorePanel extends JPanel {
    private JButton returnToOpeningPanelButton;

    public HighScorePanel() {
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());

        ArrayList<SaveData> data = new ArrayList();
        try {
            File myObj = new File("./save_file.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                if (!Objects.equals(line, "")) {
                    String[] splitedLine = line.split(" ");
                    String nick = splitedLine[0];
                    int score = Integer.parseInt(splitedLine[1]);
                    data.add(new SaveData(score, nick));
                }
            }
            myReader.close();

            Collections.sort(data);
            String[] dataArr = new String[data.size()];
            for (int i = 0; i < dataArr.length; i++) dataArr[i] = data.get(i).getData();

            JList<String> list = new JList<>(dataArr);
            list.setForeground(Color.ORANGE);
            list.setBackground(Color.BLACK);
            JScrollPane scrollPane = new JScrollPane(list);

            add(scrollPane, BorderLayout.CENTER);
        } catch (IOException ex) {
        }

        returnToOpeningPanelButton = new JButton("Return to Main Menu");
        add(returnToOpeningPanelButton, BorderLayout.SOUTH);
    }

    public void addActionListenerToReturnToOpeningPanelButton(ActionListener actionListener) {
        returnToOpeningPanelButton.addActionListener(actionListener);
    }

    private class SaveData implements Comparable<SaveData> {
        private int score;
        private String nick;

        public SaveData(int score, String nick) {
            this.score = score;
            this.nick = nick;
        }

        public String getData() {
            return nick + " " + score;
        }

        @Override
        public int compareTo(SaveData o) {
            return o.score - score;
        }
    }
}