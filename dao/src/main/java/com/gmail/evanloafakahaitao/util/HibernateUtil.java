package com.gmail.evanloafakahaitao.util;

import com.gmail.evanloafakahaitao.config.ConfigurationManager;
import com.gmail.evanloafakahaitao.config.properties.DatabaseProperties;
import com.gmail.evanloafakahaitao.config.properties.HibernateProperties;
import com.gmail.evanloafakahaitao.model.Item;
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

                registryBuilder.applySettings(settings);
                registry = registryBuilder.build();
                logger.info("Hibernate Registry builder created");

                MetadataSources sources = new MetadataSources(registry)
                        .addAnnotatedClass(Item.class);
                Metadata metadata = sources.getMetadataBuilder().build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();
                logger.info("SessionFactory created");
            } catch (Exception e) {
                logger.error("SessionFactory creation failed");
                logger.error(e.getMessage(), e);
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }
}
