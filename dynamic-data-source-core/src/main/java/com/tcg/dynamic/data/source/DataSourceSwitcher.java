package com.tcg.dynamic.data.source;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class DataSourceSwitcher {
    private static final Logger log = LoggerFactory.getLogger(DataSourceSwitcher.class);
    private static final ThreadLocal<String> dataSourceTypeHolder = new ThreadLocal<>();

    public static void setDataSource(String dataSource) {
        Assert.notNull(dataSource, "dataSource cannot be null");
        dataSourceTypeHolder.set(dataSource);
    }

    public static void setMaster(){
        clearDataSource();
    }

    public static void setSlave() {
        setDataSource("slave");
    }

    public static String getDataSource() {
        log.info("dataSource={}", dataSourceTypeHolder.get());
        return dataSourceTypeHolder.get();
    }

    public static void clearDataSource() {
        dataSourceTypeHolder.remove();
    }
}