package gravity;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class DrawingSim extends VBox {

    public DrawingSim(Scene scene, Settings settings) {

        Canvas canvas = new Canvas(settings.getMaxWidth(), settings.getMaxHeight());
        Slider maxSpeed = new Slider(0.1, 10, 1);

        List<Body> randomBodies = new ArrayList<>();
        int num = (int) (Math.round((settings.getMaxBalls() - settings.getMinBalls()) * Math.random()) + settings.getMinBalls());
        for (int i = 0; i < num; i++) {
            Body body = new Body();
            Color color = new Color(Math.random(), Math.random(), Math.random(), 1);
            body.getChildren().add(new Circle(settings.getBallSize(), color));
            body.setPosition(new Vector2D(
                    Math.random() * settings.getMaxWidth(),
                    Math.random() * settings.getMaxHeight()
            ));
            body.setVelocity(new Vector2D(
                    (Math.random() - 0.5) * 0.5,
                    (Math.random() - 0.5) * 0.5
            ));
            body.positionProperty().addListener(observable -> {
                Vector2D pos = body.getPosition();
                boolean changeX = false;
                boolean changeY = false;
                boolean xDirection = true;
                boolean yDirection = true;
                if (settings.isDraw()) {
                    canvas.getGraphicsContext2D().setFill(color);
                    canvas.getGraphicsContext2D().fillRect(pos.getX(), pos.getY(), 1, 1);
                }
                if (pos.getX() < 0) {
                    changeX = true;
                }
                if (pos.getY() < 0) {
                    changeY = true;
                }
                if (pos.getX() > settings.getMaxWidth()) {
                    changeX = true;
                    xDirection = false;
                }
                if (pos.getY() > settings.getMaxHeight()) {
                    changeY = true;
                    yDirection = false;
                }
                if (changeX || changeY) {
                    Vector2D vel = body.getVelocity();
                    double newVelX = vel.getX();
                    double newVelY = vel.getY();
                    if (changeX) {
                        if (xDirection) {
                            newVelX = Math.abs(vel.getX());
                        } else {
                            newVelX = Math.abs(vel.getX()) * -1;
                        }
                    }
                    if (changeY) {
                        if (yDirection) {
                            newVelY = Math.abs(vel.getY());
                        } else {
                            newVelY = Math.abs(vel.getY()) * -1;
                        }
                    }
                    body.setVelocity(new Vector2D(newVelX, newVelY));
                }
            });
            body.velocityProperty().addListener(observable -> {
                Vector2D vel = body.getVelocity();
                Vector2D accel = body.getAcceleration();
                double accelX = accel.getX();
                double accelY = accel.getY();
                double velX = vel.getX();
                double velY = vel.getY();
                boolean change = false;
                if (vel.getX() > maxSpeed.getValue() || vel.getX() < -maxSpeed.getValue()) {
                    accelX *= -1;
                    if (vel.getX() > maxSpeed.getValue()) {
                        velX = maxSpeed.getValue();
                    } else {
                        velX = -maxSpeed.getValue();
                    }
                    change = true;
                }
                if (vel.getY() > maxSpeed.getValue() || vel.getY() < -maxSpeed.getValue()) {
                    accelY *= -1;
                    change = true;
                    if (vel.getY() > maxSpeed.getValue()) {
                        velY = maxSpeed.getValue();
                    } else {
                        velY = -maxSpeed.getValue();
                    }
                }
                if (change) {
                    body.setAcceleration(new Vector2D(accelX, accelY));
                    body.setVelocity(new Vector2D(velX, velY));
                }

            });
            body.setAcceleration(new Vector2D(
                    (Math.random() - 0.5) * 0.1,
                    (Math.random() - 0.5) * 0.1
            ));
            randomBodies.add(body);
        }

        BodyManager manager = new BodyManager(settings);
        manager.getManaged().addAll(randomBodies);

        Pane pane = new Pane(canvas);
        pane.getChildren().addAll(randomBodies);
        pane.setOnMousePressed(event -> manager.setRunning(!manager.isRunning()));
        getChildren().addAll(pane, maxSpeed);

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.X) {
                canvas.getGraphicsContext2D().clearRect(0, 0, 1000, 1000);
                settings.setDraw(!settings.isDraw());
            }
        });
    }

}
