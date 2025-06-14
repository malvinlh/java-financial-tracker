package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.dao.ExpenseDao;
import com.db.HibernateUtil;
import com.entity.Expense;
import com.entity.User;

@WebServlet("/editExpense")
public class EditExpenseServlet extends AbstractExpenseServlet {
    private static final long serialVersionUID = 1L;
    private Expense expense;

    @Override
    protected void preAction(HttpServletRequest req)
            throws ServletException {
        // tidak lakukan apa‐apa di preAction—seluruhnya di dispatch()
    }

    @Override
    protected void doAction(HttpServletRequest req) {
        // tidak ada: update dilakukan di dispatch()
    }

    @Override
    protected void dispatch(HttpServletRequest req,
                            HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("loginUser");

        if ("POST".equalsIgnoreCase(req.getMethod())) {
            // proses update
            int    id          = Integer.parseInt(req.getParameter("id"));
            String title       = req.getParameter("title");
            String date        = req.getParameter("date");
            String time        = req.getParameter("time");
            String description = req.getParameter("description");
            String price       = req.getParameter("price");

            expense = new Expense(title, date, time, description, price, user);
            expense.setId(id);

            boolean ok = dao.updateExpense(expense);
            session.setAttribute("msg",
                ok ? "Expense updated successfully" : "Failed to update expense");
            session.setAttribute("msgType", ok ? "success" : "danger");

            // redirect kembali ke GET
            resp.sendRedirect(req.getContextPath()
                + "/editExpense?id=" + id);
        } else {
            // GET: load untuk prefill form
            int id = Integer.parseInt(req.getParameter("id"));
            expense = dao.getExpenseById(id);

            // pindahkan flash yg sudah ada
            carryFlash(req);

            req.setAttribute("expense", expense);
            req.getRequestDispatcher("/user/edit_expense.jsp")
               .forward(req, resp);
        }
    }
}
