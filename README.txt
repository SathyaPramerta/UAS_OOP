Markdown# BuildWJax — Sistem Kasir & Rakitan PC (NetBeans Java Swing)

**BuildWJax** adalah aplikasi desktop yang dirancang khusus untuk manajemen pemesanan rakitan PC secara *custom*. Aplikasi ini memudahkan kasir dalam mengelola pemilihan komponen hardware berdasarkan *tier* dan *platform* (Intel/AMD), memproses transaksi, serta mengelola data pelanggan dengan sistem yang terintegrasi.

### Cakupan fitur (inti)
Login/Register → Pilih Tier & Platform → Konfigurasi Komponen → Simpan Pesanan → Riwayat Transaksi & Cetak Struk.

### Struktur singkat

```text
BuildWJax/
├── build.xml                 # Skrip Ant (NetBeans)
├── manifest.mf
├── nbproject/                # Metadata proyek NetBeans
├── lib/                      # Taruh JAR MySQL Connector/J di sini
├── database/
│   └── db_rakitan.sql        # Skrip pembuatan database + data contoh
└── src/
    └── uas_oop/
        ├── koneksi.java      # Konfigurasi koneksi ke database MySQL
        ├── Form_Login.java   # Halaman otentikasi kasir
        ├── Form_Register.java# Registrasi akun kasir baru
        ├── Form_Utama.java   # Menu utama / Dashboard pemilihan komponen rakitan
        └── Form_Riwayat.java # Manajemen histori & cetak struk (.txt)
Cara menjalankan1. Siapkan databaseJalankan skrip database/db_rakitan.sql di MySQL (misalnya lewat phpMyAdmin atau terminal):Bashmysql -u root -p < database/db_rakitan.sql
Skrip ini akan membuat database db_rakitan, beserta tabel-tabel yang dibutuhkan seperti tabel user (kasir), tabel komponen, tabel transaksi, dan beberapa data komponen bawaan.2. Sesuaikan koneksiBuka file src/uas_oop/koneksi.java. Sesuaikan URL, username (root), dan password dengan konfigurasi MySQL di perangkat Anda.3. Tambahkan driver MySQLAplikasi membutuhkan MySQL Connector/J agar bisa terhubung ke database. Di NetBeans → klik kanan project BuildWJax → Properties → Libraries → Add JAR/Folder → pilih file JAR MySQL Connector yang ada di folder lib/.4. Buka & jalankan di NetBeansPilih File → Open Project → pilih folder BuildWJax.Tekan F6 (Run) atau klik kanan pada Form_Login.java lalu pilih Run File (Shift+F6). Aplikasi akan dimulai dari halaman login.Akun defaultUsernamePasswordPeranadminadmin123Administrator / Kasir Utamakasirkasir123KasirAlur singkatKasir Login / Register: Kasir masuk menggunakan akun yang sudah ada atau mendaftar akun baru melalui Form_Register.Menu Utama (Rakitan): Setelah login, kasir dihadapkan pada antarmuka pemilihan spesifikasi. Kasir memilih Tier (misal: Low, Mid, High) dan Platform (Intel/AMD). Sistem akan menyesuaikan dan mengunci pilihan komponen yang kompatibel.Proses Transaksi: Total harga akan terkalkulasi secara otomatis. Kasir memproses pesanan dan menyimpannya ke dalam database.Riwayat & Struk: Melalui Form_Riwayat, kasir dapat melihat histori pesanan pelanggan dan mencetak bukti transaksi (struk) yang di-generate langsung ke dalam format teks (.txt).CatatanStruk transaksi akan otomatis dibuat dalam format .txt dan disimpan di dalam direktori project saat tombol cetak ditekan.Logika kompatibilitas (misal: Motherboard Intel hanya untuk Processor Intel) diatur langsung melalui event handling pada komponen GUI.Antarmuka dibangun sepenuhnya menggunakan NetBeans GUI Builder dengan palet bawaan Java Swing.
