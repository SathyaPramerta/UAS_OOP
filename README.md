# 🖥️ BuildWJax

<p align="center">
  <h3 align="center">Sistem Kasir & Pemesanan Rakitan PC</h3>
  <p align="center">
    Aplikasi Desktop Berbasis Java Swing untuk Konfigurasi Rakitan PC dan Manajemen Transaksi
  </p>
</p>

---

## 👨‍💻 Tim Pengembang

| Nama | NIM |
|--------|--------|
| **Wahyu Agus Dwiyanto** | 2401010055 |
| **I Gusti Putu Bagus Sathya Pramerta** | 2401010069 |
| **Putu Bagus Sastrawan** | 2401010599 |
| **Theodorus Fidelis Samangun** | 2401010060 |

---

## 🎓 Informasi Akademik

| Keterangan | Detail |
|------------|---------|
| Mata Kuliah | Pemrograman Berorientasi Objek (OOP) |
| Program Studi | Informatika |
| Institusi | Institut Bisnis dan Teknologi Indonesia (INSTIKI) |
| Tahun Akademik | 2025 / 2026 |

---

## 📌 Tentang Proyek

**BuildWJax** merupakan aplikasi desktop yang dirancang untuk membantu proses pemesanan rakitan PC secara custom. Sistem memungkinkan pengguna memilih komponen berdasarkan tier dan platform yang diinginkan, menghitung total biaya secara otomatis, menyimpan data transaksi ke database MySQL, serta menghasilkan struk transaksi secara cepat dan akurat.

Proyek ini dikembangkan sebagai implementasi konsep **Object Oriented Programming (OOP)** menggunakan Java Swing dan JDBC sebagai penghubung ke database MySQL.

---

## ✨ Fitur Utama

### 🔐 Manajemen Akun
- Login Kasir
- Registrasi Akun Baru
- Validasi Username dan Password

### 🖥️ Konfigurasi Rakitan PC
- Pilihan Tier:
  - Low End
  - Mid Range
  - High End
- Pilihan Platform:
  - Intel
  - AMD
- Validasi kompatibilitas komponen

### 💰 Manajemen Transaksi
- Perhitungan harga otomatis
- Penyimpanan transaksi
- Penyimpanan data pelanggan

### 📜 Riwayat Transaksi
- Menampilkan histori transaksi
- Pencarian data pesanan
- Detail transaksi pelanggan

### 🧾 Cetak Struk
- Generate struk format `.txt`
- Detail pelanggan
- Detail komponen
- Total pembayaran

---

## 🏗️ Struktur Project

```text
BuildWJax/
│
├── build.xml
├── manifest.mf
│
├── nbproject/
│   └── Konfigurasi NetBeans
│
├── lib/
│   └── mysql-connector-j.jar
│
├── database/
│   └── db_rakitan.sql
│
└── src/
    └── uas_oop/
        │
        ├── koneksi.java
        ├── Form_Login.java
        ├── Form_Register.java
        ├── Form_Utama.java
        └── Form_Riwayat.java
```

---

## ⚙️ Teknologi yang Digunakan

| Teknologi | Kegunaan |
|------------|------------|
| Java | Bahasa Pemrograman |
| Java Swing | Antarmuka Desktop |
| NetBeans IDE | Development Environment |
| MySQL | Database |
| JDBC | Koneksi Database |
| Apache Ant | Build System |

---

## 🚀 Cara Menjalankan

### 1. Import Database

```bash
mysql -u root -p < database/db_rakitan.sql
```

### 2. Konfigurasi Database

Edit file:

```java
src/uas_oop/koneksi.java
```

Sesuaikan:

```java
String url = "jdbc:mysql://localhost:3306/db_rakitan";
String username = "root";
String password = "";
```

### 3. Tambahkan MySQL Connector

```text
Project
 └── Properties
      └── Libraries
           └── Add JAR/Folder
```

### 4. Jalankan Project

```text
Open Project → NetBeans
Run Project → F6
```

---

## 👤 Akun Default

| Username | Password | Role |
|-----------|-----------|---------|
| admin | admin123 | Administrator |
| kasir | kasir123 | Kasir |

---

## 📚 Konsep OOP yang Diterapkan

- Encapsulation
- Inheritance
- Polymorphism
- Abstraction
- Event Handling
- JDBC Database Integration

---

## 📄 Lisensi

Proyek ini dibuat untuk keperluan akademik sebagai tugas UAS Mata Kuliah Pemrograman Berorientasi Objek (OOP).

---

<div align="center">

### 🖥️ BuildWJax

*Build Your Dream PC with Ease*

</div>
