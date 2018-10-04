package com.gmail.evanloafakahaitao.pcstore.config;

import com.gmail.evanloafakahaitao.pcstore.dao.model.*;
import com.gmail.evanloafakahaitao.pcstore.dao.properties.DatabaseProperties;
import com.gmail.evanloafakahaitao.pcstore.dao.util.StorePhysicalNamingStrategy;
import com.zaxxer.hikari.HikariDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

    private final DatabaseProperties databaseProperties;

    @Autowired
    public DatabaseConfig(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    @Bean
    public DataSource dataSource() {
        final HikariDataSource dataSource = new HikariDataSource();
        dataSource.setPoolName("PC store connection pool");
        dataSource.setMaximumPoolSize(databaseProperties.getMaxPoolSize());
        dataSource.setDataSourceClassName(databaseProperties.getDataSourceClass());
        dataSource.addDataSourceProperty("url", databaseProperties.getDatabaseUrl());
        dataSource.addDataSourceProperty("user", databaseProperties.getDatabaseUsername());
        dataSource.addDataSourceProperty("password", databaseProperties.getDatabasePassword());
        dataSource.addDataSourceProperty("cachePrepStmts", databaseProperties.getCachePreparedStatements());
        dataSource.addDataSourceProperty("prepStmtCacheSize", databaseProperties.getCachePreparedStatementsSize());
        dataSource.addDataSourceProperty("prepStmtCacheSqlLimit", databaseProperties.getCachePreparedStatementsSQLLimit());
        dataSource.addDataSourceProperty("useServerPrepStmts", databaseProperties.getUseServerPreparedStatements());
        return dataSource;
    }

    @Bean
    public SpringLiquibase springLiquibase(DataSource dataSource) {
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setDataSource(dataSource);
        springLiquibase.setDropFirst(Boolean.TRUE);
        springLiquibase.setChangeLog("classpath:migration/db-changelog.xml");
        return springLiquibase;
    }

    @Bean
    @DependsOn("springLiquibase")
    public LocalSessionFactoryBean getSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPhysicalNamingStrategy(new StorePhysicalNamingStrategy());
        Properties properties = new Properties();
        //TODO dialect
        properties.put(DIALECT, "");
        properties.put(SHOW_SQL, databaseProperties.getHibernateShowSQL());
        properties.put(HBM2DDL_AUTO, databaseProperties.getHibernateHBM2DDLAuto());
        properties.put(USE_SECOND_LEVEL_CACHE, databaseProperties.getHibernateUseSecondLevelCache());
        properties.put(CACHE_REGION_FACTORY, databaseProperties.getHibernateCacheRegionFactoryClass());
        factoryBean.setHibernateProperties(properties);
        factoryBean.setAnnotatedClasses(
                Audit.class,
                Comment.class,
                Discount.class,
                Feedback.class,
                Item.class,
                News.class,
                Order.class,
                OrderId.class,
                Permission.class,
                Profile.class,
                Role.class,
                User.class
        );
        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }
}
