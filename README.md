# java-financial-tracker

## Strategy Pattern (Add Income)

**Intent**  
Strategy Pattern memisahkan algoritma penyimpanan (`save`) untuk objek `Income` ke dalam kelas‐kelas terpisah, sehingga servlet tidak terikat pada implementasi spesifik (misalnya Hibernate) dan kita bisa menggantinya (mock, JDBC native, dsb.) tanpa mengubah servlet.

**Struktur & Cara Kerja**  

1. **`SaveIncomeStrategy`** (interface)  
    ```java
    public interface SaveIncomeStrategy {
        /** Simpan objek Income, kembalikan true jika sukses */
        boolean save(Income income);
    }
    ```

2. **`HibernateIncomeStrategy`** (implementasi utama)
    ```java
    public class HibernateIncomeStrategy implements SaveIncomeStrategy {
        private final IncomeDao dao = new IncomeDao(HibernateUtil.getSessionFactory());
        @Override
        public boolean save(Income income) {
            return dao.saveIncome(income);
        }
    }
    ```

3. **`AddIncomeServlet`**
    ```java
    @WebServlet("/addIncome")
    public class AddIncomeServlet extends HttpServlet {
        private final SaveIncomeStrategy strategy = new HibernateIncomeStrategy();

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                throws ServletException, IOException {
            HttpSession session = req.getSession(false);
            User user = (User) session.getAttribute("loginUser");
            // … baca form, bangun Income object …
            boolean ok = strategy.save(in);
            session.setAttribute("msg",     ok ? "Income added successfully" : "Failed to add income");
            session.setAttribute("msgType", ok ? "success" : "danger");
            resp.sendRedirect(req.getContextPath() + "/addIncome");
        }
    }
    ```

**Kelebihan**

1. Decoupling: `AddIncomeServlet` hanya bergantung pada interface, tidak ke Hibernate langsung.
2. Extensibility: Mudah menambah strategi baru (mock, batch save, dsb.) tanpa ubah servlet.
3. Testability: Tes servlet dengan `LoggingIncomeStrategy` atau mock strategy.