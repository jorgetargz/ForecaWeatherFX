package gui.pantallas.principal;


import domain.modelo.LocationItem;
import gui.pantallas.common.BasePantallaController;
import gui.pantallas.common.ConstantesPantallas;
import gui.pantallas.common.Pantallas;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;

@Log4j2
public class PrincipalController {

    final Instance<Object> instance;

    private Stage primaryStage;
    private final Alert alert;
    private LocationItem locationSelected;
    private double xOffset;
    private double yOffset;

    @FXML
    private BorderPane root;
    @FXML
    private HBox windowHeader;
    @FXML
    private MFXFontIcon closeIcon;
    @FXML
    private MFXFontIcon minimizeIcon;
    @FXML
    private MFXFontIcon alwaysOnTopIcon;
    @FXML
    private MenuBar menuPrincipal;


    @Inject
    public PrincipalController(Instance<Object> instance) {
        this.instance = instance;
        alert = new Alert(Alert.AlertType.NONE);
    }

    public void initialize() {
        closeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> showAlertConfirmClose());
        minimizeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Stage) root.getScene().getWindow()).setIconified(true));
        alwaysOnTopIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            boolean newVal = !primaryStage.isAlwaysOnTop();
            alwaysOnTopIcon.pseudoClassStateChanged(PseudoClass
                    .getPseudoClass(ConstantesPantallas.ALWAYS_ON_TOP), newVal);
            primaryStage.setAlwaysOnTop(newVal);
        });

        windowHeader.setOnMousePressed(event -> {
            xOffset = primaryStage.getX() - event.getScreenX();
            yOffset = primaryStage.getY() - event.getScreenY();
        });
        windowHeader.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() + xOffset);
            primaryStage.setY(event.getScreenY() + yOffset);
        });
        cargarPantalla(Pantallas.BIENVENIDA);
    }

    public void setStage(Stage stage) {
        primaryStage = stage;
    }

    private void showAlertConfirmClose() {
        Alert alertCerrar = new Alert(Alert.AlertType.WARNING);
        alertCerrar.getButtonTypes().remove(ButtonType.OK);
        alertCerrar.getButtonTypes().add(ButtonType.CANCEL);
        alertCerrar.getButtonTypes().add(ButtonType.YES);
        alertCerrar.setTitle(ConstantesPantallas.EXIT);
        alertCerrar.setContentText(ConstantesPantallas.SURE_EXIT);
        alertCerrar.initOwner(primaryStage.getOwner());
        Optional<ButtonType> res = alertCerrar.showAndWait();
        res.ifPresent(buttonType -> {
            if (buttonType == ButtonType.YES) {
                Platform.exit();
            }
        });
    }

    public void showAlert(Alert.AlertType alertType, String titulo, String mensaje) {
        alert.setAlertType(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public LocationItem getLocation() {
        return locationSelected;
    }

    private void cargarPantalla(Pantallas pantalla) {
        Pane panePantalla;
        ResourceBundle r = ResourceBundle.getBundle(ConstantesPantallas.I_18_N_TEXTS_UI);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(controller -> instance.select(controller).get());
            fxmlLoader.setResources(r);
            panePantalla = fxmlLoader.load(getClass().getResourceAsStream(pantalla.getRuta()));
            BasePantallaController pantallaController = fxmlLoader.getController();
            pantallaController.setPrincipalController(this);
            pantallaController.principalCargado();
            root.setCenter(panePantalla);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    @FXML
    private void goWelcome() {
        cargarPantalla(Pantallas.BIENVENIDA);
    }

    @FXML
    private void acercaDe() {
        showAlert(Alert.AlertType.INFORMATION, ConstantesPantallas.ABOUT, ConstantesPantallas.DATOS_AUTOR);
    }

    @FXML
    private void logout() {
        locationSelected = null;
        menuPrincipal.setVisible(false);
        cargarPantalla(Pantallas.BIENVENIDA);
    }

    @FXML
    private void exit() {
        showAlertConfirmClose();
    }

    @FXML
    private void cambiarcss() {
        if (primaryStage.getScene().getRoot().getStylesheets().stream().findFirst().isEmpty()
                || (primaryStage.getScene().getRoot().getStylesheets().stream().findFirst().isPresent()
                && primaryStage.getScene().getRoot().getStylesheets().stream().findFirst().get().contains(ConstantesPantallas.STYLE))) {
            try {
                primaryStage.getScene().getRoot().getStylesheets().clear();
                primaryStage.getScene().getRoot().getStylesheets().add(getClass().getResource(ConstantesPantallas.CSS_DARKMODE_CSS).toExternalForm());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        } else {
            try {
                primaryStage.getScene().getRoot().getStylesheets().clear();
                primaryStage.getScene().getRoot().getStylesheets().add(getClass().getResource(ConstantesPantallas.CSS_STYLE_CSS).toExternalForm());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    public void onLocationSelected(LocationItem locationItem) {
        locationSelected = locationItem;
        cargarPantalla(Pantallas.LOCATION);
    }

    public void goMainScreen() {
        cargarPantalla(Pantallas.BIENVENIDA);
    }

    public void goDailyForecastScreen() {
        cargarPantalla(Pantallas.DAILY);
    }

    public void go3HourlyForecastScreen() {
        cargarPantalla(Pantallas.THREE_HOURLY);
    }

    public void goHourlyForecastScreen() {
        cargarPantalla(Pantallas.HOURLY);
    }

    public void goLocationScreen() {
        cargarPantalla(Pantallas.LOCATION);
    }

}
