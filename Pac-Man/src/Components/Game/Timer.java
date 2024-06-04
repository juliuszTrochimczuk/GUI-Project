package Components.Game;

public class Timer implements Runnable {
    private long seconds = 0;
    private GameThread mainThread;

    public Timer(GameThread mainThread) {
        this.mainThread = mainThread;
    }

    @Override
    public void run() {
        seconds = 0;
        while (mainThread.isThreadAlive) {
            try {
                Thread.sleep(1000);
                seconds += 1;
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    synchronized
    public long getSeconds() {
        return seconds;
    }
}