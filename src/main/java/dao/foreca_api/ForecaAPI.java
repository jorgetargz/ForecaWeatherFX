package dao.foreca_api;

import dao.common.Constantes;
import dao.foreca_api.modelo.ResponseForecastDaily;
import dao.foreca_api.modelo.ResponseForecastHourly;
import dao.foreca_api.modelo.ResponseLocationSearch;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ForecaAPI {

    @GET(Constantes.LOCATION_SEARCH_ENDPOINT)
    Call<ResponseLocationSearch> searchLocations(@Path(Constantes.LOCATION) String location, @Query(Constantes.LANG) String lang);

    @GET(Constantes.FORECAST_HOURLY_ENDPOINT)
    Call<ResponseForecastHourly> getHourlyForecast(@Path(Constantes.PERIOD) String period, @Path(Constantes.LOCATION_ID) int locationId);

    @GET(Constantes.FORECAST_DAYLY_ENDPOINT)
    Call<ResponseForecastDaily> getDailyForecast(@Path(Constantes.LOCATION_ID) int locationId);

}
