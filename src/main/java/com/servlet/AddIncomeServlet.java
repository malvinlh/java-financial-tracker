package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.entity.Income;
import com.entity.User;
import com.strategy.HibernateIncomeStrategy;
import com.strategy.SaveIncomeStrategy;

@WebServlet("/addIncome")
public class AddIncomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Pilih strategy yang diinginkan:
    private final SaveIncomeStrategy strategy = new HibernateIncomeStrategy();

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("loginUser") == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        // Ambil flash dari session → request
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
        HttpSession session = req.getSession(false);
        User user = (session != null)
                  ? (User) session.getAttribute("loginUser")
                  : null;
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        // 1) Baca parameter form
        String title       = req.getParameter("title");
        String date        = req.getParameter("date");
        String time        = req.getParameter("time");
        String description = req.getParameter("description");
        double amount      = Double.parseDouble(req.getParameter("amount"));

        // 2) Buat objek Income
        Income in = new Income(title, date, time, description, amount, user);

        // 3) Simpan dengan strategy
        boolean ok = strategy.save(in);

        // 4) Set flash-message
        session.setAttribute("msg",     ok ? "Income added successfully"  : "Failed to add income");
        session.setAttribute("msgType", ok ? "success" : "danger");

        // 5) Redirect kembali ke form Add Income (PRG)
        resp.sendRedirect(req.getContextPath() + "/addIncome");
    }
}