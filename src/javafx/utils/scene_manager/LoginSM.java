package javafx.utils.scene_manager;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.utils.drag.Draggable;

import java.io.IOException;

public class LoginSM extends SceneManager {

    private static LoginSM instance;


    /**
     * Constructor de la clase SceneManager.
     *
     * @param stage La ventana principal de la aplicación.
     */
    private LoginSM() {
        super(new Stage());
    }

    /**
     * Inicializa el controlador principal del gestor de escenas.
     *
     * @param stage El escenario principal.
     */
    private static LoginSM getInstance() {
        if (instance == null) {
            instance = new LoginSM();
            instance.stage.setResizable(false);
            instance.stage.initStyle(StageStyle.UNDECORATED);
        }

        if (!instance.stage.isShowing()) {
            instance.stage.show();
            instance.stage.centerOnScreen();
        }

        return instance;
    }

    public static Scene getActualScene() {
        return getInstance().actualScene;
    }

    public static void showLogin(boolean... cache) throws IOException {
        Scene scene = getInstance().generateScene(cache, "/login/Login.fxml");
        getInstance().stage.setScene(scene);
        Draggable.set(scene);
    }

    public static void close() {
        getInstance().stage.close();
    }

}
