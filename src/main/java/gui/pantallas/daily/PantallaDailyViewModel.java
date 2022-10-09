package gui.pantallas.daily;

import domain.modelo.ForecastDailyItem;
import domain.services.ServiceForecast;
import gui.pantallas.common.ConstantesPantallas;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class PantallaDailyViewModel {

    private final ServiceForecast scForecast;
    private final ObjectProperty<PantallaDailyState> state;

    private final ObservableList<ForecastDailyItem> observableForecast;

    @Inject
    public PantallaDailyViewModel(ServiceForecast scForecast) {
        this.scForecast = scForecast;
        state = new SimpleObjectProperty<>(new PantallaDailyState(null, false));
        observableForecast = FXCollections.observableArrayList();
    }

    public ObjectProperty<PantallaDailyState> getState() {
        return state;
    }

    public ObservableList<ForecastDailyItem> getForecast() {
        return FXCollections.unmodifiableObservableList(observableForecast);
    }

    public void loadForecast(int locationId) {
        Either<String, List<ForecastDailyItem>> eitherForecasts = scForecast.getDailyForecast(locationId);
        if (eitherForecasts.isRight()) {
            List<ForecastDailyItem> listForecast = eitherForecasts.get();
            if (listForecast.isEmpty()) {
                state.set(new PantallaDailyState(ConstantesPantallas.NO_HAY_RESULTADOS, false));
            } else {
                observableForecast.clear();
                observableForecast.setAll(listForecast);
            }
        } else {
            state.setValue(new PantallaDailyState(eitherForecasts.getLeft(), false));
        }
    }

    public void onGoBack() {
        state.set(new PantallaDailyState(null, true));
    }
}
