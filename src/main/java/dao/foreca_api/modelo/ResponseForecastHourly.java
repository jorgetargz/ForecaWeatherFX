package dao.foreca_api.modelo;

import domain.modelo.ForecastHourlyItem;
import lombok.Data;

import java.util.List;

@Data
public class ResponseForecastHourly {
    private boolean status;
    private List<ForecastHourlyItem> forecast;
}