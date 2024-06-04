package Components;

import java.io.Serializable;

public class ScoreData implements Comparable<ScoreData>, Serializable {
    private int score;
    private String nick;

    public ScoreData(int score, String nick) {
        this.score = score;
        this.nick = nick;
    }

    public int getScore() {
        return score;
    }

    public String getData() {
        return nick + " " + score;
    }

    @Override
    public int compareTo(ScoreData o) {
        return o.score - score;
    }

    @Override
    public String toString() {
        return nick + " " + score;
    }
}