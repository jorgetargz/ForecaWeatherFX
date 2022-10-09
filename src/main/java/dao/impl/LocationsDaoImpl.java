package dao.impl;

import dao.LocationsDao;
import dao.common.Constantes;
import dao.foreca_api.ForecaAPI;
import dao.foreca_api.modelo.ResponseLocationSearch;
import domain.modelo.LocationItem;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;


@Log4j2
public class LocationsDaoImpl implements LocationsDao {
    private final ForecaAPI forecaApi;

    @Inject
    public LocationsDaoImpl(ForecaAPI forecaApi) {
        this.forecaApi = forecaApi;
    }

    @Override
    public Either<String, List<LocationItem>> searchLocations(String nombre) {
        try {
            Response<ResponseLocationSearch> r = forecaApi.searchLocations(nombre, Constantes.ES).execute();
            if (r.isSuccessful()) {
                return Either.right(r.body().getLocations());
            } else {
                log.error(Constantes.ERROR_AL_BUSCAR_LOCALIZACIONES + r.errorBody().string());
                return Either.left(Constantes.ERROR_AL_BUSCAR_LOCALIZACIONES + r.errorBody().string());
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return Either.left(Constantes.EXCEPTION + e.getMessage());
        }
    }
}
