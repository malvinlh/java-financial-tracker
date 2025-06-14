package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Menangani proses logout user.
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        // Buat session baru untuk flash message
        HttpSession newSession = req.getSession(true);
        newSession.setAttribute("msg",     "Logout successful");
        newSession.setAttribute("msgType", "success");

        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }
}