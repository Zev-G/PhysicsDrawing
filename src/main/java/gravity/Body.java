package gravity;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;

public class Body extends Parent {

    private final ObjectProperty<Vector2D> position = new SimpleObjectProperty<>(Vector2D.ZERO);
    private final ObjectProperty<Vector2D> velocity = new SimpleObjectProperty<>(Vector2D.ZERO);
    private final ObjectProperty<Vector2D> acceleration = new SimpleObjectProperty<>(Vector2D.ZERO);

    private final DoubleProperty mass = new SimpleDoubleProperty(0);

    public Body() {
        position.addListener(observable -> {
            Vector2D newPosition = position.get();
            if (newPosition == null) {
                position.set(new Vector2D(0, 0));
                return;
            }
            setLayoutX(newPosition.getX());
            setLayoutY(newPosition.getY());
        });
    }

    public void timePassed(double time) {
        // v = v0 + at;
        velocity.set(
                velocity.get().add(
                        acceleration.get().add(
                                acceleration.get().multiply(time)
                        )
                )
        );
        // d = d0 + vt;
        position.set(
                position.get().add(
                    velocity.get().add(
                            velocity.get().multiply(time)
                    )
                )
        );
    }

    public Vector2D getPosition() {
        return position.get();
    }

    public ObjectProperty<Vector2D> positionProperty() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position.set(position);
    }

    public Vector2D getVelocity() {
        return velocity.get();
    }

    public ObjectProperty<Vector2D> velocityProperty() {
        return velocity;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity.set(velocity);
    }

    public Vector2D getAcceleration() {
        return acceleration.get();
    }

    public ObjectProperty<Vector2D> accelerationProperty() {
        return acceleration;
    }

    public void setAcceleration(Vector2D acceleration) {
        this.acceleration.set(acceleration);
    }

    public double getMass() {
        return mass.get();
    }

    public DoubleProperty massProperty() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass.set(mass);
    }

    @Override
    public ObservableList<Node> getChildren() {
        return super.getChildren();
    }

}
