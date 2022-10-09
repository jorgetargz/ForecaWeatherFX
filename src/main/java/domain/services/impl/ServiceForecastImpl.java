package domain.services.impl;

import dao.ForecastDao;
import domain.modelo.ForecastDailyItem;
import domain.modelo.ForecastHourlyItem;
import domain.services.ServiceForecast;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class ServiceForecastImpl implements ServiceForecast {

    private final ForecastDao daoForecast;

    @Inject
    public ServiceForecastImpl(ForecastDao daoForecast) {
        this.daoForecast = daoForecast;
    }

    @Override
    public Either<String, List<ForecastDailyItem>> getDailyForecast(int id) {
        return daoForecast.getDailyForecast(id);
    }

    @Override
    public Either<String, List<ForecastHourlyItem>> getHourlyForecast(int period, int id) {
        return daoForecast.getHourlyForecast(period, id);
    }
}
