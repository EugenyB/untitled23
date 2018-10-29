package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Controller {
    @FXML private Canvas canvas;
    @FXML private Pane pane;

    Timeline timeline;

    int y = 20;
    int x = 100;

    @FXML
    public void initialize() {
        canvas.widthProperty().bind(pane.widthProperty());
        canvas.heightProperty().bind(pane.heightProperty());
        canvas.widthProperty().addListener(e->draw());
        canvas.heightProperty().addListener(e->draw());
        timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> processTick()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        pane.requestFocus();
    }

    private void processTick() {
        y++;
        draw();
    }

    private void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITESMOKE);
        gc.fillRect(0,0,canvas.getWidth(), canvas.getHeight());
        gc.setStroke(Color.BLUE);
        gc.strokeText("A", x, y);
    }

    public void processKey(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case LEFT: x--; break;
            case RIGHT: x++; break;
        }
    }
}
