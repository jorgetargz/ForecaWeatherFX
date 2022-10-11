package gui.pantallas.hourly;

import domain.modelo.ForecastHourlyItem;
import domain.services.ServiceForecast;
import gui.pantallas.common.ConstantesPantallas;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class PantallaHourlyViewModel {
    private final ServiceForecast scForecast;
    private final ObjectProperty<PantallaHourlyState> state;

    private final ObservableList<ForecastHourlyItem> observableForecast;

    @Inject
    public PantallaHourlyViewModel(ServiceForecast scForecast) {
        this.scForecast = scForecast;
        state = new SimpleObjectProperty<>(new PantallaHourlyState(null, false, false));
        observableForecast = FXCollections.observableArrayList();
    }

    public ObjectProperty<PantallaHourlyState> getState() {
        return state;
    }

    public ObservableList<ForecastHourlyItem> getObservableForecast() {
        return FXCollections.unmodifiableObservableList(observableForecast);
    }

    public Either<String, List<ForecastHourlyItem>> getForecast(int locationId) {
        return scForecast.getHourlyForecast(3, locationId);
    }

    public void loadForecast(List<ForecastHourlyItem> forecast) {
        observableForecast.clear();
        observableForecast.setAll(forecast);
    }

    public void clearState() {
        state.set(new PantallaHourlyState(null, false, false));
    }


    public void showHourlyDetail(ForecastHourlyItem forecastHourlyItem) {
        if (forecastHourlyItem != null) {
            state.set(new PantallaHourlyState(null, false, true));
        } else {
            state.set(new PantallaHourlyState(ConstantesPantallas.SELECCIONA_UN_REGISTRO_PRIMERO, false, false));
        }
    }

    public void onGoBack() {
        state.set(new PantallaHourlyState(null, true, false));
    }
}

