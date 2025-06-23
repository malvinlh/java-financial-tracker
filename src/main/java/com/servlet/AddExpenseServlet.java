package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.dao.ExpenseDao;
import com.db.HibernateUtil;
import com.entity.Expense;

@WebServlet("/addExpense")
public class AddExpenseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // DAO disembunyikan sebagai private final
    private final ExpenseDao dao = 
        new ExpenseDao(HibernateUtil.getSessionFactory());

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        String msg     = (String) session.getAttribute("msg");
        String msgType = (String) session.getAttribute("msgType");
        if (msg != null) {
            req.setAttribute("msg",     msg);
            req.setAttribute("msgType", msgType);
            session.removeAttribute("msg");
            session.removeAttribute("msgType");
        }
        req.getRequestDispatcher("/user/add_expense.jsp")
           .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {
        String title       = req.getParameter("title");
        String date        = req.getParameter("date");
        String time        = req.getParameter("time");
        String description = req.getParameter("description");
        double price       = Double.parseDouble(req.getParameter("price"));

        Expense ex = new Expense(title, date, time, description, price);
        boolean ok = dao.saveExpense(ex);

        HttpSession session = req.getSession();
        session.setAttribute("msg",     ok ? "Expense added successfully" : "Failed to add expense");
        session.setAttribute("msgType", ok ? "success"                  : "danger");

        resp.sendRedirect(req.getContextPath() + "/addExpense");
    }
}