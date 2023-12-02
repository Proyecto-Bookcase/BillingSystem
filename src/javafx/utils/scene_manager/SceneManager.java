package javafx.utils.scene_manager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.utils.others.Aux;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;

import static java.lang.StringTemplate.STR;

/**
 * Clase abstracta SceneManager que maneja las escenas en una aplicación JavaFX.
 * Esta clase proporciona métodos para almacenar y recuperar escenas en un HashMap,
 * así como generar nuevas escenas a partir de archivos FXML.
 */
public abstract class SceneManager {
    protected Stage stage;
    protected Scene actualScene;
    private final HashMap<String, Scene> store;

    /**
     * Constructor de la clase SceneManager.
     *
     * @param stage La ventana principal de la aplicación.
     */
    protected SceneManager(Stage stage) {
        this.stage = stage;
        this.store = new HashMap<>();
    }

    /**
     * Devuelve la escena almacenada en caché.
     *
     * @return La escena almacenada en caché.
     */
    protected Scene cache() {
        String key = Aux.getCallingFunctionName(1);
        return store.get(key);
    }

    /**
     * Almacena la escena en caché.
     *
     * @param scene La escena a almacenar en caché.
     */
    private void cache(@NotNull Scene scene) {
        String key = Aux.getCallingFunctionName(1);
        store.put(key, scene);
    }

    /**
     * Genera una nueva escena a partir de un archivo FXML.
     *
     * @param cache Un arreglo de booleanos indicando si se debe almacenar en caché la escena generada.
     * @param path  La ruta del archivo FXML.
     * @return La escena generada.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    protected Scene generateScene(boolean[] cache, String path) throws IOException {
        Scene scene;
        if (cache.length > 0 && cache[0]) {
            scene = cache();
        } else {
            FXMLLoader loader = new FXMLLoader(MainSM.class.getResource(STR. "/javafx/scenes\{ path }" ));
            scene = new Scene(loader.load());
            cache(scene);
            actualScene = scene;
        }
        return scene;
    }
}