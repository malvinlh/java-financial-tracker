package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.dao.IncomeDao;
import com.db.HibernateUtil;
import com.entity.Income;
import com.entity.User;

/**
 * Memproses POST add_income.jsp → simpan Income → redirect ke /addIncome.
 */
@WebServlet("/saveIncome")
public class SaveIncomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (session != null)
                  ? (User) session.getAttribute("loginUser")
                  : null;

        // Proteksi
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        // Ambil parameter form
        String title       = req.getParameter("title");
        String date        = req.getParameter("date");
        String time        = req.getParameter("time");
        String description = req.getParameter("description");
        double amount      = Double.parseDouble(req.getParameter("amount"));

        // Simpan Income
        Income in = new Income(title, date, time, description, amount, user);
        boolean ok = new IncomeDao(HibernateUtil.getSessionFactory())
                         .saveIncome(in);

        // Set flash‐message
        session.setAttribute(
            "msg",
            ok ? "Income added successfully" : "Failed to add income"
        );
        session.setAttribute("msgType", ok ? "success" : "danger");

        // Redirect kembali ke form agar notifikasi muncul
        resp.sendRedirect(req.getContextPath() + "/addIncome");
    }
}