package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.dao.ExpenseDao;
import com.db.HibernateUtil;
import com.entity.Expense;
import com.entity.User;

/**
 * Menangani POST form add_expense.jsp, menyimpan Expense, lalu redirect.
 */
@WebServlet("/saveExpense")
public class SaveExpenseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {
        // Ambil parameter form
        String title       = req.getParameter("title");
        String date        = req.getParameter("date");
        String time        = req.getParameter("time");
        String description = req.getParameter("description");
        String price       = req.getParameter("price");

        // Ambil user login
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loginUser");

        // Simpan lewat DAO
        Expense  ex  = new Expense(title, date, time, description, price, user);
        boolean  ok  = new ExpenseDao(HibernateUtil.getSessionFactory())
                          .saveExpense(ex);

        // Set notifikasi
        session.setAttribute(
            "msg",
            ok ? "Expense added successfully" : "Something went wrong on server"
        );
        session.setAttribute("msgType", ok ? "success" : "danger");

        // Redirect kembali ke form
        resp.sendRedirect(req.getContextPath() + "/user/add_expense.jsp");
    }
}