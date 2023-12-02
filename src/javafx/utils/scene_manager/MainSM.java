package javafx.utils.scene_manager;

import javafx.scene.Scene;
import javafx.stage.Stage;
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
    private MainSM(Stage stage) {
        super(stage);
    }

    /**
     * Obtiene la escena actual.
     *
     * @return La escena actual.
     */
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
            instance = new MainSM(stage);
    }

    /**
     * Muestra la escena del panel de control.
     *
     * @param cache Indica si se debe utilizar la caché.
     * @throws IOException Si ocurre un error al cargar la escena.
     */
    public static void showDashBoard(boolean... cache) throws IOException {
        Scene scene = instance.generateScene(cache, "/login/Login.fxml");
        instance.stage.setScene(scene);
    }

    /**
     * Muestra la escena de creación de empresa.
     *
     * @param cache Indica si se debe utilizar la caché.
     * @throws IOException Si ocurre un error al cargar la escena.
     */
    public static void showCreateCompany(boolean... cache) throws IOException {
        Scene scene = instance.generateScene(cache, "/company/create/CreateCompany.fxml");
        instance.stage.setScene(scene);
    }

    /**
     * Muestra la escena de creación de carga.
     *
     * @param cache Indica si se debe utilizar la caché.
     * @throws IOException Si ocurre un error al cargar la escena.
     */
    public static void showCreateCargo(boolean... cache) throws IOException {
        Scene scene = instance.generateScene(cache, "/cargo/create/CreateCargo.fxml");
        instance.stage.setScene(scene);
    }
}
