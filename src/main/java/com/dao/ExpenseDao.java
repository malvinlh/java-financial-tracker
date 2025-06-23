package com.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.entity.Expense;

/**
 * DAO untuk operasi CRUD pada entitas Expense.
 */
public class ExpenseDao {

    private final SessionFactory factory;

    public ExpenseDao(SessionFactory factory) {
        this.factory = factory;
    }

    /**
     * Simpan Expense baru ke database.
     * @param ex objek Expense
     * @return true jika berhasil
     */
    public boolean saveExpense(Expense ex) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.persist(ex);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Ambil semua Expense milik user tertentu.
     * @param user user yang login
     * @return list Expense
     */
    public List<Expense> getAllExpenses() {
        try (Session s = factory.openSession()) {
        Query<Expense> q = s.createQuery("FROM Expense", Expense.class);
        return q.list();
        }
    }

    /**
     * Ambil satu Expense berdasarkan ID.
     * @param id primary key Expense
     * @return Expense atau null jika tidak ada
     */
    public Expense getExpenseById(int id) {
        try (Session session = factory.openSession()) {
            return session.get(Expense.class, id);
        }
    }

    /**
     * Update Expense yang sudah ada.
     * @param ex objek Expense dengan ID terisi
     * @return true jika berhasil
     */
    public boolean updateExpense(Expense ex) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.merge(ex);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Hapus Expense berdasarkan ID.
     * @param id primary key Expense
     * @return true jika berhasil
     */
    public boolean deleteExpense(int id) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            Expense ex = session.get(Expense.class, id);
            if (ex == null) {
                tx.rollback();
                return false;
            }
            session.remove(ex);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }
}