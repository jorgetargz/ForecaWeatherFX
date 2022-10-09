package dao.foreca_api.di;

import com.google.gson.*;
import dao.common.Constantes;
import dao.foreca_api.ForecaAPI;
import dao.foreca_api.config.ConfigForecaAPI;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ProducerForecaAPI {

    @Produces
    @Singleton
    public Retrofit getRetrofit(ConfigForecaAPI configForecaAPI) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
                (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) -> LocalDateTime.parse(json.getAsJsonPrimitive().getAsString().substring(0, 16))).registerTypeAdapter(LocalDateTime.class,
                (JsonSerializer<LocalDateTime>) (localDateTime, type, jsonSerializationContext) -> new JsonPrimitive(localDateTime.toString())
        ).registerTypeAdapter(LocalDate.class,
                (JsonDeserializer<LocalDate>) (json, type, jsonDeserializationContext) -> LocalDate.parse(json.getAsJsonPrimitive().getAsString())).registerTypeAdapter(LocalDate.class,
                (JsonSerializer<LocalDate>) (localDate, type, jsonSerializationContext) -> new JsonPrimitive(localDate.toString())
        ).create();

        OkHttpClient clientOK = new OkHttpClient.Builder()
                .connectionPool(new okhttp3.ConnectionPool(1, 1, java.util.concurrent.TimeUnit.SECONDS))
                .addInterceptor(chain -> {
                            Request original = chain.request();

                            Request request = original.newBuilder()
                                    .header(Constantes.X_RAPID_API_KEY, configForecaAPI.getApiKey())
                                    .header(Constantes.X_RAPID_API_HOST, configForecaAPI.getHost())
                                    .header(Constantes.PROTOCOL_REQUEST, Constantes.HTTP_2_0)
                                    .header(Constantes.ACCEPT, Constantes.APPLICATION_JSON)
                                    .method(original.method(), original.body())
                                    .build();

                            return chain.proceed(request);
                        }
                ).build();

        return new Retrofit.Builder()
                .baseUrl(configForecaAPI.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(clientOK)
                .build();
    }

    @Produces
    public ForecaAPI getForecaAPI(Retrofit retrofit) {
        return retrofit.create(ForecaAPI.class);
    }
}
