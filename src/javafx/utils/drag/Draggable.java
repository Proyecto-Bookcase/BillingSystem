package javafx.utils.drag;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Window;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Draggable {

    private final double[] xOffset;
    private final double[] yOffset;

    private Draggable(){
        xOffset = new double[1];
        yOffset = new double[1];
    }
    private Draggable(@NotNull Scene scene){
        this();
        Window stage = scene.getWindow();

        scene.setOnMousePressed(event -> {
            xOffset[0] = event.getSceneX();
            yOffset[0] = event.getSceneY();
        });

        scene.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset[0]);
            stage.setY(event.getScreenY() - yOffset[0]);
        });
    }

    private Draggable(@NotNull Node node){
        this();

        Window stage = node.getScene().getWindow();

        node.setOnMousePressed(event -> {
            xOffset[0] = event.getSceneX();
            yOffset[0] = event.getSceneY();
        });

        node.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset[0]);
            stage.setY(event.getScreenY() - yOffset[0]);
        });
    }

    @Contract("_ -> new")
    public static @NotNull Draggable set(Scene scene){
        return new Draggable(scene);
    }
}
