package dao.impl;

import dao.ForecastDao;
import dao.common.Constantes;
import dao.foreca_api.ForecaAPI;
import dao.foreca_api.modelo.ResponseForecastDaily;
import dao.foreca_api.modelo.ResponseForecastHourly;
import domain.modelo.ForecastDailyItem;
import domain.modelo.ForecastHourlyItem;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

@Log4j2
public class ForecastDaoImpl implements ForecastDao {

    private final ForecaAPI forecaApi;

    @Inject
    public ForecastDaoImpl(ForecaAPI forecaApi) {
        this.forecaApi = forecaApi;
    }

    @Override
    public Either<String, List<ForecastDailyItem>> getDailyForecast(int locationId) {
        try {
            Response<ResponseForecastDaily> r = forecaApi.getDailyForecast(locationId).execute();
            if (r.isSuccessful()) {
                return Either.right(r.body().getForecast());
            } else {
                log.error(Constantes.ERROR_AL_BUSCAR_PREDICCION + r.errorBody().string());
                return Either.left(Constantes.ERROR_AL_BUSCAR_PREDICCION + r.errorBody().string());
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return Either.left(Constantes.EXCEPTION + e.getMessage());
        }
    }

    @Override
    public Either<String, List<ForecastHourlyItem>> getHourlyForecast(int period, int locationId) {
        String type = Constantes.HOURLY;
        if (period == 3) {
            type = Constantes.THREE_HOURLY;
        }
        try {
            Response<ResponseForecastHourly> r = forecaApi.getHourlyForecast(type, locationId).execute();
            if (r.isSuccessful()) {
                return Either.right(r.body().getForecast());
            } else {
                log.error(Constantes.ERROR_AL_BUSCAR_PREDICCION + r.errorBody().string());
                return Either.left(Constantes.ERROR_AL_BUSCAR_PREDICCION + r.errorBody().string());
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return Either.left(Constantes.EXCEPTION + e.getMessage());
        }
    }
}
