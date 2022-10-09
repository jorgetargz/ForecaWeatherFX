package dao.foreca_api.config;

import dao.common.Constantes;
import jakarta.inject.Singleton;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.util.Map;

@Getter
@Log4j2
@Singleton
public class ConfigForecaAPI {

    private final String baseUrl;
    private final String host;
    private final String apiKey;

    public ConfigForecaAPI() {
        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(Constantes.FORECA_API_YAML);
        Map<String, Object> propertiesMap = yaml.load(inputStream);
        this.baseUrl = ((String) propertiesMap.get(Constantes.BASE_URL));
        this.host = ((String) propertiesMap.get(Constantes.HOST));
        this.apiKey = ((String) propertiesMap.get(Constantes.API_KEY));
    }
}
