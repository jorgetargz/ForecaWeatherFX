package dao.common;

public class Constantes {

    public static final String PERIOD = "period";
    public static final String ERROR_DE_COMUNICACION = "Error de comunicacion";

    private Constantes() {
    }

    public static final String LOCATION_SEARCH_ENDPOINT = "/location/search/{location}";
    public static final String FORECAST_HOURLY_ENDPOINT = "/forecast/{period}/{location_id}?alt=0&tempunit=C&windunit=MS&periods=8&dataset=full&history=1";
    public static final String FORECAST_DAYLY_ENDPOINT = "/forecast/daily/{location_id}?alt=0&tempunit=C&windunit=MS&periods=8&dataset=full";

    public static final String LOCATION = "location";
    public static final String LANG = "lang";
    public static final String LOCATION_ID = "location_id";

    public static final String ES = "es";
    public static final String ERROR_AL_BUSCAR_PREDICCION = "Error al buscar predicción: ";
    public static final String EXCEPTION = "Exception: ";
    public static final String HOURLY = "hourly";
    public static final String THREE_HOURLY = "3hourly";
    public static final String ERROR_AL_BUSCAR_LOCALIZACIONES = "Error al buscar localizaciones: ";

    public static final String X_RAPID_API_KEY = "X-RapidAPI-Key";
    public static final String X_RAPID_API_HOST = "X-RapidAPI-Host";
    public static final String PROTOCOL_REQUEST = "Protocol-Request";
    public static final String HTTP_2_0 = "HTTP/2.0";
    public static final String ACCEPT = "Accept";
    public static final String APPLICATION_JSON = "application/json";

    public static final String FORECA_API_YAML = "config/forecaAPI.yaml";
    public static final String BASE_URL = "baseUrl";
    public static final String HOST = "host";
    public static final String API_KEY = "apiKey";
}
