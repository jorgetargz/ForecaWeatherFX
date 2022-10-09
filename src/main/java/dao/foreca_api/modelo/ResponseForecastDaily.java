package dao.foreca_api.modelo;

import domain.modelo.ForecastDailyItem;
import lombok.Data;

import java.util.List;

@Data
public class ResponseForecastDaily {
    private boolean status;
    private List<ForecastDailyItem> forecast;
}
