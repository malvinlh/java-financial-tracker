package com.strategy;

import com.entity.Income;

/**
 * Strategy interface untuk menyimpan Income.
 */
public interface SaveIncomeStrategy
{
    /**
     * Simpan objek Income.
     * @param income Income yang akan disimpan
     * @return true jika berhasil
     */
    boolean save(Income income);
}