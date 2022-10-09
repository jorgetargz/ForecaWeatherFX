package domain.modelo;

import domain.common.Constantes;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ForecastHourlyItem {
    private LocalDateTime time;
    private double temperature;
    private int feelsLikeTemp;
    private int precipProb;
    private double windSpeed;

    private String symbol;
    private String symbolPhrase;
    private int relHumidity;
    private int visibility;
    private double windGust;
    private int solarRadiation;
    private int windDir;
    private int cloudiness;
    private double pressure;
    private double dewPoint;
    private int thunderProb;
    private double snowAccum;
    private double precipAccum;
    private String windDirString;
    private String precipType;
    private int uvIndex;

    public String getTime() {
        return time.getDayOfMonth() + Constantes.SEPARATOR +
                time.getMonthValue() + Constantes.SEPARATOR +
                time.getYear() + Constantes.ESPACIO +
                time.getHour() + Constantes.HS;
    }
}