package domain.services;

import domain.modelo.ForecastDailyItem;
import domain.modelo.ForecastHourlyItem;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;

import java.util.List;

public interface ServiceForecast {
    Single<Either<String, List<ForecastDailyItem>>> getDailyForecast(int id);

    Either<String, List<ForecastHourlyItem>> getHourlyForecast(int period, int id);
}
