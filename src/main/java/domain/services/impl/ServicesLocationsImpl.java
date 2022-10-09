package domain.services.impl;

import dao.LocationsDao;
import domain.modelo.LocationItem;
import domain.services.ServicesLocations;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class ServicesLocationsImpl implements ServicesLocations {

    private final LocationsDao daoLocations;

    @Inject
    public ServicesLocationsImpl(LocationsDao daoLocations) {
        this.daoLocations = daoLocations;
    }

    @Override
    public Either<String, List<LocationItem>> searchLocations(String name) {
        return daoLocations.searchLocations(name);
    }

}
