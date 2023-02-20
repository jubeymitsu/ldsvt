package ru.stomprf.db;

import org.apache.catalina.filters.CorsFilter;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Properties;

@Configuration
public class DBConfig {

    @Bean
    public DataSource dataSource(){
        Properties props = new Properties();
        FileInputStream fis = null;
        PGSimpleDataSource pgDS = null;
        try {
            fis = new FileInputStream("src/main/resources/db.properties ");
            props.load(fis);
            pgDS = new PGSimpleDataSource();
            pgDS.setURL(props.getProperty("POSTGRES_URL"));
            pgDS.setUser(props.getProperty("POSTGRES_USER"));
            pgDS.setPassword(props.getProperty("POSTGRES_PASSWORD"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pgDS;
    }

}
