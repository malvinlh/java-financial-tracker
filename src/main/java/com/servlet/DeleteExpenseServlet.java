package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.dao.ExpenseDao;
import com.db.HibernateUtil;

/**
 * Hapus Expense, lalu redirect ke viewExpense.
 */
@WebServlet("/deleteExpense")
public class DeleteExpenseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("loginUser") == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            session.setAttribute("msg", "Invalid expense ID");
            session.setAttribute("msgType", "danger");
            resp.sendRedirect(req.getContextPath() + "/viewExpense");
            return;
        }

        boolean deleted = new ExpenseDao(
            HibernateUtil.getSessionFactory()
        ).deleteExpense(id);

        session.setAttribute(
            "msg",
            deleted
                ? "Expense deleted successfully"
                : "Failed to delete expense"
        );
        session.setAttribute("msgType", deleted ? "success" : "danger");

        resp.sendRedirect(req.getContextPath() + "/viewExpense");
    }
}