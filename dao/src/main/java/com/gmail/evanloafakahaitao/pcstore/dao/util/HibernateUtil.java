package com.gmail.evanloafakahaitao.pcstore.dao.util;

import com.gmail.evanloafakahaitao.pcstore.config.ConfigurationManager;
import com.gmail.evanloafakahaitao.pcstore.config.properties.DatabaseProperties;
import com.gmail.evanloafakahaitao.pcstore.config.properties.HibernateProperties;
import com.gmail.evanloafakahaitao.pcstore.dao.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import java.util.HashMap;
import java.util.Map;

public class HibernateUtil {

    private static final Logger logger = LogManager.getLogger(HibernateUtil.class);

    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    private HibernateUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

                Map<String, String> settings = new HashMap<>();
                settings.put(Environment.DRIVER, ConfigurationManager.getInstance().getProperty(DatabaseProperties.DATABASE_DRIVER_NAME));
                settings.put(Environment.URL, ConfigurationManager.getInstance().getProperty(DatabaseProperties.DATABASE_URL));
                settings.put(Environment.USER, ConfigurationManager.getInstance().getProperty(DatabaseProperties.DATABASE_USERNAME));
                settings.put(Environment.PASS, ConfigurationManager.getInstance().getProperty(DatabaseProperties.DATABASE_PWD));
                settings.put(Environment.HBM2DDL_AUTO, ConfigurationManager.getInstance().getProperty(HibernateProperties.HIBERNATE_HBM2DDL_AUTO));
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, ConfigurationManager.getInstance().getProperty(HibernateProperties.HIBERNATE_CURRENT_SESSION_CONTEXT_CLASS));
                settings.put(Environment.USE_SECOND_LEVEL_CACHE, ConfigurationManager.getInstance().getProperty(HibernateProperties.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
                settings.put(Environment.CACHE_REGION_FACTORY, ConfigurationManager.getInstance().getProperty(HibernateProperties.HIBERNATE_CACHE_REGION_FACTORY_CLASS));
                settings.put(Environment.SHOW_SQL, ConfigurationManager.getInstance().getProperty(HibernateProperties.HIBERNATE_SHOW_SQL));
                settings.put(Environment.PHYSICAL_NAMING_STRATEGY, ConfigurationManager.getInstance().getProperty(HibernateProperties.HIBERNATE_PHYSICAL_NAMING_STRATEGY));

                registryBuilder.applySettings(settings);
                registry = registryBuilder.build();
                logger.info("Hibernate Registry builder created");

                MetadataSources sources = new MetadataSources(registry)
                        .addAnnotatedClass(OrderId.class)
                        .addAnnotatedClass(User.class)
                        .addAnnotatedClass(Role.class)
                        .addAnnotatedClass(Permission.class)
                        .addAnnotatedClass(Feedback.class)
                        .addAnnotatedClass(Audit.class)
                        .addAnnotatedClass(Profile.class)
                        .addAnnotatedClass(News.class)
                        .addAnnotatedClass(Comment.class)
                        .addAnnotatedClass(Item.class)
                        .addAnnotatedClass(Order.class)
                        .addAnnotatedClass(Discount.class);
                Metadata metadata = sources.getMetadataBuilder().build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();
                logger.info("SessionFactory created");
            } catch (Exception e) {
                logger.error("SessionFactory creation failed");
                logger.error(e.getMessage(), e);
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
                throw new RuntimeException(e);
            }
        }
        return sessionFactory;
    }
}
