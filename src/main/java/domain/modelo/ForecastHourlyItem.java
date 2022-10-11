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

    @Override
    public String toString(){
        return Constantes.TIME + getTime() + Constantes.SALTO_LINEA +
                Constantes.TEMPERATURE + temperature + Constantes.SALTO_LINEA +
                Constantes.FEELS_LIKE + feelsLikeTemp + Constantes.SALTO_LINEA +
                Constantes.PRECIPITATION_PROBABILITY + precipProb + Constantes.SALTO_LINEA +
                Constantes.WIND_SPEED + windSpeed + Constantes.SALTO_LINEA +
                Constantes.RELATIVE_HUMIDITY + relHumidity + Constantes.SALTO_LINEA +
                Constantes.VISIBILITY + visibility + Constantes.SALTO_LINEA +
                Constantes.WIND_GUST + windGust + Constantes.SALTO_LINEA +
                Constantes.SOLAR_RADIATION + solarRadiation + Constantes.SALTO_LINEA +
                Constantes.WIND_DIRECTION + windDir + Constantes.SALTO_LINEA +
                Constantes.CLOUDINESS + cloudiness + Constantes.SALTO_LINEA +
                Constantes.PRESSURE + pressure + Constantes.SALTO_LINEA +
                Constantes.DEW_POINT + dewPoint + Constantes.SALTO_LINEA +
                Constantes.THUNDER_PROBABILITY + thunderProb + Constantes.SALTO_LINEA +
                Constantes.SNOW_ACCUMULATION + snowAccum + Constantes.SALTO_LINEA +
                Constantes.PRECIPITATION_ACCUMULATION + precipAccum + Constantes.SALTO_LINEA +
                Constantes.PRECIPITATION_TYPE + precipType + Constantes.SALTO_LINEA +
                Constantes.UV_INDEX + uvIndex + Constantes.SALTO_LINEA;
    }
}