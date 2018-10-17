package com.gmail.evanloafakahaitao.pcstore.dao.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatabaseProperties {

    @Value("${database.url}")
    private String databaseUrl;
    @Value("${database.username}")
    private String databaseUsername;
    @Value("${database.password}")
    private String databasePassword;
    @Value("${hibernate.hbm2ddl.auto}")
    private String hibernateHBM2DDLAuto;
    @Value("${hibernate.show_sql}")
    private String hibernateShowSQL;
    @Value("${hibernate.dialect}")
    private String hibernateDialect;
    @Value("${hibernate.format_sql}")
    private String hibernateFormatSQL;
    @Value("${hibernate.cache.use_second_level_cache}")
    private String hibernateUseSecondLevelCache;
    @Value("${hibernate.cache.region.factory_class}")
    private String hibernateCacheRegionFactoryClass;
    @Value("${pool.data.source.class}")
    private String dataSourceClass;
    @Value("${pool.max.size}")
    private Integer maxPoolSize;
    @Value("${pool.cache.prepared.statements}")
    private String cachePreparedStatements;
    @Value("${pool.cache.prepared.statements.size}")
    private Integer cachePreparedStatementsSize;
    @Value("${pool.cache.prepared.statements.sql.limit}")
    private Integer cachePreparedStatementsSQLLimit;
    @Value("${pool.use.server.prepared.statements}")
    private String useServerPreparedStatements;

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public String getDatabaseUsername() {
        return databaseUsername;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public String getHibernateHBM2DDLAuto() {
        return hibernateHBM2DDLAuto;
    }

    public String getHibernateShowSQL() {
        return hibernateShowSQL;
    }

    public String getHibernateDialect() {
        return hibernateDialect;
    }

    public String getHibernateFormatSQL() {
        return hibernateFormatSQL;
    }

    public String getHibernateUseSecondLevelCache() {
        return hibernateUseSecondLevelCache;
    }

    public String getHibernateCacheRegionFactoryClass() {
        return hibernateCacheRegionFactoryClass;
    }

    public String getDataSourceClass() {
        return dataSourceClass;
    }

    public Integer getMaxPoolSize() {
        return maxPoolSize;
    }

    public String getCachePreparedStatements() {
        return cachePreparedStatements;
    }

    public Integer getCachePreparedStatementsSize() {
        return cachePreparedStatementsSize;
    }

    public Integer getCachePreparedStatementsSQLLimit() {
        return cachePreparedStatementsSQLLimit;
    }

    public String getUseServerPreparedStatements() {
        return useServerPreparedStatements;
    }
}
