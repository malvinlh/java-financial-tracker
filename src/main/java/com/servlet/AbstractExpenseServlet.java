package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.dao.ExpenseDao;
import com.db.HibernateUtil;
import com.entity.User;

/**
 * Template Method untuk operasi Expense: proteksi, flash, dispatch.
 */
public abstract class AbstractExpenseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected final ExpenseDao dao =
        new ExpenseDao(HibernateUtil.getSessionFactory());

    @Override
    protected final void doGet(HttpServletRequest req,
                               HttpServletResponse resp)
            throws ServletException, IOException {

        // 1) Proteksi login
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("loginUser") == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        // 2) Hook sebelum action utama (opsional)
        preAction(req);

        // 3) Aksi utama (update/delete atau kosong untuk view)
        doAction(req);

        // 4) Carry flash message ke request
        carryFlash(req);

        // 5) Dispatch: subclass yang handle forward/redirect
        dispatch(req, resp);
    }

    @Override
    protected final void doPost(HttpServletRequest req,
                                HttpServletResponse resp)
            throws ServletException, IOException {
        // Kirim POST ke doGet agar template‐flow sama
        doGet(req, resp);
    }

    /** Pindahkan flash (msg,msgType) dari session ke request, lalu hapus */
    protected void carryFlash(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            Object msg     = session.getAttribute("msg");
            Object msgType = session.getAttribute("msgType");
            if (msg != null) {
                req.setAttribute("msg",     msg);
                req.setAttribute("msgType", msgType);
                session.removeAttribute("msg");
                session.removeAttribute("msgType");
            }
        }
    }

    /** Hook: baca parameter atau load data sebelum action */
    protected abstract void preAction(HttpServletRequest req)
            throws ServletException;

    /** Hook: lakukan operasi utama (delete/update) */
    protected abstract void doAction(HttpServletRequest req);

    /** Hook: forward atau redirect */
    protected abstract void dispatch(HttpServletRequest req,
                                     HttpServletResponse resp)
            throws ServletException, IOException;
}
