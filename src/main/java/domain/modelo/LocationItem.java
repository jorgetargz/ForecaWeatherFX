package domain.modelo;

import domain.common.Constantes;
import lombok.Data;

@Data
public class LocationItem {
    private int id;
    private String country;
    private String timezone;
    private String language;
    private String state;
    private String name;
    private String adminArea;
    private String adminArea2;
    private String adminArea3;
    private double lon;
    private double lat;

    public String printLocationInfo() {
        String pais = Constantes.PAIS + country + Constantes.SALTO_LINEA;
        String coordinates = Constantes.ZONA_HORARIA + timezone + Constantes.SALTO_LINEA +
                Constantes.LATITUD + lat + Constantes.SALTO_LINEA +
                Constantes.LONGITUD + lon + Constantes.SALTO_LINEA;
        if (adminArea2 != null) {
            if (adminArea3 != null) {
                return pais + adminArea + Constantes.SALTO_LINEA +
                        adminArea2 + Constantes.SALTO_LINEA +
                        adminArea3 + Constantes.SALTO_LINEA +
                        coordinates;
            } else {
                return pais + adminArea + Constantes.SALTO_LINEA +
                        adminArea2 + Constantes.SALTO_LINEA +
                        coordinates;
            }
        } else {
            return pais + adminArea + Constantes.SALTO_LINEA +
                    coordinates;
        }
    }
}
