package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.dao.IncomeDao;
import com.db.HibernateUtil;

/**
 * Menghapus Income dan redirect ke viewIncome.
 */
@WebServlet("/deleteIncome")
public class DeleteIncomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("loginUser") == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        int id = Integer.parseInt(req.getParameter("id"));
        boolean ok = new IncomeDao(
            HibernateUtil.getSessionFactory()
        ).deleteIncome(id);

        session.setAttribute(
            "msg",
            ok ? "Income deleted successfully" : "Failed to delete income"
        );
        session.setAttribute("msgType", ok ? "success" : "danger");

        resp.sendRedirect(req.getContextPath() + "/viewIncome");
    }
}