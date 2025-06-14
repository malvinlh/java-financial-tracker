package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Menampilkan form add_income.jsp dan mem‐forward flash‐message jika ada.
 */
@WebServlet("/addIncome")
public class AddIncomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        // Proteksi: hanya user yang sudah login
        if (session == null || session.getAttribute("loginUser") == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        // Ambil flash‐message dari session → request
        String msg     = (String) session.getAttribute("msg");
        String msgType = (String) session.getAttribute("msgType");
        if (msg != null) {
            req.setAttribute("msg", msg);
            req.setAttribute("msgType", msgType);
            session.removeAttribute("msg");
            session.removeAttribute("msgType");
        }

        // Forward ke JSP form
        req.getRequestDispatcher("/user/add_income.jsp")
           .forward(req, resp);
    }
}