package org.id.gestiondossiersmutuelle.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class AppDataSourceConfig {

    @Primary
    @Bean(name = "appDataSource2")
    public DataSource appDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/mutuelle")  // Business database
                .username("root")
                .password("Samar@123")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }
}
