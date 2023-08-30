package com.rupesh.multitenant_lite.context;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author Rupesh Dangi
 * @date: 2023/08/16 17/07
 */


public class MultiTenantDataSourceObj extends AbstractRoutingDataSource
{
    @Override
    protected Object determineCurrentLookupKey()
    {
        return TenantContext.getCurrentTenant();
    }
}
