# java-financial-tracker

## Strategy Pattern (Add Income)

**Intent**  
Strategy Pattern memisahkan algoritma penyimpanan (`save`) untuk objek `Income` ke dalam kelas‐kelas terpisah, sehingga servlet tidak terikat pada implementasi spesifik (misalnya Hibernate) dan kita bisa menggantinya (mock, JDBC native, dsb.) tanpa mengubah servlet.

**Struktur & Cara Kerja**  

1. **`SaveIncomeStrategy`** (interface)
    - Simpan objek `Income`.
    - Return `true` jika sukses.

2. **`HibernateIncomeStrategy`** (implementasi utama)
    - Implementasi `save()` menggunakan `IncomeDao.saveIncome(...)`

3. **`AddIncomeServlet`**
    - Memiliki field `private final SaveIncomeStrategy strategy = new HibernateIncomeStrategy();` sebagai strategy yang dipilih.
    - `doPost()` melakukan:
        - Baca parameter form → bangun objek `Income`
        - Panggil `strategy.save(income)` → eksekusi penyimpanan sesuai strategi
        - Simpan hasil (`ok`) ke flash‐message di session
        - Redirect kembali ke `addIncome` (PRG)
    - `doGet()` mengambil flash dari session ke request dan forward ke JSP.

**Kelebihan**

1. Decoupling: `AddIncomeServlet` hanya bergantung pada interface, tidak ke Hibernate langsung.
2. Extensibility: Mudah menambah strategi baru (mock, batch save, dsb.) tanpa ubah servlet.
3. Testability: Tes servlet dengan `LoggingIncomeStrategy` atau mock strategy.