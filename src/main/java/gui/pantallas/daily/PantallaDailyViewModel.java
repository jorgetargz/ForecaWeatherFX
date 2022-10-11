package gui.pantallas.daily;

import domain.modelo.ForecastDailyItem;
import domain.services.ServiceForecast;
import gui.pantallas.common.ConstantesPantallas;
import io.reactivex.rxjava3.schedulers.Schedulers;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class PantallaDailyViewModel {

    private final ServiceForecast scForecast;
    private final ObjectProperty<PantallaDailyState> state;

    private final ObservableList<ForecastDailyItem> observableForecast;

    @Inject
    public PantallaDailyViewModel(ServiceForecast scForecast) {
        this.scForecast = scForecast;
        state = new SimpleObjectProperty<>(new PantallaDailyState(null, false, false, false, false));
        observableForecast = FXCollections.observableArrayList();
    }

    public ObjectProperty<PantallaDailyState> getState() {
        return state;
    }

    public ObservableList<ForecastDailyItem> getForecast() {
        return FXCollections.unmodifiableObservableList(observableForecast);
    }

    public void loadForecast(int locationId) {
        state.set(new PantallaDailyState(null, false, true, false, false));
        scForecast.getDailyForecast(locationId)
                .observeOn(Schedulers.single())
                .subscribe(either -> {
                    if (either.isLeft())
                        state.set(new PantallaDailyState(either.getLeft(), false, false, true, false));
                    else {
                        observableForecast.clear();
                        observableForecast.setAll(either.get());
                        state.set(new PantallaDailyState(null, false, false, true, false));
                    }
                });
    }

    public void onGoBack() {
        state.set(new PantallaDailyState(null, true, false, false, false));
    }

    public void clearState() {
        state.set(new PantallaDailyState(null, false, false, false, false));
    }

    public void onShowDetail(ForecastDailyItem forecastDailyItem) {
        if (forecastDailyItem != null) {
            state.set(new PantallaDailyState(null, false, false, false, true));
        } else {
            state.set(new PantallaDailyState(ConstantesPantallas.SELECCIONA_UN_REGISTRO_PRIMERO, false, false, false, false));
        }
    }
}
