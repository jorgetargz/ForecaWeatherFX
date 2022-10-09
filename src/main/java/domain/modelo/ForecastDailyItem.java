package domain.modelo;

import domain.common.Constantes;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ForecastDailyItem {
    private LocalDate date;
    private int maxTemp;
    private int minTemp;
    private int maxWindSpeed;

    private double precipProb;
    private String sunrise;
    private String sunset;
    private String moonrise;
    private String moonset;
    private int maxFeelsLikeTemp;
    private int minFeelsLikeTemp;
    private String symbol;
    private int maxDewPoint;
    private int sunriseEpoch;
    private int windDir;
    private int maxRelHumidity;
    private int solarRadiationSum;
    private int moonPhase;
    private String symbolPhrase;
    private int minRelHumidity;
    private String confidence;
    private int cloudiness;
    private double pressure;
    private int maxWindGust;
    private int snowAccum;
    private int minDewPoint;
    private double precipAccum;
    private int sunsetEpoch;
    private int minVisibility;
    private int uvIndex;

    public String getDate() {
        return date.getDayOfMonth() + Constantes.SEPARATOR +
                date.getMonthValue() + Constantes.SEPARATOR +
                date.getYear();
    }
}