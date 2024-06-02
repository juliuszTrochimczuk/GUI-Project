package Components.Game;

public class Timer implements Runnable {
    private long seconds = 0;

    @Override
    public void run() {
        seconds = 0;
        while (true) {
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
