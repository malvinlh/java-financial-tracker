package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.dao.ExpenseDao;
import com.db.HibernateUtil;

/**
 * Template Method untuk operasi Expense: flash → preAction → doAction → carryFlash → dispatch
 */
public abstract class AbstractExpenseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected final ExpenseDao dao =
        new ExpenseDao(HibernateUtil.getSessionFactory());

    @Override
    protected final void doGet(HttpServletRequest req,
                               HttpServletResponse resp)
            throws ServletException, IOException {
        // Hook sebelum action
        preAction(req);

        // Aksi utama (save/update/delete atau kosong untuk view)
        doAction(req);

        // Pindahkan flash (msg,msgType) dari session ke request
        carryFlash(req);

        // Dispatch (forward/redirect)
        dispatch(req, resp);
    }

    @Override
    protected final void doPost(HttpServletRequest req,
                                HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

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

    /** Hook: lakukan operasi utama (save/update/delete/view) */
    protected abstract void doAction(HttpServletRequest req);

    /** Hook: forward atau redirect */
    protected abstract void dispatch(HttpServletRequest req,
                                     HttpServletResponse resp)
            throws ServletException, IOException;
}