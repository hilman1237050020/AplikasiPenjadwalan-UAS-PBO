
# 📅 Aplikasi Penjadwalan Kegiatan - Kelompok 3

> **Tugas UAS Pemrograman Berorientasi Objek (PBO)**  
> **Universitas Komputer Indonesia (UNIKOM)**  
> **Semester Genap Tahun Ajaran 2024/2025**

---

## 🧾 Deskripsi Proyek

Aplikasi ini merupakan sistem penjadwalan kegiatan berbasis **Java Swing** yang dibuat untuk menyelesaikan **Tugas Ujian Akhir Semester (UAS)** dari mata kuliah **Pemrograman Berorientasi Objek (PBO)**.

Tujuan dari aplikasi ini adalah untuk membantu pengguna dalam **mengatur, mencatat, dan memanajemen kegiatan harian**. Pengguna dapat menyimpan event lengkap dengan nama kegiatan, tanggal, jam, deskripsi, dan tingkat prioritas (Low, Medium, High).

Aplikasi ini menyimpan data secara **lokal** di **database MySQL**, dan menyediakan fitur lengkap mulai dari menambahkan, menampilkan, mengedit, hingga menghapus data kegiatan.

---

## 🧑‍🤝‍🧑 Anggota Kelompok 3

| NIM          | Nama Lengkap                  |
|--------------|-------------------------------|
| 1237050020   | Hilman Maulana                |
| 1237050001   | Raihan Alfarizi               |
| 1237050095   | Rifky Daffa Pratama           |
| 1237050075   | Rizka Aulia Fauziah           |
| 1237050049   | Sabilla Anggraeni             |
| 1237050005   | Salwa Sayyidati Azkia         |
| 1237050085   | Suma Renata Wijaya            |
| 1237050009   | Yildi Andriana                |

---

## 🛠️ Fitur Aplikasi

- ✅ **Menambah Event Baru**  
  Pengguna dapat menambahkan event baru yang berisi informasi nama kegiatan, tanggal, waktu, deskripsi kegiatan, dan prioritas.

- ✅ **Melihat Daftar Event**  
  Semua event ditampilkan dalam urutan berdasarkan tanggal dan waktu, sehingga pengguna mudah melihat jadwal terdekat.

- ✅ **Mengedit Event**  
  Pengguna bisa memilih event yang sudah ada dan mengubah informasinya sesuai kebutuhan.

- ✅ **Menghapus Event**  
  Event yang sudah tidak diperlukan dapat dihapus dari database.

- ✅ **Navigasi Menu**  
  Menggunakan sidebar menu untuk berpindah antar halaman/form dalam aplikasi.

- ✅ **Tampilan UI Modern**  
  Dibuat dengan `Java Swing` yang sudah dimodifikasi agar tampil lebih bersih dan menarik.

---

## 🗃️ Struktur Database

**Nama Database:** `penjadwalan_db`  
**Tabel Utama:** `event`

### Struktur Tabel:
```sql
CREATE TABLE event (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100),
  date DATE,
  time TIME,
  description TEXT,
  priority VARCHAR(20)
);
```

### Contoh Data:
```sql
INSERT INTO event (name, date, time, description, priority) VALUES
('UAS PBO', '2025-06-22', '13:00:00', 'Ujian Akhir Semester PBO Kelas D', 'Low');
```

---

## 🧱 Struktur Proyek

```
📁 src
├── com.raven.main              → Main class aplikasi (entry point)
├── com.raven.model             → Class model (Event) dan DAO (EventDAO)
├── com.raven.connection        → Class DBConnection untuk menghubungkan ke database
├── com.raven.form              → Form-form tampilan aplikasi
├── com.raven.component         → Komponen pendukung tampilan seperti menu & header
└── com.raven.ui                → Tampilan user interface custom
```

---

## 🧑‍💻 Teknologi yang Digunakan

- 🟦 Java SE (Swing GUI)
- 🟨 JDBC (Java Database Connectivity)
- 🐬 MySQL (phpMyAdmin)
- 🖥️ NetBeans IDE 17
- 📋 GitHub (versi kontrol & dokumentasi)

---

## 🚀 Cara Menjalankan Aplikasi

1. **Import Database:**
   - Buka phpMyAdmin
   - Import file `penjadwalan_db.sql` yang tersedia di folder project
   - Pastikan database `penjadwalan_db` berhasil dibuat

2. **Cek Konfigurasi Koneksi:**
   - Buka file `DBConnection.java`
   - Pastikan username, password, dan nama database sesuai dengan konfigurasi MySQL kalian

3. **Jalankan Aplikasi:**
   - Buka project di NetBeans
   - Jalankan file `Main.java` sebagai file utama

4. **Gunakan Aplikasi:**
   - Aplikasi akan terbuka dalam jendela custom
   - Navigasi menggunakan menu sebelah kiri
   - Tambah, lihat, dan kelola event dengan mudah

---

## 📚 Tujuan Pembelajaran

- Menerapkan konsep **Object-Oriented Programming (OOP)** dalam aplikasi nyata
- Menghubungkan aplikasi desktop Java dengan database MySQL
- Menerapkan struktur kode yang terpisah dan modular
- Meningkatkan kemampuan kerja sama tim dalam proyek kelompok
- Memahami alur **CRUD** (Create, Read, Update, Delete) dalam program GUI

---

## 📸 Dokumentasi Tampilan *(Opsional)*

*Silakan tambahkan screenshot tampilan aplikasi di sini.*

---

## ✍️ Catatan

- Semua data tersimpan lokal di database MySQL
- Pastikan server MySQL aktif saat menjalankan aplikasi
- Password MySQL default masih kosong (`""`), sesuaikan jika berbeda di komputer masing-masing

---

## 🏁 Penutup

Aplikasi ini merupakan hasil kerja sama tim yang menunjukkan pemahaman dasar kami tentang PBO, GUI Swing, dan pengelolaan data menggunakan MySQL. Harapannya, proyek ini bisa menjadi fondasi untuk mengembangkan sistem yang lebih kompleks ke depannya.

Terima kasih kepada dosen pengampu dan seluruh anggota tim atas kontribusinya. 🙏
