package gravity;

import java.util.Objects;

public class Vector2D {

    public static Vector2D ZERO = new Vector2D(0);

    private final double x;
    private final double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(double pos) {
        this.x = pos;
        this.y = pos;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Vector2D add(double other) {
        return add(new Vector2D(other));
    }
    public Vector2D add(Vector2D other) {
        return new Vector2D(
                x + other.x,
                y + other.y
        );
    }
    public Vector2D subtract(double other) {
        return subtract(new Vector2D(other));
    }
    public Vector2D subtract(Vector2D other) {
        return new Vector2D(
                x - other.x,
                y - other.y
        );
    }
    public Vector2D multiply(double other) {
        return multiply(new Vector2D(other));
    }
    public Vector2D multiply(Vector2D other) {
        return new Vector2D(
                x * other.x,
                y * other.y
        );
    }
    public Vector2D divide(double other) {
        return divide(new Vector2D(other));
    }
    public Vector2D divide(Vector2D other) {
        return new Vector2D(
                x / other.x,
                y / other.y
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2D vector2D = (Vector2D) o;
        return Double.compare(vector2D.x, x) == 0 && Double.compare(vector2D.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}
