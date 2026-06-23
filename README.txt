# 🖥️ BuildWJax — Sistem Kasir & Pemesanan Rakitan PC

BuildWJax adalah aplikasi desktop berbasis **Java Swing (NetBeans)** yang dirancang untuk membantu proses pemesanan rakitan PC secara custom. Sistem ini memungkinkan kasir memilih komponen berdasarkan **Tier** dan **Platform (Intel/AMD)**, mengelola transaksi pelanggan, menyimpan data ke database MySQL, serta mencetak struk transaksi secara otomatis.

---

## ✨ Fitur Utama

### 🔐 Manajemen Akun
- Login Kasir
- Registrasi Akun Baru
- Validasi Username & Password

### 🖥️ Pemesanan Rakitan PC
- Pemilihan Tier Rakitan
  - Low End
  - Mid Range
  - High End
- Pemilihan Platform
  - Intel
  - AMD
- Filter kompatibilitas komponen otomatis

### 💰 Transaksi
- Perhitungan total harga otomatis
- Penyimpanan transaksi ke database
- Penyimpanan data pelanggan

### 📜 Riwayat Transaksi
- Menampilkan histori transaksi
- Pencarian data pesanan
- Cetak struk transaksi

### 🧾 Cetak Struk
- Generate struk format `.txt`
- Detail pelanggan
- Detail komponen
- Total pembayaran

---

# 🗂 Struktur Project

```text
BuildWJax/
│
├── build.xml
├── manifest.mf
│
├── nbproject/
│   └── ...
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

# 🗄️ Database

Nama Database:

```sql
db_rakitan
```

Database digunakan untuk menyimpan:

- Data pengguna (kasir)
- Data komponen komputer
- Data transaksi
- Data pelanggan
- Riwayat pemesanan

---

# ⚙️ Cara Menjalankan Project

## 1. Import Database

Jalankan file SQL:

```bash
mysql -u root -p < database/db_rakitan.sql
```

Atau import melalui:

- phpMyAdmin
- MySQL Workbench
- XAMPP

---

## 2. Konfigurasi Koneksi Database

Buka file:

```java
src/uas_oop/koneksi.java
```

Sesuaikan konfigurasi berikut:

```java
String url = "jdbc:mysql://localhost:3306/db_rakitan";
String user = "root";
String password = "";
```

---

## 3. Tambahkan Driver MySQL

Download:

```text
MySQL Connector/J
```

Lalu tambahkan ke project:

```text
Project
└── Properties
    └── Libraries
        └── Add JAR/Folder
```

Pilih file:

```text
mysql-connector-j.jar
```

---

## 4. Jalankan Aplikasi

Buka project melalui NetBeans:

```text
File → Open Project
```

Kemudian jalankan:

```text
Run Project (F6)
```

atau

```text
Run File (Shift + F6)
```

pada:

```text
Form_Login.java
```

---

# 👤 Akun Default

| Username | Password | Role |
|-----------|-----------|---------|
| admin | admin123 | Administrator |
| kasir | kasir123 | Kasir |

---

# 🔄 Alur Sistem

```text
Login/Register
       ↓
Pilih Tier Rakitan
       ↓
Pilih Platform
       ↓
Konfigurasi Komponen
       ↓
Hitung Total Harga
       ↓
Simpan Transaksi
       ↓
Riwayat Transaksi
       ↓
Cetak Struk
```

---

# 📄 Modul Utama

## Form_Login.java

Berfungsi untuk:

- Login pengguna
- Validasi akun
- Akses ke dashboard utama

---

## Form_Register.java

Berfungsi untuk:

- Registrasi kasir baru
- Penyimpanan akun ke database

---

## Form_Utama.java

Berfungsi untuk:

- Pemilihan komponen rakitan
- Perhitungan harga
- Penyimpanan transaksi

---

## Form_Riwayat.java

Berfungsi untuk:

- Menampilkan histori transaksi
- Mencetak struk transaksi

---

# 🛠 Teknologi yang Digunakan

| Teknologi | Keterangan |
|------------|------------|
| Java | Bahasa Pemrograman |
| Java Swing | GUI Desktop |
| NetBeans IDE | Development Environment |
| MySQL | Database |
| JDBC | Koneksi Database |
| Apache Ant | Build System |

---

# 📌 Catatan

- Struk transaksi akan dibuat dalam format `.txt`.
- File struk disimpan otomatis pada direktori project.
- Kompatibilitas komponen Intel dan AMD dikontrol melalui event handling Java Swing.
- Seluruh antarmuka dibangun menggunakan **NetBeans GUI Builder**.

---

# 👨‍💻 Developer

**BuildWJax Project**

Project UAS Pemrograman Berorientasi Objek (OOP)

Institut Bisnis dan Teknologi Indonesia (INSTIKI)

2026
