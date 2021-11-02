package gravity;

public class Settings {

    private double maxWidth = 1000;
    private double maxHeight = 1000;

    private double ballSize = 3;
    private int minBalls = 1000;
    private int maxBalls = minBalls;

    private boolean draw = false;

    private long minFrameLength = 5;

    public Settings() {

    }
    public Settings(double maxWidth, double maxHeight, double ballSize, int minBalls, int maxBalls, boolean draw, long minFrameLength) {
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.ballSize = ballSize;
        this.minBalls = minBalls;
        this.maxBalls = maxBalls;
        this.draw = draw;
        this.minFrameLength = minFrameLength;
    }

    public double getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(double maxWidth) {
        this.maxWidth = maxWidth;
    }

    public double getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(double maxHeight) {
        this.maxHeight = maxHeight;
    }

    public double getBallSize() {
        return ballSize;
    }

    public void setBallSize(double ballSize) {
        this.ballSize = ballSize;
    }

    public int getMinBalls() {
        return minBalls;
    }

    public void setMinBalls(int minBalls) {
        this.minBalls = minBalls;
    }

    public int getMaxBalls() {
        return maxBalls;
    }

    public void setMaxBalls(int maxBalls) {
        this.maxBalls = maxBalls;
    }

    public boolean isDraw() {
        return draw;
    }

    public void setDraw(boolean draw) {
        this.draw = draw;
    }

    public long getMinFrameLength() {
        return minFrameLength;
    }

    public void setMinFrameLength(long minFrameLength) {
        this.minFrameLength = minFrameLength;
    }

}
