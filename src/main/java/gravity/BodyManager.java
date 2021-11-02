package gravity;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.ArrayList;
import java.util.List;

public class BodyManager {

    private final BooleanProperty running = new SimpleBooleanProperty(false);
    private final DoubleProperty time = new SimpleDoubleProperty();
    private final List<Body> managed = new ArrayList<>();

    public BodyManager(Settings settings) {
        Thread timeThread = new Thread() {

            long lastPush = System.currentTimeMillis();

            @Override
            public void run() {
                while (true) {
                    long newTime = System.currentTimeMillis();
                    long deltaTime = newTime - lastPush;
                    lastPush = newTime;
                    if (isRunning()) {
                        time.set(time.get() + (deltaTime / 1000D));
                        Platform.runLater(() -> {
                            managed.forEach(body -> body.timePassed(deltaTime / 1000D));
                        });
                    }
                    if (deltaTime < settings.getMinFrameLength()) {
                        try {
                            Thread.sleep(settings.getMinFrameLength() - deltaTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
        timeThread.setDaemon(true);
        timeThread.start();
    }

    public double getTime() {
        return time.get();
    }

    public DoubleProperty timeProperty() {
        return time;
    }

    public void setTime(double time) {
        this.time.set(time);
    }

    public boolean isRunning() {
        return running.get();
    }

    public BooleanProperty runningProperty() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running.set(running);
    }

    public List<Body> getManaged() {
        return managed;
    }

}
