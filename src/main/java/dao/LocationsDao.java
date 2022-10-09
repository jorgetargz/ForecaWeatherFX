package dao;

import domain.modelo.LocationItem;
import io.vavr.control.Either;

import java.util.List;

public interface LocationsDao {
    Either<String, List<LocationItem>> searchLocations(String nombre);
}
