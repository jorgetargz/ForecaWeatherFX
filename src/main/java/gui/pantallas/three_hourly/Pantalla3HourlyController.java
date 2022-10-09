package gui.pantallas.three_hourly;

import domain.modelo.ForecastHourlyItem;
import domain.modelo.LocationItem;
import gui.pantallas.common.BasePantallaController;
import gui.pantallas.common.ConstantesPantallas;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
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
public class Pantalla3HourlyController extends BasePantallaController {

    private final Pantalla3HourlyViewModel threeHourlyViewModel;
    @FXML
    private Label lbBienvenido;
    @FXML
    private TableView<ForecastHourlyItem> tableForecast3Hourly;
    @FXML
    private TableColumn<ForecastHourlyItem, String> columnTime;
    @FXML
    private TableColumn<ForecastHourlyItem, Double> columTemp;
    @FXML
    private TableColumn<ForecastHourlyItem, Integer> columnFeelTemp;
    @FXML
    private TableColumn<ForecastHourlyItem, Integer> columnProbPre;
    @FXML
    private TableColumn<ForecastHourlyItem, Double> columnVelViento;
    @FXML
    private ImageView imgLogo;

    @Inject
    public Pantalla3HourlyController(Pantalla3HourlyViewModel threeHourlyViewModel) {
        this.threeHourlyViewModel = threeHourlyViewModel;
    }

    public void initialize() {
        try (var inputStream = getClass().getResourceAsStream(ConstantesPantallas.MEDIA_LOGO_PNG)) {
            assert inputStream != null;
            Image logoImage = new Image(inputStream);
            imgLogo.setImage(logoImage);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        columnTime.setCellValueFactory(new PropertyValueFactory<>(ConstantesPantallas.TIME));
        columTemp.setCellValueFactory(new PropertyValueFactory<>(ConstantesPantallas.TEMPERATURE));
        columnFeelTemp.setCellValueFactory(new PropertyValueFactory<>(ConstantesPantallas.FEELS_LIKE_TEMP));
        columnProbPre.setCellValueFactory(new PropertyValueFactory<>(ConstantesPantallas.PRECIP_PROB));
        columnVelViento.setCellValueFactory(new PropertyValueFactory<>(ConstantesPantallas.WIND_SPEED));
        tableForecast3Hourly.setItems(threeHourlyViewModel.getForecast());

        threeHourlyViewModel.getState().addListener((observableValue, oldState, newState) -> {
            if (newState.error() != null) {
                this.getPrincipalController().showAlert(Alert.AlertType.ERROR, ConstantesPantallas.ERROR, newState.error());
            }
            if (newState.onGoBack()) {
                this.getPrincipalController().goLocationScreen();
            }
        });
    }

    @Override
    public void principalCargado() {
        LocationItem actualLocation = getPrincipalController().getLocation();
        lbBienvenido.setText(actualLocation.getName());
        threeHourlyViewModel.loadForecast(actualLocation.getId());
    }

    @FXML
    private void onGoBack() {
        threeHourlyViewModel.onGoBack();
    }

}
