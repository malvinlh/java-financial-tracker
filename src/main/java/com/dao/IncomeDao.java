package com.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.entity.Income;
import com.entity.User;

/**
 * DAO untuk operasi CRUD pada entitas Income.
 */
public class IncomeDao {

    private final SessionFactory factory;

    public IncomeDao(SessionFactory factory) {
        this.factory = factory;
    }

    /**
     * Simpan Income baru ke database.
     * @param in objek Income
     * @return true jika berhasil
     */
    public boolean saveIncome(Income in) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.persist(in);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Ambil semua Income milik user tertentu.
     * @param user user yang login
     * @return list Income
     */
    public List<Income> getAllIncomeByUser(User user) {
        try (Session session = factory.openSession()) {
            String hql = "FROM Income i WHERE i.user.id = :uid";
            Query<Income> q = session.createQuery(hql, Income.class);
            q.setParameter("uid", user.getId());
            return q.list();
        }
    }

    /**
     * Ambil satu Income berdasarkan ID.
     * @param id primary key Income
     * @return Income atau null jika tidak ada
     */
    public Income getIncomeById(int id) {
        try (Session session = factory.openSession()) {
            return session.get(Income.class, id);
        }
    }

    /**
     * Update Income yang sudah ada.
     * @param in objek Income dengan ID terisi
     * @return true jika berhasil
     */
    public boolean updateIncome(Income in) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.merge(in);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Hapus Income berdasarkan ID.
     * @param id primary key Income
     * @return true jika berhasil
     */
    public boolean deleteIncome(int id) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            Income in = session.get(Income.class, id);
            if (in == null) {
                tx.rollback();
                return false;
            }
            session.remove(in);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }
}