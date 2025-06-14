package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Hanya meneruskan (forward) ke form add_expense.jsp
 */
@WebServlet("/addExpense")
public class AddExpenseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        // Proteksi: hanya user login yang boleh akses
        if (session == null || session.getAttribute("loginUser") == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        // Forward ke JSP
        req.getRequestDispatcher("/user/add_expense.jsp")
           .forward(req, resp);
    }
}