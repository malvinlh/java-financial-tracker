package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.dao.UserDao;
import com.db.HibernateUtil;
import com.entity.User;

/**
 * Menangani pendaftaran user baru.
 */
@WebServlet("/userRegister")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserDao dao =
        new UserDao(HibernateUtil.getSessionFactory());

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {
        // 1. Ambil parameter
        String fullname = req.getParameter("fullName");
        String email    = req.getParameter("email");
        String password = req.getParameter("password");
        String about    = req.getParameter("about");

        // 2. Simpan lewat DAO
        User user = new User(fullname, email, password, about);
        boolean ok = dao.saveUser(user);

        // 3. Set flash message
        HttpSession session = req.getSession();
        if (ok) {
            session.setAttribute("msg",     "Registered successfully");
            session.setAttribute("msgType", "success");
        } else {
            session.setAttribute("msg",
                "Registration failed, please try again");
            session.setAttribute("msgType", "danger");
        }

        // 4. Redirect kembali ke form
        resp.sendRedirect(req.getContextPath() + "/register.jsp");
    }
}