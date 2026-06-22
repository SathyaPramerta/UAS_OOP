BuildWJax — Sistem Kasir & Rakitan PC (NetBeans Java Swing)
BuildWJax adalah aplikasi desktop yang dirancang khusus untuk manajemen pemesanan rakitan PC secara custom. Aplikasi ini memudahkan kasir dalam mengelola pemilihan komponen hardware berdasarkan tier dan platform (Intel/AMD), memproses transaksi, serta mengelola data pelanggan dengan sistem yang terintegrasi.

Cakupan fitur (inti)
Login/Register → Pilih Tier & Platform → Konfigurasi Komponen → Simpan Pesanan → Riwayat Transaksi & Cetak Struk.

Struktur Proyek
Plaintext
BuildWJax/
├── build.xml               # Skrip Ant (NetBeans)
├── manifest.mf
├── nbproject/              # Metadata proyek NetBeans
├── database/db_rakitan.sql # Skrip pembuatan database + data komponen
└── src/
    └── uas_oop/
        ├── koneksi.java    # Konfigurasi koneksi ke database MySQL
        ├── Form_Login.java # Halaman otentikasi kasir
        ├── Form_Register.java # Registrasi akun kasir baru
        ├── Form_Riwayat.java # Manajemen histori & cetak struk (.txt)
        └── pemesanan_pc.java # Form utama: Konfigurasi & kalkulasi harga
Cara menjalankan
1. Siapkan database
Impor file database/db_rakitan.sql ke MySQL melalui phpMyAdmin. Skrip ini akan membuat database, tabel komponen, data stok awal, tabel transaksi, serta tabel kasir.

2. Tambahkan Driver MySQL
Pastikan MySQL Connector/J terpasang:

Klik kanan folder Libraries di NetBeans.

Pilih Add JAR/Folder dan tambahkan file .jar MySQL Connector Anda.

3. Buka & jalankan di NetBeans

Buka folder proyek ini melalui menu File → Open Project.

Klik kanan pada Form_Login.java, lalu pilih Run File (atau tekan Shift + F6).

Alur singkat
Login: Kasir melakukan otentikasi melalui BuildWJax. Jika belum memiliki akun, tersedia fitur registrasi kasir baru.

Pemesanan: Kasir memilih Tier (Low/Mid/End) dan platform prosesor (AMD/Intel). Komponen pendukung seperti Motherboard, RAM, hingga GPU akan terfilter otomatis.

Simpan: Data pesanan beserta detail pelanggan disimpan ke database pusat.

Riwayat & Cetak: Kasir dapat melihat daftar pesanan yang pernah masuk, mengedit kembali pesanan, atau mencetak struk transaksi dalam format teks (.txt) untuk diberikan kepada pelanggan.

Catatan
Data Dinamis: Aplikasi membaca data dari tabel komponen di database, memudahkan pembaruan stok atau harga tanpa mengubah source code.

Manajemen Sesi: Nama kasir yang login akan ditampilkan di pojok kanan atas aplikasi sebagai penanda sesi aktif.

Tampilan: Menggunakan Look & Feel System agar antarmuka BuildWJax terlihat menyatu dengan tema Windows yang sedang digunakan.
