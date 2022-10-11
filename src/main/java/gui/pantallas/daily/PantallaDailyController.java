package gui.pantallas.daily;

import domain.modelo.ForecastDailyItem;
import domain.modelo.LocationItem;
import gui.pantallas.common.BasePantallaController;
import gui.pantallas.common.ConstantesPantallas;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
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

@Log4j2
public class PantallaDailyController extends BasePantallaController {

    private final PantallaDailyViewModel dailyViewModel;

    @FXML
    private Label lbBienvenido;
    @FXML
    private TableView<ForecastDailyItem> tableForecastDaily;
    @FXML
    private TableColumn<ForecastDailyItem, String> columnDate;
    @FXML
    private TableColumn<ForecastDailyItem, Integer> columMaxTemp;
    @FXML
    private TableColumn<ForecastDailyItem, Integer> columnMinTemp;
    @FXML
    private TableColumn<ForecastDailyItem, Integer> columnVelViento;

    @FXML
    private ImageView imgLogo;

    @Inject
    public PantallaDailyController(PantallaDailyViewModel viewModel) {
        this.dailyViewModel = viewModel;
    }

    public void initialize() {
        try (var inputStream = getClass().getResourceAsStream(ConstantesPantallas.MEDIA_LOGO_PNG)) {
            assert inputStream != null;
            Image logoImage = new Image(inputStream);
            imgLogo.setImage(logoImage);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }


        columnDate.setCellValueFactory(new PropertyValueFactory<>(ConstantesPantallas.DATE));
        columMaxTemp.setCellValueFactory(new PropertyValueFactory<>(ConstantesPantallas.MAX_TEMP));
        columnMinTemp.setCellValueFactory(new PropertyValueFactory<>(ConstantesPantallas.MIN_TEMP));
        columnVelViento.setCellValueFactory(new PropertyValueFactory<>(ConstantesPantallas.MAX_WIND_SPEED));
        tableForecastDaily.setItems(dailyViewModel.getForecast());

        dailyViewModel.getState().addListener((observableValue, oldState, newState) -> {
            if (newState.error() != null) {
                this.getPrincipalController().showAlert(Alert.AlertType.ERROR, ConstantesPantallas.ERROR, newState.error());
            }
            if (newState.onShowDetail()) {
                ForecastDailyItem forecastItem = tableForecastDaily.getSelectionModel().getSelectedItem();
                this.getPrincipalController().showAlert(Alert.AlertType.INFORMATION, ConstantesPantallas.DETAIL, forecastItem.toString());
            }
            if (newState.onGoBack()) {
                this.getPrincipalController().goLocationScreen();
            }
            if (newState.isLoading()) {
                this.getPrincipalController().root.setCursor(Cursor.WAIT);
            }
            if (newState.isLoaded()) {
                this.getPrincipalController().root.setCursor(Cursor.DEFAULT);
            }
        });
    }

    @Override
    public void principalCargado() {
        LocationItem actualLocation = getPrincipalController().getLocation();
        lbBienvenido.setText(actualLocation.getName());
        dailyViewModel.loadForecast(actualLocation.getId());
    }

    @FXML
    private void onGoBack() {
        dailyViewModel.onGoBack();
    }

    @FXML
    private void onShowDetail() {
        ForecastDailyItem forecastDailyItem = tableForecastDaily.getSelectionModel().getSelectedItem();
            dailyViewModel.onShowDetail(forecastDailyItem);
        dailyViewModel.clearState();
    }
}
