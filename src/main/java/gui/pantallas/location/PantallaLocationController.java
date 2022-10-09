package gui.pantallas.location;

import domain.modelo.LocationItem;
import gui.pantallas.common.BasePantallaController;
import gui.pantallas.common.ConstantesPantallas;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
public class PantallaLocationController extends BasePantallaController {

    @FXML
    private Label lbBienvenido;
    @FXML
    private Label lbLocationInfo;
    @FXML
    private ImageView imgLogo;

    private final PantallaLocationViewModel locationViewModel;

    @Inject
    public PantallaLocationController(PantallaLocationViewModel locationViewModel) {
        this.locationViewModel = locationViewModel;
    }

    public void initialize() {
        try (var inputStream = getClass().getResourceAsStream(ConstantesPantallas.MEDIA_LOGO_PNG)) {
            assert inputStream != null;
            Image logoImage = new Image(inputStream);
            imgLogo.setImage(logoImage);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        locationViewModel.getState().addListener((observableValue, oldState, newState) -> {
            if (newState.onGoBack()) {
                this.getPrincipalController().goMainScreen();
            }
            if (newState.onDailyForecast()) {
                this.getPrincipalController().goDailyForecastScreen();
            }
            if (newState.on3HourlyForecast()) {
                this.getPrincipalController().go3HourlyForecastScreen();
            }
            if (newState.onHourlyForecast()) {
                this.getPrincipalController().goHourlyForecastScreen();
            }
        });
    }

    @Override
    public void principalCargado() {
        LocationItem location = getPrincipalController().getLocation();
        lbBienvenido.setText(location.getName());
        lbLocationInfo.setText(location.printLocationInfo());
    }

    @FXML
    private void onDailyForecast(ActionEvent actionEvent) {
        locationViewModel.onDailyForecast();
    }

    @FXML
    private void on3HourlyForecast(ActionEvent actionEvent) {
        locationViewModel.on3HourlyForecast();
    }

    @FXML
    private void onHourlyForecast(ActionEvent actionEvent) {
        locationViewModel.onHourlyForecast();
    }

    @FXML
    private void onGoBack(ActionEvent actionEvent) {
        locationViewModel.onGoBack();
    }
}
