package dao;

import domain.modelo.ForecastDailyItem;
import domain.modelo.ForecastHourlyItem;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;

import java.util.List;

public interface ForecastDao {
    Single<Either<String, List<ForecastDailyItem>>> getDailyForecast(int locationId);

    Either<String, List<ForecastHourlyItem>> getHourlyForecast(int period, int locationId);
}
