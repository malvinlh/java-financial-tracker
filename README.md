# java-financial-tracker

## Template Method Pattern (View/Edit/Delete Expense)

**Intent**  
Template Method Pattern mendefinisikan kerangka alur proses di kelas abstrak (`AbstractExpenseServlet`), lalu memanggil hook langkah-langkah khusus ke subclass. Untuk operasi Expense (view, edit, delete) alur dasarnya sama:

1. Cek login user  
2. `preAction(req)` — hook baca parameter atau load data  
3. `doAction(req)` — hook jalankan aksi (delete/update atau no-op)  
4. `carryFlash(req)` — pindahkan flash-message dari session→request  
5. `dispatch(req,resp)` — hook forward/redirect ke JSP yang sesuai  

**Cara Kerja Kode**  
1. **AbstractExpenseServlet**  
    - `doGet()` menjalankan urutan:  
        a. proteksi login  
        b. `preAction(req)`  
        c. `doAction(req)`  
        d. `carryFlash(req)` (pindahkan pesan session→request)  
        e. `dispatch(req, resp)`  
    - `doPost()` langsung delegate ke `doGet()` sehingga POST juga melewati alur yang sama. 
    - `preAction`, `doAction`, `dispatch` ditandai `abstract`.

2. **ViewExpenseServlet**  
    - `preAction`: fetch data lewat DAO
    - `doAction`: kosong
    - `dispatch`: set `requestScope.expenses` dan forward ke JSP

3. **EditExpenseServlet**  
    `Dispatch` menangani dua skenario:
    - `POST` → update, set flash, lalu redirect
    - `GET` → load entity, carryFlash, forward

4. **DeleteExpenseServlet**  
   - `preAction`: parse ID 
   - `doAction`: delete & set flash  
   - `dispatch`: pindahkan flash, fetch ulang list, forward

Dengan Template Method Pattern ini, ketiga servlet Expense berbagi kerangka alur yang sama, hanya melakukan override tiga hook sesuai kebutuhan masing-masing.