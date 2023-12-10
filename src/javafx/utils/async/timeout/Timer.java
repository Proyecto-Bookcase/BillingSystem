package javafx.utils.async.timeout;

import java.util.concurrent.TimeUnit;

public abstract class Timer {

    public static void timeout(final long millis, final Runnable runnable) {
        new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            runnable.run();
        }).start();
    }
}
