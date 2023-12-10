package javafx.utils.scene_manager;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.utils.drag.Draggable;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;


/**
 * Clase MainSM que extiende SceneManager.
 * Esta clase representa el controlador principal del gestor de escenas.
 * Proporciona métodos estáticos para mostrar diferentes escenas en el escenario principal.
 */
public class MainSM extends SceneManager {
    private static MainSM instance;

    /**
     * Constructor privado de MainSM.
     *
     * @param stage El escenario principal.
     */
    private MainSM() {
        super(new Stage());
    }

    /**
     * Obtiene la escena actual.
     *
     * @return La escena actual.
     */
    public static Scene getActualScene() {
        return getInstance().actualScene;
    }

    /**
     * Inicializa el controlador principal del gestor de escenas.
     *
     * @param stage El escenario principal.
     */
    private static MainSM getInstance() {
        if (instance == null) {
            instance = new MainSM();

            Stage stage = instance.stage;
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
        }

        if (!instance.stage.isShowing()) {
            instance.stage.show();
            instance.stage.centerOnScreen();
        }

        return instance;
    }

    public static void close() {
        getInstance().stage.close();
    }

    /**
     * Muestra la escena del panel de control.
     *
     * @param cache Indica si se debe utilizar la caché.
     * @throws IOException Si ocurre un error al cargar la escena.
     */
    public static void showDashBoard(boolean... cache) throws IOException {
        Scene scene = getInstance().generateScene(cache, "/login/Login.fxml");
        getInstance().stage.setScene(scene);
        Draggable.set(scene);
    }

    /**
     * Muestra la escena de creación de empresa.
     *
     * @param cache Indica si se debe utilizar la caché.
     * @throws IOException Si ocurre un error al cargar la escena.
     */
    public static void showCreateCompany(boolean... cache) {

        Scene scene = null;

        try {
            scene = getInstance().generateScene(cache, "/company/create/CreateCompany.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        getInstance().stage.setScene(scene);
        Draggable.set(scene);
    }

    /**
     * Muestra la escena de creación de carga.
     *
     * @param cache Indica si se debe utilizar la caché.
     * @throws IOException Si ocurre un error al cargar la escena.
     */
    public static void showCreateCargo(boolean... cache) throws IOException {
        Scene scene = getInstance().generateScene(cache, "/cargo/create/CreateCargo.fxml");
        getInstance().stage.setScene(scene);
        Draggable.set(scene);
    }
}
