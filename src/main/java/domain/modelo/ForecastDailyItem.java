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

    @Override
    public String toString() {
        return Constantes.DATE + getDate() + Constantes.SALTO_LINEA +
                Constantes.MAX_TEMPERATURE + maxTemp + Constantes.SALTO_LINEA +
                Constantes.MIN_TEMPERATURE + minTemp + Constantes.SALTO_LINEA +
                Constantes.MAX_WIND_SPEED + maxWindSpeed + Constantes.SALTO_LINEA +
                Constantes.PRECIPITATION_PROBABILITY + precipProb + Constantes.SALTO_LINEA +
                Constantes.SUNRISE + sunrise + Constantes.SALTO_LINEA +
                Constantes.SUNSET + sunset + Constantes.SALTO_LINEA +
                Constantes.MOONRISE + moonrise + Constantes.SALTO_LINEA +
                Constantes.MOONSET + moonset + Constantes.SALTO_LINEA +
                Constantes.MAX_FEELS_LIKE_TEMPERATURE + maxFeelsLikeTemp + Constantes.SALTO_LINEA +
                Constantes.MIN_FEELS_LIKE_TEMPERATURE + minFeelsLikeTemp + Constantes.SALTO_LINEA +
                Constantes.MAX_DEW_POINT + maxDewPoint + Constantes.SALTO_LINEA +
                Constantes.SUNRISE_EPOCH + sunriseEpoch + Constantes.SALTO_LINEA +
                Constantes.WIND_DIRECTION + windDir + Constantes.SALTO_LINEA +
                Constantes.MAX_RELATIVE_HUMIDITY + maxRelHumidity + Constantes.SALTO_LINEA +
                Constantes.SOLAR_RADIATION_SUM + solarRadiationSum + Constantes.SALTO_LINEA +
                Constantes.MOON_PHASE + moonPhase + Constantes.SALTO_LINEA +
                Constantes.SYMBOL_PHRASE + symbolPhrase + Constantes.SALTO_LINEA +
                Constantes.MIN_RELATIVE_HUMIDITY + minRelHumidity + Constantes.SALTO_LINEA +
                Constantes.CONFIDENCE + confidence + Constantes.SALTO_LINEA +
                Constantes.CLOUDINESS + cloudiness + Constantes.SALTO_LINEA +
                Constantes.PRESSURE + pressure + Constantes.SALTO_LINEA +
                Constantes.MAX_WIND_GUST + maxWindGust + Constantes.SALTO_LINEA +
                Constantes.SNOW_ACCUMULATION + snowAccum + Constantes.SALTO_LINEA +
                Constantes.MIN_DEW_POINT + minDewPoint + Constantes.SALTO_LINEA +
                Constantes.PRECIPITATION_ACCUMULATION + precipAccum + Constantes.SALTO_LINEA +
                Constantes.SUNSET_EPOCH + sunsetEpoch + Constantes.SALTO_LINEA +
                Constantes.MIN_VISIBILITY + minVisibility + Constantes.SALTO_LINEA +
                Constantes.UV_INDEX + uvIndex + Constantes.SALTO_LINEA;
    }
}
