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

/**
 * Menampilkan daftar Expense milik user.
 */
@WebServlet("/viewExpense")
public class ViewExpenseServlet extends HttpServlet {
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

        // Ambil data
        List<Expense> list = new ExpenseDao(
            HibernateUtil.getSessionFactory()
        ).getAllExpenseByUser(user);

        // Pindahkan pesan dari session ke request (jika ada)
        String msg     = (String) session.getAttribute("msg");
        String msgType = (String) session.getAttribute("msgType");
        if (msg != null) {
            req.setAttribute("msg", msg);
            req.setAttribute("msgType", msgType);
            session.removeAttribute("msg");
            session.removeAttribute("msgType");
        }

        req.setAttribute("expenses", list);
        req.getRequestDispatcher("/user/view_expense.jsp")
           .forward(req, resp);
    }
}