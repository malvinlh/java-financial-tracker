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
 * GET  → prefill form edit
 * POST → proses update dan redirect kembali ke GET
 */
@WebServlet("/editExpense")
public class EditExpenseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ExpenseDao dao = 
        new ExpenseDao(HibernateUtil.getSessionFactory());

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (session != null)
                  ? (User) session.getAttribute("loginUser")
                  : null;

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        int id = Integer.parseInt(req.getParameter("id"));
        Expense ex = dao.getExpenseById(id);

        req.setAttribute("expense", ex);
        req.getRequestDispatcher("/user/edit_expense.jsp")
           .forward(req, resp);
    }

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loginUser");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        // Ambil param form
        int    id          = Integer.parseInt(req.getParameter("id"));
        String title       = req.getParameter("title");
        String date        = req.getParameter("date");
        String time        = req.getParameter("time");
        String description = req.getParameter("description");
        String price       = req.getParameter("price");

        // Update lewat DAO
        Expense ex = new Expense(title, date, time, description, price, user);
        ex.setId(id);
        boolean ok = dao.updateExpense(ex);

        // Set pesan
        session.setAttribute(
            "msg",
            ok ? "Expense updated successfully" : "Failed to update expense"
        );
        session.setAttribute("msgType", ok ? "success" : "danger");

        // Redirect kembali ke form edit (GET)
        resp.sendRedirect(
            req.getContextPath() + "/editExpense?id=" + id
        );
    }
}