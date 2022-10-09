package domain.services;

import domain.modelo.ForecastDailyItem;
import domain.modelo.ForecastHourlyItem;
import io.vavr.control.Either;

import java.util.List;

public interface ServiceForecast {
    Either<String, List<ForecastDailyItem>> getDailyForecast(int id);

    Either<String, List<ForecastHourlyItem>> getHourlyForecast(int period, int id);
}
