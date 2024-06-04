package Components;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class SaveDataController {
    private static SaveDataController instance;

    private ArrayList<ScoreData> data = new ArrayList<>();

    private SaveDataController() {
        if (data.isEmpty())
            loadDataFromFile();
    }

    public static SaveDataController getInstance() {
        if (instance == null)
            instance = new SaveDataController();
        return instance;
    }

    public void addNewSaveData(ScoreData data) {
        this.data.add(data);
        Collections.sort(this.data);
    }

    public ArrayList<ScoreData> getData() {
        return data;
    }

    public int getHighestScore() {
        return data.get(0).getScore();
    }

    public void saveDataInFile()  {
        try {
            FileOutputStream fos = new FileOutputStream("./save_file.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
            oos.close();
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadDataFromFile() {
        try {
            FileInputStream fis = new FileInputStream("./save_file.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            data = (ArrayList<ScoreData>)ois.readObject();
            System.out.println(data);
            ois.close();
            fis.close();
        } catch (ClassNotFoundException | FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
}
