package javafx.utils.scene_manager;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class LoginSM extends SceneManager{

    private static LoginSM instance;


    /**
     * Constructor de la clase SceneManager.
     *
     * @param stage La ventana principal de la aplicación.
     */
    private LoginSM(Stage stage) {
        super(stage);
    }

    public static Scene getActualScene() {
        return instance.actualScene;
    }

    /**
     * Inicializa el controlador principal del gestor de escenas.
     *
     * @param stage El escenario principal.
     */
    public static void initialize(@NotNull Stage stage) {
        if (instance == null)
            instance = new LoginSM(stage);
    }

    public static void showLogin(boolean...cache) throws IOException {
        Scene scene = instance.generateScene(cache, "/login/Login.fxml");
        instance.stage.setScene(scene);
    }


}
