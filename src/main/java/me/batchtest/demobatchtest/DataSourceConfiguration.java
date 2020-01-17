package me.batchtest.demobatchtest;

import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class DataSourceConfiguration {

    @Bean(name="dataSource")
    @Primary
    @Qualifier("dataSource")
    @ConfigurationProperties("batchlog.datasource.hikari")
    public DataSource dataSource (){
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "usersDataSource")
    @Qualifier("usersDataSource")
    @ConfigurationProperties("users.datasource.hikari")
    public DataSource userDataSource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.type(HikariDataSource.class);
        HikariDataSource hikariDataSource = new HikariDataSource();
        System.out.println("MaximumPoolSize default value " + hikariDataSource.getMaximumPoolSize());
        System.out.println("MinimumIdle default value " + hikariDataSource.getMinimumIdle());
        hikariDataSource.getDataSourceProperties();
        hikariDataSource.setMaximumPoolSize(50);
        hikariDataSource.setMinimumIdle(50);
        System.out.println("MaximumPoolSize value after set MaximumPoolSize " + hikariDataSource.getMaximumPoolSize());
        System.out.println("MinimumIdle value after set MaximumPoolSize  " + hikariDataSource.getMinimumIdle());
        return dataSourceBuilder.build();
    }

}
