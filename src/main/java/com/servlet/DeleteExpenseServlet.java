package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.dao.ExpenseDao;
import com.db.HibernateUtil;
import com.entity.User;

@WebServlet("/deleteExpense")
public class DeleteExpenseServlet extends AbstractExpenseServlet {
    private static final long serialVersionUID = 1L;
    private Integer expenseId;

    @Override
    protected void preAction(HttpServletRequest req) {
        try {
            expenseId = Integer.parseInt(req.getParameter("id"));
        } catch (Exception e) {
            expenseId = null;
        }
    }

    @Override
    protected void doAction(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (expenseId == null) {
            session.setAttribute("msg", "Invalid expense ID");
            session.setAttribute("msgType", "danger");
        } else {
            boolean ok = dao.deleteExpense(expenseId);
            session.setAttribute("msg",
                ok ? "Expense deleted successfully"
                   : "Failed to delete expense");
            session.setAttribute("msgType", ok ? "success" : "danger");
        }
    }

    @Override
    protected void dispatch(HttpServletRequest req,
                            HttpServletResponse resp)
            throws ServletException, IOException {
        // 1) Bawa flash dari session → request
        carryFlash(req);

        // 2) Reload data dan simpan ke request
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("loginUser");
        req.setAttribute("expenses",
            dao.getAllExpenseByUser(user)
        );

        // 3) Forward ke JSP view
        req.getRequestDispatcher("/user/view_expense.jsp")
           .forward(req, resp);
    }
}