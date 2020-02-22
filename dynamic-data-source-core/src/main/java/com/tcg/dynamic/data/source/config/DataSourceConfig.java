package com.tcg.dynamic.data.source.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.tcg.dynamic.data.source.DynamicDataSource;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {

    private static final Logger log = LoggerFactory.getLogger(DataSourceConfig.class);

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.master.hikari")
    public DataSource masterDataSource() {
        HikariDataSource ds = new HikariDataSource();
        log.info("create masterDataSource");
        return ds;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.slave.hikari")
    public DataSource slaveDataSource() {
        HikariDataSource ds = new HikariDataSource();
        log.info("create slaveDataSource");
        return ds;
    }

    @Bean
    public DataSource dataSource(@Qualifier("masterDataSource") DataSource masterDataSource, @Qualifier("slaveDataSource") DataSource slaveDataSource) {
        log.info("masterDataSource init completed, url={}", ((HikariDataSource) masterDataSource).getJdbcUrl());
        log.info("slaveDataSource init completed, url={}", ((HikariDataSource) slaveDataSource).getJdbcUrl());

        DynamicDataSource dataSource = new DynamicDataSource();

        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("slave", slaveDataSource);
        targetDataSources.put("master", masterDataSource);

        dataSource.setTargetDataSources(targetDataSources);

        dataSource.setDefaultTargetDataSource(masterDataSource);

        log.info("dataSource init completed");

        return dataSource;
    }
}
