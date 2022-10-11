package gui.pantallas.three_hourly;

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

public class Pantalla3HourlyViewModel {
    private final ServiceForecast scForecast;
    private final ObjectProperty<Pantalla3HourlyState> state;

    private final ObservableList<ForecastHourlyItem> observableForecast;

    @Inject
    public Pantalla3HourlyViewModel(ServiceForecast scForecast) {
        this.scForecast = scForecast;
        state = new SimpleObjectProperty<>(new Pantalla3HourlyState(null, false,false));
        observableForecast = FXCollections.observableArrayList();
    }

    public ObjectProperty<Pantalla3HourlyState> getState() {
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
        state.set(new Pantalla3HourlyState(null, false,false));
    }

    public void onGoBack() {
        state.set(new Pantalla3HourlyState(null, true, false));
    }

    public void showHourlyDetail(ForecastHourlyItem forecastHourlyItem) {
        if (forecastHourlyItem != null) {
            state.set(new Pantalla3HourlyState(ConstantesPantallas.SELECCIONA_UN_REGISTRO_PRIMERO, false, false));
        }
        state.set(new Pantalla3HourlyState(null, false, true));
    }
}

