package javafx.scenes.home.subscenes.manager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.HashMap;

import static javafx.utils.async.thread.ThreadHelpers.fxthread;

/**
 * Clase abstracta SceneManager que maneja las escenas en una aplicación JavaFX.
 * Esta clase proporciona métodos para almacenar y recuperar escenas en un HashMap,
 * así como generar nuevas escenas a partir de archivos FXML.
 */
public class HomeSceneManager {

    public static Object store;
    private static HomeSceneManager instance;

    protected Pane scene;
    protected Pane actualPane;
    private final HashMap<Scenes, String> scenes;

    /**
     * Constructor de la clase SceneManager.
     */
    private HomeSceneManager(Pane scene) {
        this.scene = scene;
        this.scenes = new HashMap<>();

        scenes.put(Scenes.ENTERPRISE, "/enterprise/Enterprise.fxml");

        scenes.put(Scenes.COMPANIES, "/companies/Companies.fxml");
        scenes.put(Scenes.COMPANY_DELETE, "/companies/delete/CompanyDelete.fxml");

        scenes.put(Scenes.CLIENTS, "/clients/Warehouses.fxml");
        scenes.put(Scenes.CLIENT_DELETE, "/clients/delete/ClientDelete.fxml");

        scenes.put(Scenes.CARGOS, "/cargos/Cargos.fxml");
        scenes.put(Scenes.CARGO_DELETE, "/cargos/delete/CargoDelete.fxml");

        scenes.put(Scenes.WAREHOUSES, "/warehouses/Warehouses.fxml");

    }

    public static void initialize(Pane pane) {
        instance = new HomeSceneManager(pane);
    }

    /**
     * Genera una nueva escena a partir de un archivo FXML.
     *
     * @return La escena generada.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    protected Parent generatePane(Scenes sceneEnum) throws IOException {
        String path = scenes.get(sceneEnum);
        FXMLLoader loader = new FXMLLoader(HomeSceneManager.class.getResource(STR. "/javafx/scenes/home/subscenes/scenes\{ path }" ));
        return loader.load();
    }

    /**
     * @param sceneEnum Scene que se quiere mostrar
     */
    public static void to(Scenes sceneEnum) {

        fxthread(() -> {

            try {
                Parent p = instance.generatePane(sceneEnum);
                instance.scene.getChildren().clear();
                instance.scene.getChildren().add(p);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        if (!instance.scene.isVisible()) instance.scene.setVisible(true);
    }

    public static void to(Scenes sceneEnum, boolean over) {

        fxthread(() -> {

            try {
                Parent p = instance.generatePane(sceneEnum);
                if (!over) {
                    instance.scene.getChildren().clear();
                }
                instance.scene.getChildren().add(p);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        if (!instance.scene.isVisible()) instance.scene.setVisible(true);
    }
}