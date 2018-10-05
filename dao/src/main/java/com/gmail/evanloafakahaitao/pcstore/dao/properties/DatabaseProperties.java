package com.gmail.evanloafakahaitao.pcstore.dao.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class DatabaseProperties {

    private final Environment environment;

    private String databaseDriverName;
    private String databaseUrl;
    private String databaseUsername;
    private String databasePassword;
    private String hibernateCurrentSessionContextClass;
    private String hibernateHBM2DDLAuto;
    private String hibernateShowSQL;
    private String hibernateDialect;
    private String hibernateFormatSQL;
    private String hibernateUseSecondLevelCache;
    private String hibernateCacheRegionFactoryClass;
    private String hibernatePhysicalNamingStrategy;
    private String dataSourceClass;
    private Integer maxPoolSize;
    private String cachePreparedStatements;
    private Integer cachePreparedStatementsSize;
    private Integer cachePreparedStatementsSQLLimit;
    private String useServerPreparedStatements;

    @Autowired
    public DatabaseProperties(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void initialize() {
        this.databaseDriverName = environment.getProperty("database.driver.name");
        this.databaseUrl = environment.getProperty("database.url");
        this.databaseUsername = environment.getProperty("database.username");
        this.databasePassword = environment.getProperty("database.password");
        this.hibernateCurrentSessionContextClass = environment.getProperty("hibernate.current_session_context_class");
        this.hibernateHBM2DDLAuto = environment.getProperty("hibernate.hbm2ddl.auto");
        this.hibernateShowSQL = environment.getProperty("hibernate.show_sql");
        this.hibernateDialect = environment.getProperty("hibernate.dialect");
        this.hibernateFormatSQL = environment.getProperty("hibernate.format_sql");
        this.hibernateUseSecondLevelCache = environment.getProperty("hibernate.cache.use_second_level_cache");
        this.hibernateCacheRegionFactoryClass = environment.getProperty("hibernate.cache.region.factory_class");
        this.hibernatePhysicalNamingStrategy = environment.getProperty("hibernate.physical.naming.strategy");
        this.dataSourceClass = environment.getProperty("pool.data.source.class");
        this.maxPoolSize = Integer.valueOf(Objects.requireNonNull(environment.getProperty("pool.max.size")));
        this.cachePreparedStatements = environment.getProperty("pool.cache.prepared.statements");
        this.cachePreparedStatementsSize = Integer.valueOf(Objects.requireNonNull(environment.getProperty("pool.cache.prepared.statements.size")));
        this.cachePreparedStatementsSQLLimit = Integer.valueOf(Objects.requireNonNull(environment.getProperty("pool.cache.prepared.statements.sql.limit")));
        this.useServerPreparedStatements = environment.getProperty("pool.use.server.prepared.statements");
    }

    public String getDatabaseDriverName() {
        return databaseDriverName;
    }

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public String getDatabaseUsername() {
        return databaseUsername;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public String getHibernateCurrentSessionContextClass() {
        return hibernateCurrentSessionContextClass;
    }

    public String getHibernateHBM2DDLAuto() {
        return hibernateHBM2DDLAuto;
    }

    public String getHibernateShowSQL() {
        return hibernateShowSQL;
    }

    public String getHibernateUseSecondLevelCache() {
        return hibernateUseSecondLevelCache;
    }

    public String getHibernateCacheRegionFactoryClass() {
        return hibernateCacheRegionFactoryClass;
    }

    public String getHibernatePhysicalNamingStrategy() {
        return hibernatePhysicalNamingStrategy;
    }

    public String getDataSourceClass() {
        return dataSourceClass;
    }

    public Integer getMaxPoolSize() {
        return maxPoolSize;
    }

    public String getHibernateDialect() {
        return hibernateDialect;
    }

    public String getHibernateFormatSQL() {
        return hibernateFormatSQL;
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
