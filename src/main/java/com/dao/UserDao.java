package com.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.entity.User;

/**
 * DAO untuk operasi pada entitas User.
 */
public class UserDao {

    private final SessionFactory factory;

    public UserDao(SessionFactory factory) {
        this.factory = factory;
    }

    /**
     * Simpan User baru ke database.
     * @param user objek User
     * @return true jika berhasil
     */
    public boolean saveUser(User user) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.persist(user);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Autentikasi user berdasarkan email & password.
     * @param email email yang dimasukkan
     * @param password password yang dimasukkan
     * @return objek User jika cocok, atau null
     */
    public User login(String email, String password) {
        try (Session session = factory.openSession()) {
            String hql = "FROM User u WHERE u.email = :email AND u.password = :password";
            Query<User> q = session.createQuery(hql, User.class);
            q.setParameter("email", email);
            q.setParameter("password", password);
            return q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}