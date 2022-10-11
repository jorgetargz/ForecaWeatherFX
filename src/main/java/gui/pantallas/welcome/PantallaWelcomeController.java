package gui.pantallas.welcome;

import domain.modelo.LocationItem;
import gui.pantallas.common.BasePantallaController;
import gui.pantallas.common.ConstantesPantallas;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.List;

@Log4j2
public class PantallaWelcomeController extends BasePantallaController {

    @FXML
    private ImageView imgLogo;
    @FXML
    private MFXButton btnSelect;
    @FXML
    private TableView<LocationItem> tableLocations;
    @FXML
    private TableColumn<LocationItem, String> columName;
    @FXML
    private TableColumn<LocationItem, String> columCountry;
    @FXML
    private TableColumn<LocationItem, String> columnAdminArea;
    @FXML
    private MFXTextField txtLocationName;
    @FXML
    private Label lbBienvenido;

    private final PantallaWelcomeViewModel welcomeViewModel;

    @Inject
    public PantallaWelcomeController(PantallaWelcomeViewModel welcomeViewModel) {
        this.welcomeViewModel = welcomeViewModel;
    }

    public void initialize() {
        String bienvenida = ConstantesPantallas.JAVA_FX;
        lbBienvenido.setText(bienvenida);

        try (var inputStream = getClass().getResourceAsStream(ConstantesPantallas.MEDIA_LOGO_PNG)) {
            assert inputStream != null;
            Image logoImage = new Image(inputStream);
            imgLogo.setImage(logoImage);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        columName.setCellValueFactory(new PropertyValueFactory<>(ConstantesPantallas.NAME));
        columCountry.setCellValueFactory(new PropertyValueFactory<>(ConstantesPantallas.COUNTRY));
        columnAdminArea.setCellValueFactory(new PropertyValueFactory<>(ConstantesPantallas.ADMIN_AREA));

        tableLocations.setItems(welcomeViewModel.getLocations());
        btnSelect.setVisible(false);

        welcomeViewModel.getState().addListener((observableValue, oldState, newState) -> {
            if (newState.error() != null) {
                this.getPrincipalController().showAlert(Alert.AlertType.ERROR, ConstantesPantallas.ERROR, newState.error());
            }
            if (newState.locationSelected()) {
                this.getPrincipalController().onLocationSelected(tableLocations.getSelectionModel().getSelectedItem());
            }
        });
    }

    @FXML
    private void updateFields() {
        LocationItem location = tableLocations.getSelectionModel().getSelectedItem();
        if (location != null) {
            btnSelect.setVisible(true);
            btnSelect.setText(location.getName());
        }
    }

    @FXML
    private void onLocationSelected() {
        LocationItem location = tableLocations.getSelectionModel().getSelectedItem();
        if (location != null) {
            welcomeViewModel.onLocationSelected();
        }
    }

    @FXML
    private void onLocationSearch() {
        getPrincipalController().root.setCursor(Cursor.WAIT);
        var task = new Task<Either<String, List<LocationItem>>>() {
            @Override
            protected Either<String, List<LocationItem>> call() {
                String name = txtLocationName.getText();
                return welcomeViewModel.getLocations(name);
            }
        };
        task.setOnSucceeded(workerStateEvent -> {
            getPrincipalController().root.setCursor(Cursor.DEFAULT);
            var result = task.getValue();
            result.peek(locationItems -> {
                if (locationItems.isEmpty()) {
                    getPrincipalController().showAlert(Alert.AlertType.INFORMATION, ConstantesPantallas.INFO, ConstantesPantallas.NO_HAY_RESULTADOS);
                } else {
                    welcomeViewModel.loadLocations(locationItems);
                }
            }).peekLeft(error -> getPrincipalController().showAlert(Alert.AlertType.ERROR, error, error));
        });
        task.setOnFailed(workerStateEvent -> {
            getPrincipalController().showAlert(Alert.AlertType.ERROR, ConstantesPantallas.ERROR, task.getException().getMessage());
            getPrincipalController().root.setCursor(Cursor.DEFAULT);
        });
        new Thread(task).start();
        welcomeViewModel.clearState();
    }

}
