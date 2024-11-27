package org.id.gestiondossiersmutuelle.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    // Primary Data Source (Application Data)
    @Bean(name = "appDataSource")
    @Primary
    public DataSource appDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/mutuelle")
                .username("root")
                .password("Samar@123")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }

    // Batch Data Source (Job Repository)
    @Bean(name = "dataSource")
    public DataSource batchDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/job_repo")
                .username("root")
                .password("Samar@123")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }
}
