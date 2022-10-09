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
        state = new SimpleObjectProperty<>(new PantallaHourlyState(null, false));
        observableForecast = FXCollections.observableArrayList();
    }

    public ObservableList<ForecastHourlyItem> getForecast() {
        return FXCollections.unmodifiableObservableList(observableForecast);
    }

    public void loadForecast(int locationId) {
        Either<String, List<ForecastHourlyItem>> eitherForecasts = scForecast.getHourlyForecast(1, locationId);
        if (eitherForecasts.isRight()) {
            List<ForecastHourlyItem> listForecast = eitherForecasts.get();
            if (listForecast.isEmpty()) {
                state.set(new PantallaHourlyState(ConstantesPantallas.NO_HAY_RESULTADOS, false));
            } else {
                observableForecast.clear();
                observableForecast.setAll(listForecast);
            }
        } else {
            state.setValue(new PantallaHourlyState(eitherForecasts.getLeft(), false));
        }
    }

    public ObjectProperty<PantallaHourlyState> getState() {
        return state;
    }

    public void onGoBack() {
        state.set(new PantallaHourlyState(null, true));
    }
}

