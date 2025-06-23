package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.entity.Expense;

@WebServlet("/editExpense")
public class EditExpenseServlet extends AbstractExpenseServlet {
    private static final long serialVersionUID = 1L;
    private Expense expense;

    @Override
    protected void preAction(HttpServletRequest req) throws ServletException {
        // nothing
    }

    @Override
    protected void doAction(HttpServletRequest req) {
        // nothing
    }

    @Override
    protected void dispatch(HttpServletRequest req,
                            HttpServletResponse resp)
            throws ServletException, IOException {

        if ("POST".equalsIgnoreCase(req.getMethod())) {
            // Proses update
            int    id          = Integer.parseInt(req.getParameter("id"));
            String title       = req.getParameter("title");
            String date        = req.getParameter("date");
            String time        = req.getParameter("time");
            String description = req.getParameter("description");
            double price       = Double.parseDouble(req.getParameter("price"));

            expense = new Expense(title, date, time, description, price);
            expense.setId(id);

            boolean ok = dao.updateExpense(expense);
            HttpSession session = req.getSession();
            session.setAttribute("msg",
                ok ? "Expense edited successfully" : "Failed to edit expense");
            session.setAttribute("msgType", ok ? "success" : "danger");

            resp.sendRedirect(req.getContextPath() + "/editExpense?id=" + id);

        } else {
            // GET: load data untuk prefill form
            int id = Integer.parseInt(req.getParameter("id"));
            expense = dao.getExpenseById(id);

            // carryFlash sudah dipanggil sebelum hook di template
            req.setAttribute("expense", expense);
            req.getRequestDispatcher("/user/edit_expense.jsp")
               .forward(req, resp);
        }
    }
}