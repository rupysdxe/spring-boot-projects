package com.rupesh.multitenant_lite.context;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Rupesh Dangi
 * @date: 2023/08/17 13/38
 */

@Configuration
public class MultiTenantConfiguration
{

    @Bean
    @ConfigurationProperties(prefix = "tenants")
    public DataSource dataSource()
    {
        File[] files = Paths.get("tenants").toFile().listFiles();
        Map<Object, Object> resolvedDataSources = new HashMap<>();
        assert files != null;
        for (File propertyFile : files)
        {
            Properties tenantProperties = new Properties();
            DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
            try {
                tenantProperties.load(Files.newInputStream(propertyFile.toPath()));
                String tenantId = tenantProperties.getProperty("name");
                dataSourceBuilder.driverClassName(tenantProperties.getProperty("datasource.driver-class-name"));
                dataSourceBuilder.username(tenantProperties.getProperty("datasource.username"));
                dataSourceBuilder.password(tenantProperties.getProperty("datasource.password"));
                dataSourceBuilder.url(tenantProperties.getProperty("datasource.url"));
                // pup config like show sql etc

                resolvedDataSources.put(tenantId, dataSourceBuilder.build());
            } catch (IOException exp) {
                throw new RuntimeException("Problem in tenant datasource:" + exp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        AbstractRoutingDataSource dataSource = new MultiTenantDataSourceObj();
        setDefaultDataSource(resolvedDataSources);
        dataSource.setDefaultTargetDataSource(resolvedDataSources.get("default"));
        dataSource.setTargetDataSources(resolvedDataSources);
        dataSource.afterPropertiesSet();
        return dataSource;
    }


    private void setDefaultDataSource(Map<Object, Object> resolvedDataSources)
    {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("rupesh");
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/MT");
        resolvedDataSources.put("default", dataSourceBuilder.build());

    }


    
}
