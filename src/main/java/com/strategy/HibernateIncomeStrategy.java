package com.strategy;

import com.dao.IncomeDao;
import com.db.HibernateUtil;
import com.entity.Income;

/**
 * Strategy yang menggunakan Hibernate/DAO untuk menyimpan Income.
 */
public class HibernateIncomeStrategy implements SaveIncomeStrategy {

    private final IncomeDao dao;

    public HibernateIncomeStrategy() {
        this.dao = new IncomeDao(HibernateUtil.getSessionFactory());
    }

    @Override
    public boolean save(Income income) {
        return dao.saveIncome(income);
    }
}