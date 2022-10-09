package dao;

import domain.modelo.ForecastDailyItem;
import domain.modelo.ForecastHourlyItem;
import io.vavr.control.Either;

import java.util.List;

public interface ForecastDao {
    Either<String, List<ForecastDailyItem>> getDailyForecast(int locationId);

    Either<String, List<ForecastHourlyItem>> getHourlyForecast(int period, int locationId);
}
