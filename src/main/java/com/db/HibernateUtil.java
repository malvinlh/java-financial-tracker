package com.db;

import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.*;
import org.hibernate.service.ServiceRegistry;
import com.entity.Expense;

/**
 * Utility class untuk mendapatkan SessionFactory Hibernate.
 */
public final class HibernateUtil 
{
    private static SessionFactory sessionFactory;

    private HibernateUtil() { }

    /**
     * Dapatkan (singleton) SessionFactory.
     */
    public static synchronized SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration cfg = new Configuration();
                Properties props = new Properties();

                props.put(Environment.DRIVER,       "com.mysql.cj.jdbc.Driver");
                props.put(Environment.URL,          "jdbc:mysql://localhost:3306/financial_tracker?useSSL=false&serverTimezone=UTC");
                props.put(Environment.USER,         "root");
                props.put(Environment.PASS,         "");
                props.put(Environment.DIALECT,      "org.hibernate.dialect.MySQL8Dialect");
                props.put(Environment.HBM2DDL_AUTO, "update");
                props.put(Environment.SHOW_SQL,     "true");

                cfg.setProperties(props);
                cfg.addAnnotatedClass(Expense.class);

                ServiceRegistry reg = new StandardServiceRegistryBuilder()
                    .applySettings(cfg.getProperties())
                    .build();

                sessionFactory = cfg.buildSessionFactory(reg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}