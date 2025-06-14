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
 * GET  → prefill form edit_income.jsp
 * POST → proses update & redirect kembali ke GET
 */
@WebServlet("/editIncome")
public class EditIncomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final IncomeDao dao =
        new IncomeDao(HibernateUtil.getSessionFactory());

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
        Income in = dao.getIncomeById(id);

        req.setAttribute("income", in);
        req.getRequestDispatcher("/user/edit_income.jsp")
           .forward(req, resp);
    }

    @Override
    protected void doPost(
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

        // Ambil parameter form
        int    id          = Integer.parseInt(req.getParameter("id"));
        String title       = req.getParameter("title");
        String date        = req.getParameter("date");
        String time        = req.getParameter("time");
        String description = req.getParameter("description");
        double amount      = Double.parseDouble(req.getParameter("amount"));

        // Update Income
        Income in = new Income(title, date, time, description, amount, user);
        in.setId(id);
        boolean ok = dao.updateIncome(in);

        // Set flash‐message
        session.setAttribute(
            "msg",
            ok ? "Income updated successfully" : "Failed to update income"
        );
        session.setAttribute("msgType", ok ? "success" : "danger");

        // Redirect kembali ke GET agar JSP di‐prefill & notifikasi tampil
        resp.sendRedirect(
            req.getContextPath() + "/editIncome?id=" + id
        );
    }
}