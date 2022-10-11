module ForecaWeather {

    requires retrofit2;
    requires retrofit2.converter.gson;

    requires java.sql;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;
    requires io.reactivex.rxjava3;
    requires org.pdfsam.rxjavafx;
    requires retrofit2.adapter.rxjava3;

    requires lombok;
    requires org.apache.logging.log4j;

    requires jakarta.inject;
    requires jakarta.cdi;
    requires org.yaml.snakeyaml;
    requires okhttp3;
    requires io.vavr;
    requires gson;

    exports gui.main;
    exports gui.main.common;
    exports gui.pantallas.principal;
    exports gui.pantallas.common;
    exports gui.pantallas.welcome;
    exports gui.pantallas.location;
    exports gui.pantallas.daily;
    exports gui.pantallas.three_hourly;
    exports gui.pantallas.hourly;

    exports dao;
    exports dao.common;
    exports dao.impl;
    exports dao.foreca_api;
    exports dao.foreca_api.config;
    exports dao.foreca_api.di;
    exports dao.foreca_api.modelo;
    exports domain.services;
    exports domain.services.impl;
    exports domain.modelo;

    opens gui.main;
    opens gui.main.common;
    opens gui.pantallas.common;
    opens gui.pantallas.principal;
    opens gui.pantallas.welcome;
    opens gui.pantallas.location;
    opens gui.pantallas.daily;
    opens gui.pantallas.three_hourly;
    opens gui.pantallas.hourly;

    opens dao;
    opens dao.common;
    opens dao.impl;
    opens dao.foreca_api.modelo;
    opens dao.foreca_api;
    opens domain.common;
    opens domain.modelo;
    opens css;
    opens fxml;
    opens media;
    opens config;

}