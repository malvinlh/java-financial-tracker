package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.entity.Income;
import com.strategy.HibernateIncomeStrategy;
import com.strategy.SaveIncomeStrategy;

@WebServlet("/addIncome")
public class AddIncomeServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    // Strategy disembunyikan sebagai private final
    private final SaveIncomeStrategy strategy = new HibernateIncomeStrategy();

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
        req.getRequestDispatcher("/user/add_income.jsp")
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
        double amount      = Double.parseDouble(req.getParameter("amount"));

        Income in = new Income(title, date, time, description, amount);
        boolean ok = strategy.save(in);

        HttpSession session = req.getSession();
        session.setAttribute("msg",     ok ? "Income added successfully" : "Failed to add income");
        session.setAttribute("msgType", ok ? "success"                 : "danger");

        resp.sendRedirect(req.getContextPath() + "/addIncome");
    }
}