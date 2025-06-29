package com.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.dao.IncomeDao;
import com.db.HibernateUtil;
import com.entity.Income;
import com.entity.User;

/**
 * Menampilkan daftar Income milik user.
 */
@WebServlet("/viewIncome")
public class ViewIncomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
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

        // Ambil semua Income dari DAO
        List<Income> list = new IncomeDao(
            HibernateUtil.getSessionFactory()
        ).getAllIncomeByUser(user);

        // Pindahkan flash‐message dari session → request
        String msg     = (String) session.getAttribute("msg");
        String msgType = (String) session.getAttribute("msgType");
        if (msg != null) {
            req.setAttribute("msg", msg);
            req.setAttribute("msgType", msgType);
            session.removeAttribute("msg");
            session.removeAttribute("msgType");
        }

        req.setAttribute("incomes", list);
        req.getRequestDispatcher("/user/view_income.jsp")
           .forward(req, resp);
    }
}