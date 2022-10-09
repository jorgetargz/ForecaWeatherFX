package domain.services;

import domain.modelo.LocationItem;
import io.vavr.control.Either;

import java.util.List;

public interface ServicesLocations {
    Either<String, List<LocationItem>> searchLocations(String name);
}
