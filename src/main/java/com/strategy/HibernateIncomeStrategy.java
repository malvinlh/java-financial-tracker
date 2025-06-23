package com.strategy;

import com.dao.IncomeDao;
import com.db.HibernateUtil;
import com.entity.Income;

/**
 * Concrete Strategy menggunakan Hibernate/DAO.
 */
public class HibernateIncomeStrategy implements SaveIncomeStrategy
{
    private final IncomeDao dao;

    public HibernateIncomeStrategy() {
        this.dao = new IncomeDao(HibernateUtil.getSessionFactory());
    }

    @Override
    public boolean save(Income income) {
        return dao.saveIncome(income);
    }
}