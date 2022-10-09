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
        state = new SimpleObjectProperty<>(new Pantalla3HourlyState(null, false));
        observableForecast = FXCollections.observableArrayList();
    }

    public ObjectProperty<Pantalla3HourlyState> getState() {
        return state;
    }

    public ObservableList<ForecastHourlyItem> getForecast() {
        return FXCollections.unmodifiableObservableList(observableForecast);
    }

    public void loadForecast(int locationId) {
        Either<String, List<ForecastHourlyItem>> eitherForecasts = scForecast.getHourlyForecast(3, locationId);
        if (eitherForecasts.isRight()) {
            List<ForecastHourlyItem> listForecast = eitherForecasts.get();
            if (listForecast.isEmpty()) {
                state.set(new Pantalla3HourlyState(ConstantesPantallas.NO_HAY_RESULTADOS, false));
            } else {
                observableForecast.clear();
                observableForecast.setAll(listForecast);
            }
        } else {
            state.set(new Pantalla3HourlyState(eitherForecasts.getLeft(), false));
        }
    }

    public void onGoBack() {
        state.set(new Pantalla3HourlyState(null, true));
    }
}

