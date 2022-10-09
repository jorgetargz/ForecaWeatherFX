package gui.pantallas.location;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class PantallaLocationViewModel {

    private final ObjectProperty<PantallaLocationState> state;

    public PantallaLocationViewModel() {
        this.state = new SimpleObjectProperty<>(new PantallaLocationState(false, false, false, false));
    }

    public ReadOnlyObjectProperty<PantallaLocationState> getState() {
        return state;
    }

    public void onGoBack() {
        state.set(new PantallaLocationState(false, false, false, true));
    }

    public void onDailyForecast() {
        state.set(new PantallaLocationState(true, false, false, false));
    }

    public void on3HourlyForecast() {
        state.set(new PantallaLocationState(false, true, false, false));
    }

    public void onHourlyForecast() {
        state.set(new PantallaLocationState(false, false, true, false));
    }
}
