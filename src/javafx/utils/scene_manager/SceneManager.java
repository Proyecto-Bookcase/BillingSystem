package javafx.utils.scene_manager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.utils.drag.Draggable;
import javafx.utils.others.Auxiliary;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;

/**
 * Clase abstracta SceneManager que maneja las escenas en una aplicación JavaFX.
 * Esta clase proporciona métodos para almacenar y recuperar escenas en un HashMap,
 * así como generar nuevas escenas a partir de archivos FXML.
 */
public class SceneManager {

    private static SceneManager instance;
    protected Stage stage;
    protected Scene actualScene;
    private final HashMap<String, Scene> store;

    private final HashMap<Scenes, String> scenes;

    /**
     * Constructor de la clase SceneManager.
     */
    protected SceneManager() {
        this.stage = new Stage();
        this.store = new HashMap<>();
        this.scenes = new HashMap<>();

        scenes.put(Scenes.LOGIN, "/login/Login.fxml");
        scenes.put(Scenes.HOME, "/home/Home.fxml");
        scenes.put(Scenes.ENTERPRISE_UPDATE, "/enterprise/update/UpdateEnterprise.fxml");
        scenes.put(Scenes.COMPANY_CREATE, "/company/create/CreateCompany.fxml");
    }

    private static SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }

    /**
     * Devuelve la escena almacenada en caché.
     *
     * @return La escena almacenada en caché.
     */
    protected Scene cache() {
        String key = Auxiliary.getCallingFunctionName(1);
        return store.get(key);
    }

    /**
     * Almacena la escena en caché.
     *
     * @param scene La escena a almacenar en caché.
     */
    private void cache(@NotNull Scene scene) {
        String key = Auxiliary.getCallingFunctionName(1);
        store.put(key, scene);
    }

    /**
     * Genera una nueva escena a partir de un archivo FXML.
     *
     * @param cache Un arreglo de booleanos indicando si se debe almacenar en caché la escena generada.
     * @return La escena generada.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    protected Scene generateScene(boolean[] cache, Scenes sceneEnum) throws IOException {

        String path = scenes.get(sceneEnum);

        Scene scene;
        if (cache.length > 0 && cache[0]) {
            scene = cache();
        } else {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(STR. "/javafx/scenes\{ path }" ));
            scene = new Scene(loader.load());
            cache(scene);
            actualScene = scene;
        }
        return scene;
    }

    /**
     * @param sceneEnum Scene que se quiere mostrar
     * @param cache     (Opcional) si se quiere guardar la scene en caché
     * @return
     */
    public static Scene show(Scenes sceneEnum, boolean... cache) {

        Scene scene = null;
        try {
            scene = getInstance().generateScene(cache, sceneEnum);
            getInstance().stage.setScene(scene);
            Draggable.set(scene);

            if (!getInstance().stage.isShowing()) {
                getInstance().stage.setResizable(false);
                getInstance().stage.initStyle(StageStyle.UNDECORATED);
                getInstance().stage.show();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return scene;
    }

    public static void close() {
        getInstance().stage.close();
    }
}