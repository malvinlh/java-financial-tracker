package com.db;

import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.*;
import org.hibernate.service.ServiceRegistry;

import com.entity.Expense;
import com.entity.User;
import com.entity.Income;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration cfg = new Configuration();
                Properties props = new Properties();

                props.put(Environment.DRIVER,   "com.mysql.cj.jdbc.Driver");
                props.put(Environment.URL,      "jdbc:mysql://localhost:3306/financial_tracker?useSSL=false&serverTimezone=UTC");
                props.put(Environment.USER,     "root");
                props.put(Environment.PASS,     "");
                props.put(Environment.DIALECT,  "org.hibernate.dialect.MySQL8Dialect");

                props.put(Environment.HBM2DDL_AUTO, "update");
                props.put(Environment.SHOW_SQL,      "true");

                cfg.setProperties(props);
                cfg.addAnnotatedClass(User.class);
                cfg.addAnnotatedClass(Expense.class);
                cfg.addAnnotatedClass(Income.class);

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