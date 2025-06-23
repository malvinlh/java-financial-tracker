package com.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.entity.Expense;

@WebServlet("/viewExpense")
public class ViewExpenseServlet extends AbstractExpenseServlet {
    private static final long serialVersionUID = 1L;
    private List<Expense> expenses;

    @Override
    protected void preAction(HttpServletRequest req) {
        // Ambil semua Expense
        expenses = dao.getAllExpenses();
    }

    @Override
    protected void doAction(HttpServletRequest req) {
        // Hanya set attribute
        req.setAttribute("expenses", expenses);
    }

    @Override
    protected void dispatch(HttpServletRequest req,
                            HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/user/view_expense.jsp")
           .forward(req, resp);
    }
}