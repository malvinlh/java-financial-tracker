package com.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.entity.Income;

/**
 * DAO untuk operasi CRUD pada entitas Income.
 */
public class IncomeDao 
{
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
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Ambil semua Income.
     * @return list Income
     */
    public List<Income> getAllIncomes() {
        try (Session session = factory.openSession()) {
            Query<Income> q = session.createQuery("FROM Income", Income.class);
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
}