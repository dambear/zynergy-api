//package com.danbear.zynergy.config;
//
//import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//public class DataSourceRouter extends AbstractRoutingDataSource {
//
//  private static final ThreadLocal<String> currentDataSource = new ThreadLocal<>();
//  private final Map<Object, Object> targetDataSources = new HashMap<>();
//
//  @Override
//  protected Object determineCurrentLookupKey() {
//    return currentDataSource.get();
//  }
//
//  public static void setCurrentDataSource(String dataSourceKey) {
//    currentDataSource.set(dataSourceKey);
//  }
//
//  public static void clearCurrentDataSource() {
//    currentDataSource.remove();
//  }
//
//  // Constructor to initialize targetDataSources if needed
//  public DataSourceRouter() {
//    // Initialize targetDataSources here if needed.
//    setTargetDataSources(targetDataSources);
//    afterPropertiesSet(); // Refresh the data source map
//  }
//
//  public void addDataSource(String key, String url) {
//    DriverManagerDataSource dataSource = new DriverManagerDataSource();
//    dataSource.setUrl(url);
//
//    // Add the new data source to the map
//    targetDataSources.put(key, dataSource);
//
//    // After adding a new data source, update the target data sources and refresh.
//    setTargetDataSources(targetDataSources);
//    afterPropertiesSet();
//  }
//}
