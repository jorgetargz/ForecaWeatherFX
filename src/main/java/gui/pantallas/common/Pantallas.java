package gui.pantallas.common;

public enum Pantallas {

    BIENVENIDA(ConstantesPantallas.FXML_PANTALLA_INICIO_FXML),
    LOCATION(ConstantesPantallas.FXML_LOCATION_SCREEN_FXML),
    DAILY(ConstantesPantallas.FXML_DAILY_SCREEN_FXML),
    THREE_HOURLY(ConstantesPantallas.FXML_THREE_HOURLY_SCREEN_FXML),
    HOURLY(ConstantesPantallas.FXML_HOURLY_SCREEN_FXML);

    private final String ruta;

    Pantallas(String ruta) {
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }


}
