package Components.Game;

import Components.Game.UI.UIGameFrame;

public class GameThread extends Thread {
    private UIGameFrame gameFrame;
    private GameWorld world;
    private Timer gameTimer;
    private Thread gameTimerThread;

    public GameThread(int heightOfMap, int widthOfMap) {
        world = new GameWorld(heightOfMap, widthOfMap);
        gameFrame = new UIGameFrame(this, world);
        gameTimerThread = new Thread(gameTimer);
    }

    @Override
    public void run() {
        gameTimerThread.start();
        while (!currentThread().isInterrupted()) {
            System.out.println("Game is running");
            gameFrame.revalidate();
            gameFrame.repaint();
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                currentThread().interrupt();
                gameTimerThread.interrupt();
            }
        }
    }
}
