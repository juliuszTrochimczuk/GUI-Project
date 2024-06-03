package Components.Game;

import Components.Game.UI.UIGameFrame;

public class GameThread extends Thread {
    private UIGameFrame gameFrame;
    private GameWorld world;
    private Timer timer;
    private Thread timerThread;
    private Thread playerThread;

    public GameThread(int heightOfMap, int widthOfMap) {
        world = new GameWorld(heightOfMap, widthOfMap);
        gameFrame = new UIGameFrame(this, world);
        timer = new Timer();
        timerThread = new Thread(timer);
        playerThread = new Thread(world.getPlayer());
    }

    @Override
    public void run() {
        timerThread.start();
        playerThread.start();
        while (!currentThread().isInterrupted()) {
            gameFrame.updateUI(timer);
            gameFrame.revalidate();
            gameFrame.repaint();
            world.updateWorld();
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                currentThread().interrupt();
                timerThread.interrupt();
                playerThread.interrupt();
            }
        }
    }
}
