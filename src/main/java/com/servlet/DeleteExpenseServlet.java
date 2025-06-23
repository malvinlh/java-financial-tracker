package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

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
        HttpSession session = req.getSession();
        if (expenseId == null) {
            session.setAttribute("msg", "Invalid expense ID");
            session.setAttribute("msgType", "danger");
        } else {
            boolean ok = dao.deleteExpense(expenseId);
            session.setAttribute("msg",
                ok ? "Expense deleted successfully"
                   : "Failed to delete expense");
            session.setAttribute("msgType", "danger");
        }
    }

    @Override
    protected void dispatch(HttpServletRequest req,
                            HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("expenses", dao.getAllExpenses());
        req.getRequestDispatcher("/user/view_expense.jsp")
           .forward(req, resp);
    }
}