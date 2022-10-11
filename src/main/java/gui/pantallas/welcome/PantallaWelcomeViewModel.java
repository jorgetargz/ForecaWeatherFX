package gui.pantallas.welcome;

import domain.modelo.LocationItem;
import domain.services.ServicesLocations;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class PantallaWelcomeViewModel {

    private final ServicesLocations scLocations;
    private final ObjectProperty<PantallaWelcomeState> state;
    private final ObservableList<LocationItem> observableLocations;

    @Inject
    public PantallaWelcomeViewModel(ServicesLocations scLocations) {
        this.scLocations = scLocations;
        state = new SimpleObjectProperty<>(new PantallaWelcomeState(null, false));
        observableLocations = FXCollections.observableArrayList();
    }

    public ReadOnlyObjectProperty<PantallaWelcomeState> getState() {
        return state;
    }

    public ObservableList<LocationItem> getLocations() {
        return FXCollections.unmodifiableObservableList(observableLocations);
    }

    public Either<String, List<LocationItem>> getLocations(String name) {
        return scLocations.searchLocations(name);
    }

    public void onLocationSelected() {
        state.set(new PantallaWelcomeState(null, true));
    }

    public void clearState() {
        state.set(new PantallaWelcomeState(null, false));
    }

    public void loadLocations(List<LocationItem> locations) {
        observableLocations.clear();
        observableLocations.setAll(locations);
    }
}
