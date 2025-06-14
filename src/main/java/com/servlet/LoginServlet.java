package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.dao.UserDao;
import com.db.HibernateUtil;
import com.entity.User;

/**
 * Menangani autentikasi user.
 */
@WebServlet("/userLogin")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserDao dao =
        new UserDao(HibernateUtil.getSessionFactory());

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {
        // 1. Ambil parameter
        String email    = req.getParameter("email");
        String password = req.getParameter("password");

        // 2. Autentikasi via DAO
        User user = dao.login(email, password);

        HttpSession session = req.getSession();
        if (user == null) {
            // 3a. Gagal
            session.setAttribute("msg",     "Invalid email or password");
            session.setAttribute("msgType", "danger");
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        } else {
            // 3b. Sukses
            session.setAttribute("loginUser", user);
            resp.sendRedirect(req.getContextPath() + "/user/home.jsp");
        }
    }

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {
        // Langsung ke form login
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }
}