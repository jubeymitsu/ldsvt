package ru.stomprf.db;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class DBConfig {

    @Bean
    public DataSource dataSource(){
        Properties props = new Properties();
        FileInputStream fis;
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
