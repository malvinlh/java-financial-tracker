package com.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.dao.ExpenseDao;
import com.db.HibernateUtil;
import com.entity.Expense;
import com.entity.User;

@WebServlet("/viewExpense")
public class ViewExpenseServlet extends AbstractExpenseServlet {
    private static final long serialVersionUID = 1L;
    private List<Expense> expenses;

    @Override
    protected void preAction(HttpServletRequest req) {
        // fetch semua expenses user
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loginUser");
        expenses = new ExpenseDao(HibernateUtil.getSessionFactory())
                       .getAllExpenseByUser(user);
    }

    @Override
    protected void doAction(HttpServletRequest req) {
        // untuk view, tidak ada aksi update/delete
    }

    @Override
    protected void dispatch(HttpServletRequest req,
                            HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("expenses", expenses);
        req.getRequestDispatcher("/user/view_expense.jsp")
           .forward(req, resp);
    }
}
