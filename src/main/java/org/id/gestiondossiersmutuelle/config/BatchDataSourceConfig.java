package org.id.gestiondossiersmutuelle.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class BatchDataSourceConfig {

    @Bean(name = "batchDataSource2")
    public DataSource batchDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/job_repo")  // Batch database
                .username("root")
                .password("Samar@123")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }
}
