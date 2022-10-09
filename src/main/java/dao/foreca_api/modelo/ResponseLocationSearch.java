package dao.foreca_api.modelo;

import domain.modelo.LocationItem;
import lombok.Data;

import java.util.List;

@Data
public class ResponseLocationSearch {
    private boolean status;
    private List<LocationItem> locations;

}