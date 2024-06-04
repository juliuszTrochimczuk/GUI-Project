package Components.Game;

import Components.Game.UI.UIGameFrame;

public class GameThread extends Thread {
    private UIGameFrame gameFrame;
    private GameWorld world;
    private Timer timer;
    private Thread timerThread;
    private Thread playerThread;

    public boolean isThreadAlive = true;

    public GameThread(int heightOfMap, int widthOfMap) {
        isThreadAlive = true;
        world = new GameWorld(heightOfMap, widthOfMap);
        gameFrame = new UIGameFrame(this, world);
        timer = new Timer(this);
        timerThread = new Thread(timer);
        playerThread = new Thread(world.getPlayer());
    }

    @Override
    public void run() {
        world.getPlayer().setMainThread(this);
        timerThread.start();
        playerThread.start();
        while (isThreadAlive) {
            try {
                gameFrame.updateUI(timer, world.getPlayer());
                gameFrame.checkIfTheGameEnds(world);
                gameFrame.revalidate();
                gameFrame.repaint();
                world.updateWorld();
                sleep(500);
            } catch (InterruptedException e) {
                timerThread.interrupt();
                playerThread.interrupt();
            }
        }
    }
}