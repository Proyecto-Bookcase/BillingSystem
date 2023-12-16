package javafx.utils.async.thread;

import javafx.application.Platform;
import org.jetbrains.annotations.NotNull;

public class ThreadHelpers {

    /**
     * Start a thread
     *
     * @param func Function that executes when the thread is started
     * @return The started thread
     */
    public static @NotNull Thread thread(Runnable func) {

        Thread thread = new Thread(func);
        thread.start();

        return thread;
    }

    public static @NotNull Thread fxthread(Runnable func) {

        return thread(() -> {
            Platform.runLater(func);
        });
    }
}
